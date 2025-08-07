package io.violabs.picard.domain.k8sResources

import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestList
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequest
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundleList
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundle
import io.violabs.picard.v2.resources.authentication.self.SelfSubjectReview
import io.violabs.picard.v2.resources.authentication.service.account.ServiceAccountList
import io.violabs.picard.v2.resources.authentication.service.account.ServiceAccount
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestList
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequest
import io.violabs.picard.v2.resources.authentication.token.review.TokenReviewList
import io.violabs.picard.v2.resources.authentication.token.review.TokenReview
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReview
import io.violabs.picard.v2.resources.authorization.review.access.subject.local.LocalSubjectAccessReview
import io.violabs.picard.v2.resources.authorization.review.access.subject.self.SelfSubjectAccessReview
import io.violabs.picard.v2.resources.authorization.review.rules.SelfSubjectRulesReview
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleList
import io.violabs.picard.v2.resources.authorization.role.ClusterRole
import io.violabs.picard.v2.resources.authorization.role.Role
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBinding
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBinding
import io.violabs.picard.v2.resources.config.map.ConfigMap
import io.violabs.picard.v2.resources.config.secret.Secret
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuota
import io.violabs.picard.v2.resources.policy.limit.LimitRange
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchema
import io.violabs.picard.v2.resources.policy.network.NetworkPolicy
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudget
import io.violabs.picard.v2.resources.storage.StorageClass
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigration
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaim
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriver
import io.violabs.picard.v2.resources.storage.csi.node.CsiNode
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacity
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolume
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClass
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachment
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfiguration
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicy
import io.violabs.picard.v2.resources.policy.admission.validating.binding.ValidatingAdmissionPolicyBinding
import io.violabs.picard.v2.resources.policy.admission.mutating.MutatingAdmissionPolicy
import io.violabs.picard.v2.resources.policy.admission.mutating.binding.MutatingAdmissionPolicyBinding
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinition
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClass
import io.violabs.picard.v2.resources.extend.webhook.mutating.MutatingWebhookConfiguration
import io.violabs.picard.v2.resources.extend.webhook.validating.ValidatingWebhookConfiguration
import io.violabs.picard.v2.resources.cluster.service.api.ApiService
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatus
import io.violabs.picard.v2.resources.cluster.event.Event
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddress
import io.violabs.picard.v2.resources.cluster.lease.Lease
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidate
import io.violabs.picard.v2.resources.cluster.namespace.Namespace
import io.violabs.picard.v2.resources.cluster.node.Node
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClass
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidr
import io.violabs.picard.v2.resources.service.Service
import io.violabs.picard.v2.resources.service.ServiceList
import io.violabs.picard.v2.resources.service.endpoints.Endpoints
import io.violabs.picard.v2.resources.service.endpointslice.EndpointSlice
import io.violabs.picard.v2.resources.service.ingress.Ingress
import io.violabs.picard.v2.resources.service.ingressclass.IngressClass
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSlice
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaim
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplate
import io.violabs.picard.v2.resources.workload.resource.device.taint.DeviceTaintRule
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClass
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscaler
import io.violabs.picard.v2.resources.workload.batch.cron.CronJob
import io.violabs.picard.v2.resources.workload.batch.job.Job
import io.violabs.picard.v2.resources.workload.daemon.DaemonSet
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevision
import io.violabs.picard.v2.resources.workload.deployment.Deployment
import io.violabs.picard.v2.resources.workload.binding.Binding
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationController
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSet
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSet
import io.violabs.picard.v2.resources.workload.pod.Pod
import io.violabs.picard.v2.resources.cluster.ipaddress.IpAddressList
import io.violabs.picard.v2.resources.cluster.runtimeclass.RuntimeClassList
import io.violabs.picard.v2.resources.cluster.namespace.NamespaceList
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateList
import io.violabs.picard.v2.resources.cluster.lease.LeaseList
import io.violabs.picard.v2.resources.cluster.node.NodeList
import io.violabs.picard.v2.resources.cluster.event.EventList
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassList
import io.violabs.picard.v2.resources.config.secret.SecretList
import io.violabs.picard.v2.resources.workload.deployment.DeploymentList
import io.violabs.picard.v2.resources.service.endpoints.EndpointsList
import io.violabs.picard.v2.resources.service.ingressclass.IngressClassList
import io.violabs.picard.v2.resources.service.ingress.IngressList
import io.violabs.picard.v2.resources.service.endpointslice.EndpointSliceList
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBindingList
import io.violabs.picard.v2.resources.authorization.role.RoleList
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBindingList
import io.violabs.picard.v2.resources.cluster.component.status.ComponentStatusList
import io.violabs.picard.v2.resources.cluster.service.api.ApiServiceList
import io.violabs.picard.v2.resources.cluster.service.cidr.ServiceCidrList
import io.violabs.picard.v2.resources.config.map.ConfigMapList
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionList
import io.violabs.picard.v2.resources.extend.webhook.mutating.MutatingWebhookConfigurationList
import io.violabs.picard.v2.resources.extend.webhook.validating.ValidatingWebhookConfigurationList
import io.violabs.picard.v2.resources.policy.admission.validating.binding.ValidatingAdmissionPolicyBindingList
import io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyList
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetList
import io.violabs.picard.v2.resources.policy.level.PriorityLevelConfigurationList
import io.violabs.picard.v2.resources.policy.limit.LimitRangeList
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyList
import io.violabs.picard.v2.resources.policy.resource.quota.ResourceQuotaList
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaList
import io.violabs.picard.v2.resources.storage.StorageClassList
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverList
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeList
import io.violabs.picard.v2.resources.storage.csi.storage.capacity.CsiStorageCapacityList
import io.violabs.picard.v2.resources.storage.persistent.volume.PersistentVolumeList
import io.violabs.picard.v2.resources.storage.persistent.volume.claim.PersistentVolumeClaimList
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationList
import io.violabs.picard.v2.resources.storage.volume.VolumeAttributesClassList
import io.violabs.picard.v2.resources.storage.volume.attachment.VolumeAttachmentList
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerList
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobList
import io.violabs.picard.v2.resources.workload.batch.job.JobList
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerList
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionList
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetList
import io.violabs.picard.v2.resources.workload.pod.PodList
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateListV2
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateV2
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimList
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateList
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceList
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassList
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSetList
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetList

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
        Binding.Version,
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
        PodTemplateV2.Version,
        PodTemplateListV2.Version,
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

    object AdmissionRegistrationV1Alpha1 : KAPIVersion("admissionregistration.k8s.io/v1alpha1"),
        MutatingAdmissionPolicy.Version,
        MutatingAdmissionPolicyBinding.Version

    object APIExtensionsV1 : KAPIVersion("apiextensions.k8s.io/v1"),
        CustomResourceDefinition.Version,
        CustomResourceDefinitionList.Version

    object APIRegistrationV1 : KAPIVersion("apiregistration.k8s.io/v1"),
        ApiService.Version,
        ApiServiceList.Version

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

    object CertificatesV1Beta1 : KAPIVersion("certificates.k8s.io/v1beta1"),
        ClusterTrustBundle.Version,
        ClusterTrustBundleList.Version

    object CoordinationV1 : KAPIVersion("coordination.k8s.io/v1"),
        Lease.Version,
        LeaseList.Version

    object CoordinationV1Alpha1 : KAPIVersion("coordination.k8s.io/v1alpha1"),
        LeaseCandidate.Version,
        LeaseCandidateList.Version

    object CoordinationV1Beta1 : KAPIVersion("coordination.k8s.io/v1beta1"),
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
        NetworkPolicyList.Version,
        ServiceCidr.Version,
        ServiceCidrList.Version

    object NetworkingV1Beta1 : KAPIVersion("networking.k8s.io/v1beta1"),
        IpAddress.Version,
        IpAddressList.Version,
        ServiceCidr.Version,
        ServiceCidrList.Version

    object NodeV1 : KAPIVersion("node.k8s.io/v1"),
        RuntimeClass.Version,
        RuntimeClassList.Version

    object PolicyV1 : KAPIVersion("policy/v1"),
        PodDisruptionBudget.Version,
        PodDisruptionBudgetList.Version

    object RbacAuthorizationV1 : KAPIVersion("rbac.authorization.k8s.io/v1"),
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
        DeviceTaintRule.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version

    object ResourceV1Beta1 : KAPIVersion("resource.k8s.io/v1beta1"),
        DeviceClass.Version,
        DeviceClassList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version

    object ResourceV1Beta2 : KAPIVersion("resource.k8s.io/v1beta2"),
        DeviceClass.Version,
        DeviceClassList.Version,
        ResourceSlice.Version,
        ResourceSliceList.Version,
        ResourceClaim.Version,
        ResourceClaimList.Version,
        ResourceClaimTemplate.Version,
        ResourceClaimTemplateList.Version

    object SchedulingV1 : KAPIVersion("scheduling.k8s.io/v1"),
        PriorityClass.Version,
        PriorityClassList.Version

    object StorageV1 : KAPIVersion("storage.k8s.io/v1"),
        CsiDriver.Version,
        CsiDriverList.Version,
        CsiNode.Version,
        CsiNodeList.Version,
        CsiStorageCapacity.Version,
        CsiStorageCapacityList.Version,
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