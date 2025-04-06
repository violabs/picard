package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigrationList
import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigration
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClassList
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClass
import io.violabs.picard.domain.k8sResources.config.secret.SecretList
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.storage.volume.persistentVolume.PersistentVolumeList
import io.violabs.picard.domain.k8sResources.storage.volume.persistentVolumeClaim.PersistentVolumeClaimList
import io.violabs.picard.domain.k8sResources.storage.volume.persistentVolumeClaim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.storage.volume.persistentVolume.PersistentVolume
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacity
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacityList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINodeList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINode
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriverList
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriver
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList
import io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevision
import io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevisionList
import io.violabs.picard.domain.k8sResources.workload.cronJob.CronJob
import io.violabs.picard.domain.k8sResources.workload.cronJob.CronJobList
import io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSet
import io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSetList
import io.violabs.picard.domain.k8sResources.workload.deployment.Deployment
import io.violabs.picard.domain.k8sResources.workload.deployment.DeploymentList
import io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscaler
import io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscalerList
import io.violabs.picard.domain.k8sResources.workload.job.Job
import io.violabs.picard.domain.k8sResources.workload.job.JobList
import io.violabs.picard.domain.k8sResources.workload.pod.Pod
import io.violabs.picard.domain.k8sResources.workload.pod.PodList
import io.violabs.picard.domain.k8sResources.workload.podSchedulingContext.PodSchedulingContext
import io.violabs.picard.domain.k8sResources.workload.podSchedulingContext.PodSchedulingContextList
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplateList
import io.violabs.picard.domain.k8sResources.workload.priorityClass.PriorityClass
import io.violabs.picard.domain.k8sResources.workload.priorityClass.PriorityClassList
import io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSet
import io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSetList
import io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationController
import io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationControllerList
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaimList
import io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplate
import io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplateList
import io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSlice
import io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSliceList
import io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSet
import io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSetList

interface APIVersion {
    fun refString(): String
}

open class KAPIVersion(
    private val ref: String? = null
) {
    fun refString(): String = ref ?: this::class.simpleName!!.lowercase()

    override fun toString(): String = refString()

    //apiVersion: storage.k8s.io/v1
    object V1 : KAPIVersion(),
        ConfigMap.Version,
        ConfigMapList.Version,
        PersistentVolume.Version,
        PersistentVolumeList.Version,
        PersistentVolumeClaim.Version,
        PersistentVolumeClaimList.Version,
        Pod.Version,
        PodList.Version,
        PodTemplate.Version,
        PodTemplateList.Version,
        ReplicationController.Version,
        ReplicationControllerList.Version,
        Secret.Version,
        SecretList.Version

    object AppsV1 : KAPIVersion(),
        ControllerRevision.Version,
        ControllerRevisionList.Version,
        DaemonSet.Version,
        DaemonSetList.Version,
        Deployment.Version,
        DeploymentList.Version,
        ReplicaSet.Version,
        ReplicaSetList.Version,
        StatefulSet.Version,
        StatefulSetList.Version

    object AutoscalingV1 : KAPIVersion(),
        HorizontalPodAutoscaler.Version,
        HorizontalPodAutoscalerList.Version

    object AutoscalingV2 : KAPIVersion(),
        HorizontalPodAutoscaler.Version,
        HorizontalPodAutoscalerList.Version

    object BatchV1 : KAPIVersion(),
        CronJob.Version,
        CronJobList.Version,
        Job.Version,
        JobList.Version

    object ResourceV1Alpha3 : KAPIVersion("resource.k8s.io/v1alpha3"),
        PodSchedulingContext.Version,
        PodSchedulingContextList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version

    object ResourceV1Beta1 : KAPIVersion(),
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version

    object SchedulingV1 : KAPIVersion("scheduling.k8s.io/v1"),
        PriorityClass.Version,
        PriorityClassList.Version

    object StorageV1 : KAPIVersion("storage.k8s.io/v1"),
        CSIDriver.Version,
        CSIDriverList.Version,
        CSINode.Version,
        CSINodeList.Version,
        CSIStorageCapacity.Version,
        CSIStorageCapacityList.Version,
        StorageClass.Version,
        StorageClassList.Version

    object StorageMigrationV1Alpha1 : KAPIVersion("storagemigration.k8s.io/v1alpha1"),
        StorageVersionMigration.Version,
        StorageVersionMigrationList.Version
}