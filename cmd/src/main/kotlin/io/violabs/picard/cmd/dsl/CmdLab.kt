package io.violabs.picard.cmd.dsl

import io.violabs.picard.cmd.KubectlConstants.APPLY
import io.violabs.picard.cmd.KubectlConstants.DELETE
import io.violabs.picard.cmd.KubectlConstants.FILE_FLAG
import io.violabs.picard.cmd.KubectlConstants.GET
import io.violabs.picard.cmd.KubectlConstants.KUBECTL
import io.violabs.picard.cmd.KubectlConstants.POD
import io.violabs.picard.cmd.KubectlConstants.PODS
import io.violabs.picard.common.Logger
import io.violabs.picard.common.Logging

val logger = Logger(Logging.CMD)

fun kubectl(scope: Kubectl.() -> Unit) {
    Kubectl().apply(scope)
}

class Kubectl {
    fun getPods(scope: GetPodsTask.() -> Unit) {
        val task = GetPodsTask().apply(scope)

        processTask(task)
    }

    fun applyPod(scope: ApplyTask.() -> Unit) {
        val task = ApplyTask().apply(scope)

        processTask(task)
    }

    fun deletePod(scope: DeletePodTask.() -> Unit) {
        val task = DeletePodTask().apply(scope)

        processTask(task)
    }

    private fun processTask(task: Task) {
        logger.info("Executing command: ${task.cmd().joinToString(" ")}")
        try {
            val process = ProcessBuilder(*task.cmd())
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
            logger.info("Error executing command: ${task.cmd().joinToString(" ")}")
            logger.info("Exception: ${e.message}")
            throw e
        }
    }
}

class GetPodsTask : Task {
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

class DeletePodTask : Task {
    var name: String? = null

    override fun cmd(): Array<String> {
        return arrayOf(KUBECTL, DELETE, POD, name!!)
    }
}

class ApplyTask : Task {
    var fileLocation: String? = null

    override fun cmd(): Array<String> {
        val loc = requireNotNull(fileLocation) { "file location must be provided" }
        return arrayOf(KUBECTL, APPLY, FILE_FLAG, loc)
    }
}

interface Task {
    fun cmd(): Array<String>
}