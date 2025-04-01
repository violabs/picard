package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.KubeletConfiguration

class EvictionHardBuilder : Builder<KubeletConfiguration.EvictionHard> {
    private var memory: KubeletConfiguration.EvictionHard.Memory? = null
    private var nodeFs: KubeletConfiguration.EvictionHard.NodeFs? = null
    private var imageFs: KubeletConfiguration.EvictionHard.ImageFs? = null

    override fun build(): KubeletConfiguration.EvictionHard {
        return KubeletConfiguration.EvictionHard(
            memory = memory,
            nodefs = nodeFs,
            imagefs = imageFs
        )
    }

    fun memory(scope: MemoryBuilder.() -> Unit) {
        memory = MemoryBuilder().apply(scope).build()
    }

    fun nodeFs(scope: NodeFsBuilder.() -> Unit) {
        nodeFs = NodeFsBuilder().apply(scope).build()
    }

    fun imageFs(scope: ImageFsBuilder.() -> Unit) {
        imageFs = ImageFsBuilder().apply(scope).build()
    }

    class MemoryBuilder : Builder<KubeletConfiguration.EvictionHard.Memory> {
        var available: String? = null

        override fun build(): KubeletConfiguration.EvictionHard.Memory {
            return KubeletConfiguration.EvictionHard.Memory(
                available = requireNotNull(available) { "Please provide a memory available" }
            )
        }
    }

    class NodeFsBuilder : Builder<KubeletConfiguration.EvictionHard.NodeFs> {
        var available: String? = null
        var iNodesFree: String? = null

        override fun build(): KubeletConfiguration.EvictionHard.NodeFs {
            return KubeletConfiguration.EvictionHard.NodeFs(
                available = requireNotNull(available) { "Please provide a nodeFs available" },
                inodesFree = requireNotNull(iNodesFree) { "Please provide a nodeFs iNodesFree" }
            )
        }
    }

    class ImageFsBuilder : Builder<KubeletConfiguration.EvictionHard.ImageFs> {
        var available: String? = null
        var iNodesFree: String? = null

        override fun build(): KubeletConfiguration.EvictionHard.ImageFs {
            return KubeletConfiguration.EvictionHard.ImageFs(
                available = requireNotNull(available) { "Please provide a imageFs available" },
                inodesFree = requireNotNull(iNodesFree) { "Please provide a imageFs iNodesFree" }
            )
        }
    }
}