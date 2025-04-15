package io.violabs.picard.domain

interface DSLBuilder<T> {

    fun build(): T
}

abstract class ResourceSpecStatusDSLBuilder<
    T,
    SPEC : BaseSpec,
    SPEC_B : DSLBuilder<SPEC>,
    STATUS : BaseStatus,
    STATUS_B : DSLBuilder<STATUS>,
    >(private val specBuilder: SPEC_B, private val statusBuilder: STATUS_B) : ResourceDSLBuilder<T>() {
    protected var spec: SPEC? = null
    protected var status: STATUS? = null

    fun spec(scope: SPEC_B.() -> Unit) {
        spec = specBuilder.apply(scope).build()
    }

    fun status(scope: STATUS_B.() -> Unit) {
        status = statusBuilder.apply(scope).build()
    }
}

abstract class ResourceSpecDSLBuilder<
    T,
    SPEC : BaseSpec,
    SPEC_B : DSLBuilder<SPEC>,
    >(private val specBuilder: SPEC_B) : ResourceDSLBuilder<T>() {
    protected var spec: SPEC? = null

    fun spec(scope: SPEC_B.() -> Unit) {
        spec = specBuilder.apply(scope).build()
    }
}

abstract class ResourceStatusDSLBuilder<
    T,
    STATUS : BaseStatus,
    STATUS_B : DSLBuilder<STATUS>,
    >(private val statusBuilder: STATUS_B) : ResourceDSLBuilder<T>() {
    protected var status: STATUS? = null

    fun status(scope: STATUS_B.() -> Unit) {
        status = statusBuilder.apply(scope).build()
    }
}

abstract class ResourceDSLBuilder<T> : DSLBuilder<T> {
    protected var metadata: ObjectMetadata? = null

    fun metadata(scope: ObjectMetadata.Builder.() -> Unit) {
        metadata = ObjectMetadata.Builder().apply(scope).build()
    }
}