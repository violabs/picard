package io.violabs.picard.domain.k8sResources

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/quantity/#Quantity
 */
@JvmInline
@SingleEntryTransformDsl<String>(String::class)
value class Quantity(val value: String)