package io.violabs.picard.domain

abstract class DefaultGroup<T> {
    private val items: MutableList<T> = mutableListOf()

    protected fun items(): MutableList<T>? {
        return items.takeIf { it.isNotEmpty() }
    }

    protected fun add(item: T) {
        items.add(item)
    }
}