package io.violabs.picard.domain

class LocalObjectReferenceGroup {
    private val refs = mutableListOf<LocalObjectReference>()

    fun references(): MutableList<LocalObjectReference> {
        return refs
    }

    fun reference(name: String) {
        refs.add(LocalObjectReference(name))
    }
}