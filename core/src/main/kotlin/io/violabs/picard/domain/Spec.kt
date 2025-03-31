package io.violabs.picard.domain

/**
 * @property restartPolicy defaults to Always if not specified when run by kubectl
 */
data class Spec(
    val containers: List<Container>? = null,
    val restartPolicy: RestartPolicy? = null,
    val template: PodTemplate? = null
)