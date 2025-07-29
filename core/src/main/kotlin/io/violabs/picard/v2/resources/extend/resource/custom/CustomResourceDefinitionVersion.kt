package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CustomResourceDefinitionVersion describes a version for CRD.
 */
@GeneratedDsl(withListGroup = true)
data class CustomResourceDefinitionVersion(
    /**
     * name is the version name, e.g. "v1", "v2beta1", etc. 
     * The custom resources are served under this version at /apis/<group>/<version>/... if served is true.
     */
    val name: String? = null,
    /**
     * served is a flag enabling/disabling this version from being served via REST APIs
     */
    val served: Boolean? = null,
    /**
     * storage indicates this version should be used when persisting custom resources to storage. 
     * There must be exactly one version with storage=true.
     */
    val storage: Boolean? = null,
    /**
     * additionalPrinterColumns specifies additional columns returned in Table output. 
     * See https://kubernetes.io/docs/reference/using-api/api-concepts/#receiving-resources-as-tables for details. 
     * If no columns are specified, a single column displaying the age of the custom resource is used.
     */
    val additionalPrinterColumns: List<CustomResourceColumnDefinition>? = null,
    /**
     * deprecated indicates this version of the custom resource API is deprecated. 
     * When set to true, API requests to this version receive a warning header in the server response. 
     * Defaults to false.
     */
    val deprecated: Boolean? = null,
    /**
     * deprecationWarning overrides the default warning returned to API clients. 
     * May only be set when deprecated is true. The default warning indicates this version is deprecated 
     * and recommends use of the newest served version of equal or greater stability, if one exists.
     */
    val deprecationWarning: String? = null,
    /**
     * schema describes the schema used for validation, pruning, and defaulting of this version of the custom resource.
     */
    val schema: CustomResourceValidation? = null,
    /**
     * selectableFields specifies paths to fields that may be used as field selectors. 
     * A maximum of 8 selectable fields are allowed. 
     * See https://kubernetes.io/docs/concepts/overview/working-with-objects/field-selectors
     */
    val selectableFields: List<SelectableField>? = null,
    /**
     * subresources specify what subresources this version of the defined custom resource have.
     */
    val subresources: CustomResourceSubresources? = null
)