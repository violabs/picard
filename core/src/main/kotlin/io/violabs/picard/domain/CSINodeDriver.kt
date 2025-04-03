package io.violabs.picard.domain

data class CSINodeDriver(
    val name: String,
    val nodeID: String,
    val allocatable: Allocatable? = null,
    val topologyKeys: List<String>? = null
) {
    data class Allocatable(val count: Long)
}