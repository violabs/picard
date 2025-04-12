package io.violabs.picard.domain

abstract class BuilderGroup<T, B : DslBuilder<T>>(protected val builder: B) {
    private val items: MutableList<T> = mutableListOf()

    protected fun items(): MutableList<T>? {
        return items.takeIf { it.isNotEmpty() }
    }

    fun add(scope: B.() -> Unit) {
        items.add(builder.apply(scope).build())
    }
}