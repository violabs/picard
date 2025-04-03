package io.violabs.picard.domain

/**
 * @property restartPolicy defaults to Always if not specified when run by kubectl
 */
data class Spec(
    val containers: List<Container>? = null,
    val restartPolicy: RestartPolicy? = null,
    val template: PodTemplate? = null,
    val readinessGates: List<ReadinessGate>? = null,
    val initContainers: List<Container>? = null,
    val ports: List<ServicePortConfig>? = null,
    val volumes: List<Volume>? = null,
    val replicas: Int? = null,
    val selector: Selector? = null,
    val strategy: Strategy? = null
)