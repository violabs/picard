package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class CustomResourceDefinitionNames(
    val kind: String,
    val plural: String,
    val categories: List<String>? = null,
    val listKind: String? = null,
    val shortNames: List<String>? = null,
    val singular: String? = null
) {
    class Builder : DSLBuilder<CustomResourceDefinitionNames> {
        var kind: String? = null
        var plural: String? = null
        private var categories: List<String>? = null
        var listKind: String? = null
        private var shortNames: List<String>? = null
        var singular: String? = null

        fun categories(vararg categories: String) {
            this.categories = categories.toList()
        }

        fun shortNames(vararg shortNames: String) {
            this.shortNames = shortNames.toList()
        }

        override fun build(): CustomResourceDefinitionNames {
            return CustomResourceDefinitionNames(
                kind = vRequireNotNull(this::kind),
                plural = vRequireNotNull(this::plural),
                categories = categories,
                listKind = listKind,
                shortNames = shortNames,
                singular = singular
            )
        }
    }
}