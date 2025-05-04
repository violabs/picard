package io.violabs.picard.domain

import io.violabs.picard.common.DSLBuilder

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/list-meta/#ListMeta
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 */
data class ListMeta(
    // continue
    val continueGather: String? = null,
    val remainingItemCount: Long? = null,
    val resourceVersion: String? = null
) {
    val `continue`: String? by lazy { continueGather }

    class Builder : DSLBuilder<ListMeta> {
        var continueGather: String? = null
        var remainingItemCount: Long? = null
        var resourceVersion: String? = null

        override fun build(): ListMeta {
            return ListMeta(
                continueGather,
                remainingItemCount,
                resourceVersion
            )
        }
    }
}