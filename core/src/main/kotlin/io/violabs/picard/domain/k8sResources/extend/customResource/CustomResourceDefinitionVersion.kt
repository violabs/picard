package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.extend.json.SelectableField

data class CustomResourceDefinitionVersion(
    val name: String,
    val served: Boolean,
    val storage: Boolean,
    val additionalPrinterColumns: List<CustomResourceColumnDefinition>? = null,
    val deprecated: Boolean? = null,
    val deprecationWarning: String? = null,
    val schema: CustomResourceValidation? = null,
    val selectableFields: List<SelectableField>? = null,
    val subresources: CustomResourceSubresources? = null
) {
    class Builder : DSLBuilder<CustomResourceDefinitionVersion> {
        var name: String? = null
        private var served: Boolean? = null
        private var storage: Boolean? = null
        private var additionalPrinterColumns: List<CustomResourceColumnDefinition>? = null
        private var deprecated: Boolean? = null
        var deprecationWarning: String? = null
        private var schema: CustomResourceValidation? = null
        private var selectableFields: List<SelectableField>? = null
        private var subresources: CustomResourceSubresources? = null

        fun served(value: Boolean = true) {
            this.served = value
        }

        fun storage(value: Boolean = true) {
            this.storage = value
        }

        fun additionalPrinterColumns(scope: CustomResourceColumnDefinition.Group.() -> Unit) {
            this.additionalPrinterColumns = CustomResourceColumnDefinition.Group().apply(scope).definitions()
        }

        fun deprecated(value: Boolean = true) {
            this.deprecated = value
        }

        fun schema(scope: CustomResourceValidation.Builder.() -> Unit) {
            this.schema = CustomResourceValidation.Builder().apply(scope).build()
        }

        fun selectableFields(scope: SelectableField.Group.() -> Unit) {
            this.selectableFields = SelectableField.Group().apply(scope).fields()
        }


        fun subresources(scope: CustomResourceSubresources.Builder.() -> Unit) {
            this.subresources = CustomResourceSubresources.Builder().apply(scope).build()
        }

        override fun build(): CustomResourceDefinitionVersion {
            return CustomResourceDefinitionVersion(
                name = vRequireNotNull(this::name),
                served = vRequireNotNull(this::served),
                storage = vRequireNotNull(this::storage),
                additionalPrinterColumns = additionalPrinterColumns,
                deprecated = deprecated,
                deprecationWarning = deprecationWarning,
                schema = schema,
                selectableFields = selectableFields,
                subresources = subresources
            )
        }
    }
}