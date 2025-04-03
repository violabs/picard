package io.violabs.picard.domain.k8sResources

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/quantity/#Quantity
 */
@JvmInline
value class Quantity(val value: String)