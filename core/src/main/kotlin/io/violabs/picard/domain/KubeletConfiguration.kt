package io.violabs.picard.domain

data class KubeletConfiguration(
    val apiVersion: Version,
    val kind: Kind,
    val address: String,
    val port: Int,
    val serializeImagePulls: Boolean,
    val evictionHard: EvictionHard? = null
) {
    enum class Version(val ref: String) {
        V1_BETA1("kubelet.config.k8s.io/v1beta1");

        override fun toString(): String = ref
    }

    data class EvictionHard(
        val memory: Memory? = null,
        val nodeFs: NodeFs? = null,
        val imageFs: ImageFs? = null
    ) {
        data class Memory(val available: String)
        data class NodeFs(
            val available: String,
            val iNodesFree: String
        )
        data class ImageFs(
            val available: String,
            val iNodesFree: String
        )
    }
}