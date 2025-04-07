package io.violabs.picard.cmd.dsl

import io.violabs.picard.cmd.KubectlConstants.APPLY
import io.violabs.picard.cmd.KubectlConstants.DELETE
import io.violabs.picard.cmd.KubectlConstants.DEPLOYMENT
import io.violabs.picard.cmd.KubectlConstants.DESCRIBE
import io.violabs.picard.cmd.KubectlConstants.FILE_FLAG
import io.violabs.picard.cmd.KubectlConstants.GET
import io.violabs.picard.cmd.KubectlConstants.KUBECTL
import io.violabs.picard.cmd.KubectlConstants.LOGS
import io.violabs.picard.cmd.KubectlConstants.POD
import io.violabs.picard.cmd.KubectlConstants.PODS
import io.violabs.picard.common.Logger
import io.violabs.picard.common.Logging

//kubectl get pods frontend-gbgfx -o yaml

val logger = Logger(Logging.CMD)

fun kubectl(scope: Kubectl.() -> Unit) {
    Kubectl().apply(scope)
}

class Kubectl {
    fun applyPod(scope: ApplyPodKubeTask.() -> Unit) {
        val task = ApplyPodKubeTask().apply(scope)

        processTask(task)
    }

    fun describePod(scope: DescribePodKubeTask.() -> Unit) {
        val task = DescribePodKubeTask().apply(scope)
        processTask(task)
    }

    //kubectl delete deployment <deployment-name> -n <namespace>
    fun deletePod(scope: DeletePodKubeTask.() -> Unit) {
        val task = DeletePodKubeTask().apply(scope)

        processTask(task)
    }

    fun deleteDeployment(scope: DeleteDeploymentKubeTask.() -> Unit) {
        val task = DeleteDeploymentKubeTask().apply(scope)

        processTask(task)
    }

    fun getPods(scope: GetPodsKubeTask.() -> Unit) {
        val task = GetPodsKubeTask().apply(scope)

        processTask(task)
    }

    fun logs(scope: LogsKubeTask.() -> Unit) {
        val task = LogsKubeTask().apply(scope)

        processTask(task)
    }

    private fun processTask(kubeTask: KubeTask) {
        if (!kubeTask.enabled) {
            logger.info("Task is disabled, skipping execution. command: ${kubeTask.cmd().joinToString(" ")}")
            return
        }

        logger.info("Executing command: ${kubeTask.cmd().joinToString(" ")}")
        try {
            val process = ProcessBuilder(*kubeTask.cmd())
                .redirectErrorStream(true)  // Merge stderr with stdout
                .start()

            val lines = process.inputReader().readLines().toMutableList()

            // Check process exit code
            val exitCode = process.waitFor()
            if (exitCode != 0) {
                lines.forEach { logger.error(it) }
            } else {
                lines.forEach { logger.info(it) }
            }
        } catch (e: Exception) {
            logger.info("Error executing command: ${kubeTask.cmd().joinToString(" ")}")
            logger.info("Exception: ${e.message}")
            throw e
        }
    }
}

class GetPodsKubeTask : KubeTask() {
    private var allNameSpaces: Boolean = false
    private var output: Output? = null
    private var filter: Filter? = null
    private val cmdParts: MutableList<String> = requiredCmdArgs()
    var sortBy: String? = null

    fun allNameSpaces() {
        allNameSpaces = true
    }

    fun wide() {
        output = Output.WIDE
    }

    fun filter(scope: Filter.() -> Unit) {
        filter = Filter().apply(scope)
    }

    override fun cmd(): Array<String> {
        addAllNameSpacesIfApplied()
        addOutputIfApplied()
        processFilterIfApplied()

        return cmdParts.toTypedArray()
    }

    private fun addAllNameSpacesIfApplied() {
        if (allNameSpaces) {
            cmdParts.add("-A")
        }
    }

    private fun addOutputIfApplied() {
        if (output != null) {
            with(cmdParts) {
                add("-o")
                add(output!!.name.lowercase())
            }
        }
    }

    fun processFilterIfApplied() {
        if (filter == null) return

        val foundFilter = filter ?: return

        with(cmdParts) {
            foundFilter.labels?.also {
                add("-l")
                add("${it.key}=${it.value}")
            }

            foundFilter.namespace?.also {
                add("-n")
                add(it)
            }
        }
    }

    inner class Filter {
        internal var labels: Label? = null
        internal var namespace: String? = null

        fun byLabel(key: String, value: String) {
            labels = Label(key, value)
        }

        fun byNamespace(namespace: String) {
            this.namespace = namespace
        }
    }

    companion object {
        fun requiredCmdArgs(): MutableList<String> {
            return mutableListOf(KUBECTL, GET, PODS)
        }
    }
}

enum class Output {
    WIDE
}

class Label(
    val key: String,
    val value: String
)
//kubectl delete deployment <deployment-name> -n <namespace>
class DeletePodKubeTask : KubeTask() {
    var name: String? = null

    override fun cmd(): Array<String> {
        return arrayOf(KUBECTL, DELETE, POD, name!!)
    }
}

class DeleteDeploymentKubeTask : KubeTask() {
    var name: String? = null
    var namespace: String? = null

    override fun cmd(): Array<String> {
        val cmd = mutableListOf(KUBECTL, DELETE, DEPLOYMENT, name!!)
        if (namespace != null) {
            cmd.add("-n")
            cmd.add(namespace!!)
        }
        return cmd.toTypedArray()
    }
}

class ApplyPodKubeTask : KubeTask() {
    var fileLocation: String? = null

    override fun cmd(): Array<String> {
        val loc = requireNotNull(fileLocation) { "file location must be provided" }
        return arrayOf(KUBECTL, APPLY, FILE_FLAG, loc)
    }
}

class DescribePodKubeTask : KubeTask() {
    var fileLocation: String? = null
    var namespace: String? = null

    override fun cmd(): Array<String> {
        val loc = requireNotNull(fileLocation) { "file location must be provided" }
        val cmd = mutableListOf(KUBECTL, DESCRIBE, FILE_FLAG, loc)

        if (namespace != null) {
            cmd.add("-n")
            cmd.add(namespace!!)
        }

        return cmd.toTypedArray()
    }
}

//kubectl logs myapp-pod -c init-myservice
class LogsKubeTask : KubeTask() {
    var name: String? = null
    var container: String? = null
    var follow: Boolean = false

    override fun cmd(): Array<String> {
        val cmdParts = mutableListOf(KUBECTL, LOGS, name!!)
        if (container != null) {
            cmdParts.add("-c")
            cmdParts.add(container!!)
        }
        if (follow) {
            cmdParts.add("-f")
        }
        return cmdParts.toTypedArray()
    }
}

abstract class KubeTask {
    var enabled: Boolean = true
    abstract fun cmd(): Array<String>

    fun disable() {
        enabled = false
    }
}


enum class Phase {
    PENDING,
    RUNNING,
    SUCCEEDED,
    FAILED,
    UNKNOWN;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}

enum class ContainerState {
    WAITING,
    RUNNING,
    TERMINATED;

    override fun toString(): String = name.lowercase().replaceFirstChar { it.uppercase() }
}