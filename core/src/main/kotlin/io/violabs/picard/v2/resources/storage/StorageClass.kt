package io.violabs.picard.v2.resources.storage

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.domain.manifest.StorageResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.storage.selector.label.TopologySelectorTerm

/**
 * StorageClass describes the parameters for a class of storage for which PersistentVolumes can be dynamically provisioned.
 * apiVersion: storage.k8s.io/v1
 *
 * import "k8s.io/api/storage/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/storage-class-v1/
 *
 * StorageClass describes the parameters for a class of storage for which
 * PersistentVolumes can be dynamically provisioned.
 *
 * StorageClasses are non-namespaced; the name of the storage class according to
 * etcd is in ObjectMeta.Name.
 */
@GeneratedDsl(withListGroup = true)
data class StorageClass(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMeta? = null,
    /**
     * provisioner indicates the type of the provisioner.
     */
    val provisioner: String,
    /**
     * allowVolumeExpansion shows whether the storage class allow volume expand.
     */
    val allowVolumeExpansion: Boolean? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * allowedTopologies restrict the node topologies where volumes can be dynamically
     * provisioned. Each volume plugin defines its own supported topology specifications.
     * An empty TopologySelectorTerm list means there is no topology restriction. This
     * field is only honored by servers that enable the VolumeScheduling feature.
     */
    val allowedTopologies: List<TopologySelectorTerm>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * mountOptions controls the mountOptions for dynamically provisioned PersistentVolumes
     * of this storage class. e.g. ["ro", "soft"]. Not validated - mount of the PVs will
     * simply fail if one is invalid.
     */
    val mountOptions: List<String>? = null,
    /**
     * parameters holds the parameters for the provisioner that should create volumes of
     * this storage class.
     */
    val parameters: Map<String, String>? = null,
    /**
     * reclaimPolicy controls the reclaimPolicy for dynamically provisioned
     * PersistentVolumes of this storage class. Defaults to Delete.
     */
    val reclaimPolicy: String? = null,
    /**
     * volumeBindingMode indicates how PersistentVolumeClaims should be provisioned
     * and bound. When unset, VolumeBindingImmediate is used. This field is only honored
     * by servers that enable the VolumeScheduling feature.
     */
    val volumeBindingMode: String? = null,
) : StorageResource<StorageClass.Version, ObjectMeta> {
    interface Version : APIVersion
}