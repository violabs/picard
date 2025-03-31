package io.violabs.picard

import io.violabs.picard.domain.Pod
import io.violabs.picard.domain.builder.Builder
import io.violabs.picard.domain.builder.PodBuilder

fun buildPod(scope: PodBuilder.() -> Unit): Pod = scopedBuild(::PodBuilder, scope)

fun <T : Builder<R>, R> scopedBuild(emptyConstructor: () -> T, scope: T.() -> Unit): R {
    val builder = emptyConstructor()
    builder.scope()
    return builder.build()
}
