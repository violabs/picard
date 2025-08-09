package io.violabs.picard.domain

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/list-meta/#ListMeta
 * import "k8s.io/apimachinery/pkg/apis/meta/v1"
 */
@GeneratedDsl
data class ListMeta(
    // continue
    @JsonProperty("continue")
    val continueGather: String? = null,
    val remainingItemCount: Long? = null,
    val resourceVersion: String? = null
)