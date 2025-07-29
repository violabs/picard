package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CustomResourceColumnDefinition specifies a column for server side printing.
 */
@GeneratedDsl(withListGroup = true)
data class CustomResourceColumnDefinition(
    /**
     * jsonPath is a simple JSON path (i.e. with array notation) which is evaluated against 
     * each custom resource to produce the value for this column.
     */
    val jsonPath: String? = null,
    /**
     * name is a human readable name for the column.
     */
    val name: String? = null,
    /**
     * type is an OpenAPI type definition for this column. 
     * See https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#data-types for details.
     */
    val type: String? = null,
    /**
     * description is a human readable description of this column.
     */
    val description: String? = null,
    /**
     * format is an optional OpenAPI type definition for this column. The 'name' format is applied 
     * to the primary identifier column to assist in clients identifying column is the resource name. 
     * See https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md#data-types for details.
     */
    val format: String? = null,
    /**
     * priority is an integer defining the relative importance of this column compared to others. 
     * Lower numbers are considered higher priority. Columns that may be omitted in limited space 
     * scenarios should be given a priority greater than 0.
     */
    val priority: Int? = null
)