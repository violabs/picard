package io.violabs.picard.domain

data class Strategy(
    val type: Type,
    val rollingUpdate: RollingUpdate? = null
) {
    enum class Type : K8sEnum {
        ROLLING_UPDATE;

        override fun toString(): String = properCase()
    }

    data class RollingUpdate(
        val maxUnavailable: Int? = null,
        val maxSurge: Int? = null,
        val progressDeadlineSeconds: Int? = null,
        val minReadySeconds: Int? = null,
        val revisionHistoryLimit: Int? = null,
        val paused: Boolean? = null
    )
}