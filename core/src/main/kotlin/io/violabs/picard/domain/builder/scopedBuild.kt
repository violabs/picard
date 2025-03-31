package io.violabs.picard.domain.builder

fun <T : Builder<R>, R> scopedBuild(emptyConstructor: () -> T, scope: T.() -> Unit): R {
    val builder = emptyConstructor()
    builder.scope()
    return builder.build()
}
