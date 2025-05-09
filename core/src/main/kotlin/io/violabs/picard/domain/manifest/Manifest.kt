package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty

data class Manifest(
    val resources: List<ManifestResource>
) {

    class Builder : DSLBuilder<Manifest> {
        private val resources: MutableList<ManifestResource> = mutableListOf()

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequest]
         * * [io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequestList]
         * * [io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundle]
         * * [io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundleList]
         * * [io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview.SelfSubjectReview]
         * * [io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest]
         * * [io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequestList]
         * * [io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReview]
         * * [io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReviewList]
         */
        fun authenticationSection(block: AuthenticationResourceSection.Builder.() -> Unit) {
            resources += AuthenticationResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.authorization.accessReview.LocalSubjectAccessReview]
         * * [io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectAccessReview]
         * * [io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectRulesReview]
         * * [io.violabs.picard.domain.k8sResources.authorization.accessReview.SubjectAccessReview]
         * * [io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole]
         * * [io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRoleList]
         * * [io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBinding]
         * * [io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBindingList]
         * * [io.violabs.picard.domain.k8sResources.authorization.role.Role]
         * * [io.violabs.picard.domain.k8sResources.authorization.role.RoleList]
         * * [io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBinding]
         * * [io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBindingList]
         */
        fun authorizationSection(block: AuthorizationResourceSection.Builder.() -> Unit) {
            resources += AuthorizationResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.cluster.apiService.APIService]
         * * [io.violabs.picard.domain.k8sResources.cluster.apiService.APIServiceList]
         * * [io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatus]
         * * [io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatusList]
         * * [io.violabs.picard.domain.k8sResources.cluster.event.Event]
         * * [io.violabs.picard.domain.k8sResources.cluster.event.EventList]
         * * [io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddress]
         * * [io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddressList]
         * * [io.violabs.picard.domain.k8sResources.cluster.lease.Lease]
         * * [io.violabs.picard.domain.k8sResources.cluster.lease.LeaseList]
         * * [io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidate]
         * * [io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidateList]
         * * [io.violabs.picard.domain.k8sResources.cluster.namespace.Namespace]
         * * [io.violabs.picard.domain.k8sResources.cluster.namespace.NamespaceList]
         * * [io.violabs.picard.domain.k8sResources.cluster.node.Node]
         * * [io.violabs.picard.domain.k8sResources.cluster.node.NodeList]
         * * [io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClass]
         * * [io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClassList]
         * * [io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDR]
         * * [io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDRList]
         */
        fun clusterSection(block: ClusterResourceSection.Builder.() -> Unit) {
            resources += ClusterResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap]
         * * [io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList]
         * * [io.violabs.picard.domain.k8sResources.config.secret.Secret]
         * * [io.violabs.picard.domain.k8sResources.config.secret.SecretList]
         */
        fun configSection(block: ConfigResourceSection.Builder.() -> Unit) {
            resources += ConfigResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinition]
         * * [io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinitionList]
         * * [io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClass]
         * * [io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClassList]
         * * [io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration]
         * * [io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfigurationList]
         * * [io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfiguration]
         * * [io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfigurationList]
         */
        fun extendSection(block: ExtendResourceSection.Builder.() -> Unit) {
            resources += ExtendResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema]
         * * [io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchemaList]
         * * [io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRange]
         * * [io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRangeList]
         * * [io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicy]
         * * [io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicyList]
         * * [io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudget]
         * * [io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudgetList]
         * * [io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfiguration]
         * * [io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationList]
         * * [io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuota]
         * * [io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuotaList]
         * * [io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicy]
         * * [io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicyList]
         * * [io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBinding]
         * * [io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBindingList]
         */
        fun policySection(block: PolicyResourceSection.Builder.() -> Unit) {
            resources += PolicyResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.service.endpoints.Endpoints]
         * * [io.violabs.picard.domain.k8sResources.service.endpoints.EndpointsList]
         * * [io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSlice]
         * * [io.violabs.picard.domain.k8sResources.service.endpointSlice.EndpointSliceList]
         * * [io.violabs.picard.domain.k8sResources.service.ingress.Ingress]
         * * [io.violabs.picard.domain.k8sResources.service.ingress.IngressList]
         * * [io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClass]
         * * [io.violabs.picard.domain.k8sResources.service.ingressClass.IngressClassList]
         * * [io.violabs.picard.domain.k8sResources.service.Service]
         * * [io.violabs.picard.domain.k8sResources.service.ServiceList]
         */
        fun serviceSection(block: ServiceResourceSection.Builder.() -> Unit) {
            resources += ServiceResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriver]
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiDriver.CSIDriverList]
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINode]
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiNode.CSINodeList]
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacity]
         * * [io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity.CSIStorageCapacityList]
         * * [io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaim]
         * * [io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim.PersistentVolumeClaimList]
         * * [io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolume]
         * * [io.violabs.picard.domain.k8sResources.storage.persistentVolume.PersistentVolumeList]
         * * [io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClass]
         * * [io.violabs.picard.domain.k8sResources.storage.storageClass.StorageClassList]
         * * [io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigration]
         * * [io.violabs.picard.domain.k8sResources.storage.storageVersionMigration.StorageVersionMigrationList]
         * * [io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachment]
         * * [io.violabs.picard.domain.k8sResources.storage.volumeAttachment.VolumeAttachmentList]
         * * [io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClass]
         * * [io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass.VolumeAttributesClassList]
         */
        fun storageSection(block: StorageResourceSection.Builder.() -> Unit) {
            resources += StorageResourceSection.Builder().apply(block).build()
        }

        /**
         * Contains following:
         * * [io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevision]
         * * [io.violabs.picard.domain.k8sResources.workload.controllerRevision.ControllerRevisionList]
         * * [io.violabs.picard.domain.k8sResources.workload.cronJob.CronJob]
         * * [io.violabs.picard.domain.k8sResources.workload.cronJob.CronJobList]
         * * [io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSet]
         * * [io.violabs.picard.domain.k8sResources.workload.daemonSet.DaemonSetList]
         * * [io.violabs.picard.domain.k8sResources.workload.deployment.Deployment]
         * * [io.violabs.picard.domain.k8sResources.workload.deployment.DeploymentList]
         * * [io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscaler]
         * * [io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler.HorizontalPodAutoscalerList]
         * * [io.violabs.picard.domain.k8sResources.workload.job.Job]
         * * [io.violabs.picard.domain.k8sResources.workload.job.JobList]
         * * [io.violabs.picard.domain.k8sResources.workload.pod.Pod]
         * * [io.violabs.picard.domain.k8sResources.workload.pod.PodList]
         * * [io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate]
         * * [io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplateList]
         * * [io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSet]
         * * [io.violabs.picard.domain.k8sResources.workload.replicaSet.ReplicaSetList]
         * * [io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationController]
         * * [io.violabs.picard.domain.k8sResources.workload.replicationController.ReplicationControllerList]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaimList]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplate]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate.ResourceClaimTemplateList]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSlice]
         * * [io.violabs.picard.domain.k8sResources.workload.resourceSlice.ResourceSliceList]
         * * [io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSet]
         * * [io.violabs.picard.domain.k8sResources.workload.statefulSet.StatefulSetList]
         */
        fun workloadSection(block: WorkloadResourceSection.Builder.() -> Unit) {
            resources += WorkloadResourceSection.Builder().apply(block).build()
        }

        override fun build(): Manifest {
            return Manifest(
                resources = vRequireNotEmpty(this::resources)
            )
        }
    }
}