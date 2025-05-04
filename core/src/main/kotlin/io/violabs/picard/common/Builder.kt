package io.violabs.picard.common

import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource

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

abstract class ResourceListDSLBuilder<
    T : K8sResource<*>,
    B : DSLBuilder<T>,
    G : K8sListResource.ItemGroup<T, B>,
    L : K8sListResource<*, T>
    >(private val groupBuilder: G) : DSLBuilder<L> {
    protected var metadata: ListMeta? = null
    protected var items: List<T>? = null

    fun items(scope: G.() -> Unit) {
        items = groupBuilder.apply(scope).listItems()
    }

    fun metadata(scope: ListMeta.Builder.() -> Unit) {
        metadata = ListMeta.Builder().apply(scope).build()
    }
}