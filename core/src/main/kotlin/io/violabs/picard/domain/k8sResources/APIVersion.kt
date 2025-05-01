package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview.SelfSubjectReview
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundle
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundleList
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequest
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequestList
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReview
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReviewList
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequestList
import io.violabs.picard.domain.k8sResources.authentication.serviceAccount.ServiceAccount
import io.violabs.picard.domain.k8sResources.authentication.serviceAccount.ServiceAccountList
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBinding
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBindingList
import io.violabs.picard.domain.k8sResources.authorization.role.Role
import io.violabs.picard.domain.k8sResources.authorization.role.RoleList
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBinding
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBindingList
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRoleList
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectRulesReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.LocalSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SubjectAccessReview
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
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolumeList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaimList
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim
import io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolume
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacity
import io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacityList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINodeList
import io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINode
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriverList
import io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriver
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfigurationList
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfigurationList
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClass
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClassList
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinition
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinitionList
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBinding
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBindingList
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicy
import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicyList
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfiguration
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationList
import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudget
import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudgetList
import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicy
import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicyList
import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuota
import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuotaList
import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRange
import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRangeList
import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema
import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchemaList
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
import io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachment
import io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachmentList
import io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClassList
import io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClass
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
        LimitRange.Version,
        LimitRangeList.Version,
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
        ResourceQuota.Version,
        ResourceQuotaList.Version,
        Secret.Version,
        SecretList.Version,
        Service.Version,
        ServiceList.Version,
        ServiceAccount.Version,
        ServiceAccountList.Version

    object AdmissionRegistrationV1 : KAPIVersion("admissionregistration.k8s.io/v1"),
        MutatingWebhookConfiguration.Version,
        MutatingWebhookConfigurationList.Version,
        ValidatingAdmissionPolicy.Version,
        ValidatingAdmissionPolicyList.Version,
        ValidatingAdmissionPolicyBinding.Version,
        ValidatingAdmissionPolicyBindingList.Version,
        ValidatingWebhookConfiguration.Version,
        ValidatingWebhookConfigurationList.Version

    object APIExtensionsV1 : KAPIVersion("apiextensions.k8s.io/v1"),
        CustomResourceDefinition.Version,
        CustomResourceDefinitionList.Version

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

    object AuthenticationV1 : KAPIVersion("authentication.k8s.io/v1"),
        TokenRequest.Version,
        TokenRequestList.Version,
        TokenReview.Version,
        TokenReviewList.Version,
        SelfSubjectReview.Version

    object AuthorizationV1 : KAPIVersion("authorization.k8s.io/v1"),
        LocalSubjectAccessReview.Version,
        SelfSubjectAccessReview.Version,
        SelfSubjectRulesReview.Version,
        SubjectAccessReview.Version

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

    object CertificatesV1 : KAPIVersion("certificates.k8s.io/v1"),
        CertificateSigningRequest.Version,
        CertificateSigningRequestList.Version

    object CertificatesV1Alpha1 : KAPIVersion("certificates.k8s.io/v1alpha1"),
        ClusterTrustBundle.Version,
        ClusterTrustBundleList.Version

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

    object FlowControlApiServerV1 : KAPIVersion("flowcontrol.apiserver.k8s.io/v1"),
        FlowSchema.Version,
        FlowSchemaList.Version,
        PriorityLevelConfiguration.Version,
        PriorityLevelConfigurationList.Version

    object NetworkingV1 : KAPIVersion("networking.k8s.io/v1"),
        Ingress.Version,
        IngressList.Version,
        IngressClass.Version,
        IngressClassList.Version,
        NetworkPolicy.Version,
        NetworkPolicyList.Version

    object NetworkingV1Beta1 : KAPIVersion("networking.k8s.io/v1"),
        IPAddress.Version,
        IPAddressList.Version,
        ServiceCIDR.Version,
        ServiceCIDRList.Version

    object NodeV1 : KAPIVersion("node.k8s.io/v1"),
        RuntimeClass.Version,
        RuntimeClassList.Version

    object PolicyV1 : KAPIVersion("policy/v1"),
        PodDisruptionBudget.Version,
        PodDisruptionBudgetList.Version

    object RBACAuthorizationV1 : KAPIVersion("rbac.authorization.k8s.io/v1"),
        ClusterRole.Version,
        ClusterRoleList.Version,
        ClusterRoleBinding.Version,
        ClusterRoleBindingList.Version,
        Role.Version,
        RoleList.Version,
        RoleBinding.Version,
        RoleBindingList.Version

    object ResourceV1Alpha3 : KAPIVersion("resource.k8s.io/v1alpha3"),
        DeviceClass.Version,
        DeviceClassList.Version,
        PodSchedulingContext.Version,
        PodSchedulingContextList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version

    object ResourceV1Beta1 : KAPIVersion("resource.k8s.io/v1beta1"),
        DeviceClass.Version,
        DeviceClassList.Version,
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
        VolumeAttributesClassList.Version

    object StorageMigrationV1Alpha1 : KAPIVersion("storagemigration.k8s.io/v1alpha1"),
        StorageVersionMigration.Version,
        StorageVersionMigrationList.Version
}