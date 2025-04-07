package io.violabs.picard.domain.k8sResources.extend.customResource

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
)