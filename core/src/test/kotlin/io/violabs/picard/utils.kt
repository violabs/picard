package io.violabs.picard

import io.violabs.picard.domain.DslBuilder

fun <B : DslBuilder<T>, T> dslBuild(builder: B, block: B.() -> Unit): T {
    return builder.apply(block).build()
}