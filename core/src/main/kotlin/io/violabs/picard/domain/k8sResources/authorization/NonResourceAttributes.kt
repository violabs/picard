package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.HTTPVerb

data class NonResourceAttributes(
    val path: String? = null,
    val verb: HTTPVerb? = null
) {
    class Builder : DSLBuilder<NonResourceAttributes> {
        var path: String? = null
        var verb: HTTPVerb? = null

        override fun build(): NonResourceAttributes {
            return NonResourceAttributes(path, verb)
        }
    }
}
