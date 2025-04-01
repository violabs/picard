package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.CrashLoopBackOff
import io.violabs.picard.domain.Kind

data class KubeletConfiguration(
    val apiVersion: String,
    val kind: Kind,
    val address: String,
    val port: Int,
    val serializeImagePulls: Boolean,
    val evictionHard: EvictionHard? = null,
    val crashLoopBackOff: CrashLoopBackOff? = null
) {
    enum class DefinedVersion(val ref: String) {
        V1_BETA1("kubelet.config.k8s.io/v1beta1");

        override fun toString(): String = ref
    }

    data class EvictionHard(
        val memory: Memory? = null,
        val nodefs: NodeFs? = null,
        val imagefs: ImageFs? = null
    ) {
        data class Memory(val available: String)
        data class NodeFs(
            val available: String,
            val inodesFree: String
        )
        data class ImageFs(
            val available: String,
            val inodesFree: String
        )
    }
}