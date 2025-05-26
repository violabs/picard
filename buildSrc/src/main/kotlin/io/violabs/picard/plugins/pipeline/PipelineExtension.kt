package io.violabs.picard.plugins.pipeline

open class PipelineExtension {
    var hasSrc: Boolean = false
    private var dependencyLookUp: DependencyLookUp? = null

    fun dependencyLookUps(): DependencyLookUp? {
        return dependencyLookUp
    }

    fun dependencyLookUp(block: DependencyLookUp.() -> Unit) {
        dependencyLookUp = DependencyLookUp().apply(block)
    }
}