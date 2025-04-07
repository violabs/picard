package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.domain.BasePort


data class Endpoint(
    val addresses: List<String>? = null,
    val conditions: List<Condition>? = null,
    val hints: Hints? = null,
    val hostname: String? = null,
    val nodeName: String? = null,
    val targetRef: ObjectReference? = null,
    val zone: String? = null
) {
    data class Subset(
        val addresses: List<Address>? = null,
        val notReadyAddresses: List<Address>? = null,
        val ports: List<Port>? = null
    )

    data class Address(
        val ip: String,
        val hostname: String? = null,
        val nodeName: String? = null,
        val targetRef: ObjectReference? = null
    )

    data class Port(
        val port: Int,
        val protocol: Protocol? = null,
        val name: String? = null,
        val appProtocol: String? = null
    ) : BasePort

    data class Condition(
        val ready: Boolean? = null,
        val serving: Boolean? = null,
        val terminating: Boolean? = null
    )

    data class Hints(
        val forZones: List<ForZone>? = null
    ) {
        data class ForZone(val name: String)
    }
}