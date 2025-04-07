package io.violabs.picard.domain

data class DeviceSelector(
    val cel: CEL? = null
) {
    data class CEL(
        val expression: String
    )
}