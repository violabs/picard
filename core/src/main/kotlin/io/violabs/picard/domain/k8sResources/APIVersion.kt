package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.k8sResources.cluster.node.Node
import io.violabs.picard.domain.k8sResources.cluster.node.NodeList
import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDR
import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDRList
import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClass
import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClassList
import io.violabs.picard.domain.k8sResources.cluster.namespace.Namespace
import io.violabs.picard.domain.k8sResources.cluster.namespace.NamespaceList
import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidate
import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidateList
import io.violabs.picard.domain.k8sResources.cluster.lease.Lease
import io.violabs.picard.domain.k8sResources.cluster.lease.LeaseList
import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddress
import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddressList
import io.violabs.picard.domain.k8sResources.cluster.apiService.APIService
import io.violabs.picard.domain.k8sResources.cluster.apiService.APIServiceList
import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatus
import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatusList
import io.violabs.picard.domain.k8sResources.cluster.event.Event
import io.violabs.picard.domain.k8sResources.cluster.event.EventList
import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigrationList
import io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigration
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClassList
import io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClass
import io.violabs.picard.domain.k8sResources.config.secret.SecretList
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolume.PersistentVolumeList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolumeClaim.PersistentVolumeClaimList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolumeClaim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.persistentVolume.PersistentVolume
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacity
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacityList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINodeList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINode
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriverList
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriver
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList
import io.violabs.picard.domain.k8sResources.service.endpoints.Endpoints
import io.violabs.picard.domain.k8sResources.service.endpoints.EndpointsList
import io.violabs.picard.domain.k8sResources.service.Service
import io.violabs.picard.domain.k8sResources.service.ServiceList
import io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSlice
import io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSliceList
import io.violabs.picard.domain.k8sResources.service.ingress.Ingress
import io.violabs.picard.domain.k8sResources.service.ingress.IngressList
import io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClass
import io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClassList
import io.violabs.picard.domain.k8sResources.storage.VolumeAttachment
import io.violabs.picard.domain.k8sResources.storage.VolumeAttachmentList
import io.violabs.picard.domain.k8sResources.storage.VolumeAttributeClassList
import io.violabs.picard.domain.k8sResources.storage.VolumeAttributesClass
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
        ComponentStatus.Version,
        ComponentStatusList.Version,
        ConfigMap.Version,
        ConfigMapList.Version,
        Endpoints.Version,
        EndpointsList.Version,
        Namespace.Version,
        NamespaceList.Version,
        Node.Version,
        NodeList.Version,
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
        SecretList.Version,
        Service.Version,
        ServiceList.Version

    object AdmissionRegistrationV1 : KAPIVersion("admissionregistration.k8s.io/v1")

    object APIExtensionsV1 : KAPIVersion("apiextensions.k8s.io/v1")

    object APIRegistrationV1 : KAPIVersion("apiregistration.k8s.io/v1"),
        APIService.Version,
        APIServiceList.Version

    object AppsV1 : KAPIVersion("apps/v1"),
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

    object AuthenticationV1 : KAPIVersion("authentication.k8s.io/v1")

    object AuthorizationV1 : KAPIVersion("authorization.k8s.io/v1")

    object AutoscalingV1 : KAPIVersion("autoscaling/v1"),
        HorizontalPodAutoscaler.Version,
        HorizontalPodAutoscalerList.Version

    object AutoscalingV2 : KAPIVersion("autoscaling/v2"),
        HorizontalPodAutoscaler.Version,
        HorizontalPodAutoscalerList.Version

    object BatchV1 : KAPIVersion("batch/v1"),
        CronJob.Version,
        CronJobList.Version,
        Job.Version,
        JobList.Version

    object CertificatesV1 : KAPIVersion("certificates.k8s.io/v1")

    object CoordinationV1 : KAPIVersion("coordination.k8s.io/v1"),
        Lease.Version,
        LeaseList.Version

    object CoordinationV1Alpha1 : KAPIVersion("coordination.k8s.io/v1alpha1"),
        LeaseCandidate.Version,
        LeaseCandidateList.Version

    object DiscoveryV1 : KAPIVersion("discovery.k8s.io/v1"),
        EndpointSlice.Version,
        EndpointSliceList.Version

    object EventsV1 : KAPIVersion("events.k8s.io/v1"),
        Event.Version,
        EventList.Version

    object FlowControlApiServerV1 : KAPIVersion("flowcontrol.apiserver.k8s.io/v1")

    object NetworkingV1 : KAPIVersion("networking.k8s.io/v1"),
        Ingress.Version,
        IngressList.Version,
        IngressClass.Version,
        IngressClassList.Version

    object NetworkingV1Beta1 : KAPIVersion("networking.k8s.io/v1"),
        IPAddress.Version,
        IPAddressList.Version,
        ServiceCIDR.Version,
        ServiceCIDRList.Version

    object NodeV1 : KAPIVersion("node.k8s.io/v1"),
        RuntimeClass.Version,
        RuntimeClassList.Version

    object PolicyV1 : KAPIVersion("policy/v1")

    object RBACAuthorizationV1 : KAPIVersion("rbac.authorization.k8s.io/v1")

    object ResourceV1Alpha3 : KAPIVersion("resource.k8s.io/v1alpha3"),
        PodSchedulingContext.Version,
        PodSchedulingContextList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version

    object ResourceV1Beta1 : KAPIVersion("resource.k8s.io/v1beta1"),
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
        StorageClassList.Version,
        VolumeAttachment.Version,
        VolumeAttachmentList.Version

    object StorageV1Beta1 : KAPIVersion("storage.k8s.io/v1beta1"),
        VolumeAttributesClass.Version,
        VolumeAttributeClassList.Version

    object StorageMigrationV1Alpha1 : KAPIVersion("storagemigration.k8s.io/v1alpha1"),
        StorageVersionMigration.Version,
        StorageVersionMigrationList.Version
}