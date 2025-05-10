package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants.GET
import io.violabs.picard.cmd.KubectlConstants.KUBECTL
import io.violabs.picard.cmd.KubectlConstants.PODS

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

        inner class Label(
            val key: String,
            val value: String
        )
    }

    enum class Output {
        WIDE
    }

    companion object {
        fun requiredCmdArgs(): MutableList<String> {
            return mutableListOf(KUBECTL, GET, PODS)
        }
    }
}