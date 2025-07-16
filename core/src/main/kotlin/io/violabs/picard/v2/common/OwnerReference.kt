package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Map: unique values on key uid will be kept during a merge
 *
 * List of objects depended by this object. If ALL objects in the list have been deleted, this object
 * will be garbage collected. If this object is managed by a controller, then an entry in this list
 * will point to this controller, with the controller field set to true. There cannot be more than
 * one managing controller.
 */
@GeneratedDsl
data class OwnerReference(
    /**
     * API version of the referent.
     */
    val apiVersion: String,
    /**
     * Kind of the referent. More info:
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds
     */
    val kind: String,
    /**
     * Name of the referent. More info:
     * https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String,
    /**
     * UID of the referent. More info:
     * https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#uids
     */
    val uid: String,
    /**
     * If true, AND if the owner has the "foregroundDeletion" finalizer, then the owner cannot be deleted
     * from the key-value store until this reference is removed. Defaults to false. To set this field,
     * a user needs "delete" permission of the owner, otherwise 422 (Unprocessable Entity) will be returned.
     */
    val blockOwnerDeletion: Boolean? = null,
    /**
     * If true, this reference points to the managing controller.
     * There can be only one managing controller.
     */
    val controller: Boolean? = null
)