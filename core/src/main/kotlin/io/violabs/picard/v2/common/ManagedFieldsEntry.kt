package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion

/**
 * A workflow-id, a FieldSet and the group version of the resource that the fieldset applies to.
 */
@GeneratedDsl
data class ManagedFieldsEntry(
    /**
     * Defines the version of this resource that this field set applies to. The format is
     * "group/version" just like the top-level APIVersion field. It is necessary to track the
     * version of a field set because it cannot be automatically converted.
     */
    val version: APIVersion? = null,
    /**
     * FieldsType is the discriminator for the different fields format and version.
     * There is currently only one possible value: "FieldsV1"
     */
    val fieldsType: String? = null,
    /**
     * FieldsV1 holds the first JSON version format as described in the "FieldsV1" type.
     *
     * *FieldsV1 stores a set of fields in a data structure like a Trie, in JSON format.
     *
     * Each key is either a '.' representing the field itself, and will always map to an empty set,
     * or a string representing a sub-field or item. The string will follow one of these
     * four formats: 'f:', where is the name of a field in a struct, or key in a map 'v:', where
     * is the exact json formatted value of a list item 'i:', where is position of a item in a list 'k:',
     * where is a map of a list item's key fields to their unique values If a key maps to an empty
     * Fields value, the field that key represents is part of the set.
     *
     * The exact format is defined in sigs.k8s.io/structured-merge-diff*
     */
    val fieldsV1: String? = null,
    /**
     * Manager is an identifier of the workflow managing these fields.
     */
    val manager: String? = null,
    /**
     * Operation is the type of operation which lead to this ManagedFieldsEntry being created.
     * The only valid values for this field are 'Apply' and 'Update'.
     */
    val operation: String? = null,
    /**
     * Subresource is the name of the subresource used to update that object, or empty string
     * if the object was updated through the main resource. The value of this field is used to
     * distinguish between managers, even if they share the same name. For example, a status
     * update will be distinct from a regular update using the same manager name. Note that
     * the APIVersion field is not related to the Subresource field and it always corresponds
     * to the version of the main resource.
     */
    val subresource: String? = null,
    /**
     * Time is the timestamp of when the ManagedFields entry was added. The timestamp will also
     * be updated if a field is added, the manager changes any of the owned fields value or
     * removes a field. The timestamp does not update when a field is removed from the entry
     * because another manager took it over.
     *
     * Time is a wrapper around time. Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val time: String? = null
)