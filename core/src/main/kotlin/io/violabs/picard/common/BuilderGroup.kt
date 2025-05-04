package io.violabs.picard.common

abstract class BuilderGroup<T, B : DSLBuilder<T>>(protected val builder: B) {
    private val items: MutableList<T> = mutableListOf()

    protected fun items(): MutableList<T>? {
        return items.takeIf { it.isNotEmpty() }
    }

    protected fun add(scope: B.() -> Unit) {
        items.add(builder.apply(scope).build())
    }
}