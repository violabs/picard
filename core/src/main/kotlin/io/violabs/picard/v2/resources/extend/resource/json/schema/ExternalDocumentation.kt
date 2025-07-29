package io.violabs.picard.v2.resources.extend.resource.json.schema

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ExternalDocumentation allows referencing an external resource for extended documentation.
 */
@GeneratedDsl
data class ExternalDocumentation(
    /**
     * Description of the external documentation
     */
    val description: String? = null,
    /**
     * URL to the external documentation
     */
    val url: String? = null
)