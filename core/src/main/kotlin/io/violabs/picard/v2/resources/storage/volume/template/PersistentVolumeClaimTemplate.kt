package io.violabs.picard.v2.resources.storage.volume.template

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimSpec

//todo: update with v2
/**
 * Will be used to create a stand-alone PVC to provision the volume. The pod in which this
 * EphemeralVolumeSource is embedded will be the owner of the PVC, i.e. the PVC will be deleted
 * together with the pod. The name of the PVC will be \<pod name>-\<volume name> where \<volume name> is the
 * name from the PodSpec.Volumes array entry. Pod validation will reject the pod if the concatenated name is
 * not valid for a PVC (for example, too long).
 *
 * An existing PVC with that name that is not owned by the pod will not be used for the pod to avoid
 * using an unrelated volume by mistake. Starting the pod is then blocked until the unrelated PVC is removed.
 * If such a pre-created PVC is meant to be used by the pod, the PVC has to updated with an owner reference to
 * the pod once the pod exists. Normally this should not be necessary, but it may be useful when manually
 * reconstructing a broken cluster.
 *
 * This field is read-only and no changes will be made by Kubernetes to the PVC after it has been created.
 */
@GeneratedDsl
data class PersistentVolumeClaimTemplate(
    /**
     * The specification for the PersistentVolumeClaim. The entire content is copied unchanged
     * into the PVC that gets created from this template. The same fields as in a PersistentVolumeClaim
     * are also valid here.
     */
    val spec: PersistentVolumeClaimSpec,
    /**
     * May contain labels and annotations that will be copied into the PVC when creating it. No other
     * fields are allowed and will be rejected during validation.
     */
    val metadata: ObjectMeta? = null
)