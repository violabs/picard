package io.violabs.picard.domain.k8sResources.storage.csi.csiNode

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class CSINodeDriver(
    val name: String,
    val nodeID: String,
    val allocatable: Allocatable? = null,
    val topologyKeys: List<String>? = null
) {
    data class Allocatable(val count: Long)

    class Builder : DslBuilder<CSINodeDriver> {
        var name: String? = null
        var nodeID: String? = null
        private var allocatable: Allocatable? = null
        private var topologyKeys: List<String>? = null

        fun allocatable(count: Long) {
            allocatable = Allocatable(count)
        }

        fun topologyKeys(vararg keys: String) {
            topologyKeys = keys.toList()
        }

        override fun build(): CSINodeDriver {
            return CSINodeDriver(
                name = vRequireNotNull(this::name),
                nodeID = vRequireNotNull(this::nodeID),
                allocatable = allocatable,
                topologyKeys = topologyKeys
            )
        }
    }

    class Group : BuilderGroup<CSINodeDriver, Builder>(Builder()) {
        fun drivers(): List<CSINodeDriver>? = items()

        fun addCSINodeDriver(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}