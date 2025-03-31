package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Container
import io.violabs.picard.domain.PodTemplate
import io.violabs.picard.domain.RestartPolicy
import io.violabs.picard.domain.Spec
import io.violabs.picard.scopedBuild

class SpecBuilder : Builder<Spec> {
    private var containers: List<Container>? = null
    var restartPolicy: RestartPolicy? = null
    var template: PodTemplate? = null

    override fun build(): Spec {
        if (containers == null && template == null) {
            throw IllegalStateException("Please provide at least one container or template")
        }

        return Spec(
            containers = containers,
            restartPolicy = restartPolicy,
            template = template
        )
    }

    fun containers(scope: ContainersBuilder.() -> Unit) {
        containers = scopedBuild(::ContainersBuilder, scope)
    }

    fun template(scope: PodTemplateBuilder.() -> Unit) {
        template = scopedBuild(::PodTemplateBuilder, scope)
    }
}