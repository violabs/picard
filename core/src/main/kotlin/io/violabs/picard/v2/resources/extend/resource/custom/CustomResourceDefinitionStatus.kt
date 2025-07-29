package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CustomResourceDefinitionStatus indicates the state of the CustomResourceDefinition
 */
@GeneratedDsl
data class CustomResourceDefinitionStatus(
    /**
     * acceptedNames are the names that are actually being used to serve discovery. 
     * They may be different than the names in spec.
     */
    val acceptedNames: CustomResourceDefinitionNames? = null,
    /**
     * conditions indicate state for particular aspects of a CustomResourceDefinition
     */
    val conditions: List<CustomResourceDefinitionCondition>? = null,
    /**
     * storedVersions lists all versions of CustomResources that were ever persisted. 
     * Tracking these versions allows a migration path for stored versions in etcd. 
     * The field is mutable so a migration controller can finish a migration to another version 
     * (ensuring no old objects are left in storage), and then remove the rest of the versions from this list. 
     * Versions may not be removed from spec.versions while they exist in this list.
     */
    val storedVersions: List<String>? = null
)