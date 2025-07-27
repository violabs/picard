package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 *  FlowDistinguisherMethod specifies the method of a flow distinguisher.
 */
@SingleEntryTransformDsl<FlowDistinguisherMethod.Type>(FlowDistinguisherMethod.Type::class)
data class FlowDistinguisherMethod(
    /**
     * type is the type of flow distinguisher method The supported types are
     * "ByUser" and "ByNamespace". Required.
     */
    val type: Type? = null
) {
    enum class Type {
        /**
         * ByUser distinguishes flows by the user making the request.
         */
        ByUser,

        /**
         * ByNamespace distinguishes flows by the namespace of the request.
         */
        ByNamespace
    }
}

