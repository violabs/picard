package io.violabs.picard.domain.builder

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.pod.container.Container
import io.violabs.picard.domain.k8sResources.pod.ReadinessGate

class SpecBuilder : Builder<Spec> {
    var restartPolicy: RestartPolicy? = null
    var replicas: Int? = null
    private var containers: List<Container>? = null
    private var template: PodTemplate? = null
    private var readinessGates: List<ReadinessGate>? = null
    private var initContainers: List<Container>? = null
    private var ports: List<ServicePortConfig>? = null
    private var volumes: List<Volume>? = null
    private var selector: Selector? = null
    private var strategy: Strategy? = null

    override fun build(): Spec {
        if (containers == null && template == null && ports == null) {
            throw IllegalStateException("Please provide at least one container or template")
        }

        return Spec(
            containers = containers,
            restartPolicy = restartPolicy,
            template = template,
            readinessGates = readinessGates,
            initContainers = initContainers,
            ports = ports,
            volumes = volumes,
            replicas = replicas,
            selector = selector,
            strategy = strategy
        )
    }

    fun containers(scope: ContainersBuilder.() -> Unit) {
        containers = scopedBuild(::ContainersBuilder, scope)
    }

    fun template(scope: PodTemplateBuilder.() -> Unit) {
        template = scopedBuild(::PodTemplateBuilder, scope)
    }

    fun readinessGates(scope: ReadinessGatesBuilder.() -> Unit) {
        readinessGates = scopedBuild(::ReadinessGatesBuilder, scope)
    }

    fun initContainers(scope: ContainersBuilder.() -> Unit) {
        initContainers = scopedBuild(::ContainersBuilder, scope)
    }

    fun ports(scope: ServicePortsBuilder.() -> Unit) {
        ports = scopedBuild(::ServicePortsBuilder, scope)
    }

    fun volumes(scope: VolumesBuilder.() -> Unit) {
        volumes = scopedBuild(::VolumesBuilder, scope)
    }

    fun selector(scope: SelectorBuilder.() -> Unit) {
        selector = scopedBuild(::SelectorBuilder, scope)
    }

    fun strategy(scope: StrategyBuilder.() -> Unit) {
        strategy = scopedBuild(::StrategyBuilder, scope)
    }
}