package io.violabs.picard.domain

data class LocalObjectReference(
    val name: String? = null
) {
    class Group : DefaultGroup<LocalObjectReference>() {
        fun references(): List<LocalObjectReference>? = items()

        fun reference(name: String) {
            add(LocalObjectReference(name))
        }
    }
}