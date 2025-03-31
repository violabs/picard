package io.violabs.picard.domain

data class Container(
    val name: String,
    val image: String,
    val command: List<String>? = null,
    val ports: List<PortConfig>? = null,
    val volumeMounts: List<VolumeMount>? = null,
    val restartPolicy: RestartPolicy? = null
) {

    data class PortConfig(
        val containerPort: Int
    )
}