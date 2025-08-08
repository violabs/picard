package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerDslBuilderScope
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerListDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerListDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobDslBuilder
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobListDslBuilder
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobListDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.job.JobDslBuilder
import io.violabs.picard.v2.resources.workload.batch.job.JobDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.job.JobListDslBuilder
import io.violabs.picard.v2.resources.workload.batch.job.JobListDslBuilderScope
import io.violabs.picard.v2.resources.workload.binding.BindingDslBuilder
import io.violabs.picard.v2.resources.workload.binding.BindingDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerDslBuilder
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerListDslBuilder
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerListDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionDslBuilder
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionListDslBuilder
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionListDslBuilderScope
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetDslBuilder
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetDslBuilderScope
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetListDslBuilder
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetListDslBuilderScope
import io.violabs.picard.v2.resources.workload.deployment.DeploymentDslBuilder
import io.violabs.picard.v2.resources.workload.deployment.DeploymentDslBuilderScope
import io.violabs.picard.v2.resources.workload.deployment.DeploymentListDslBuilder
import io.violabs.picard.v2.resources.workload.deployment.DeploymentListDslBuilderScope
import io.violabs.picard.v2.resources.workload.pod.PodDslBuilder
import io.violabs.picard.v2.resources.workload.pod.PodDslBuilderScope
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateDslBuilder
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateDslBuilderScope
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateListDslBuilder
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateListDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimListDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimListDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateListDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateListDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceDslBuilder
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceListDslBuilder
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceListDslBuilderScope
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassDslBuilder
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassDslBuilderScope
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassListDslBuilder
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassListDslBuilderScope
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSetDslBuilder
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSetDslBuilderScope
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetDslBuilder
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetDslBuilderScope
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetListDslBuilder
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetListDslBuilderScope

interface WorkloadResource<T : APIVersion, META> : K8sResource<T, META>
interface WorkloadListResource<T : APIVersion, E> : K8sListResource<T, E>

data class WorkloadResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<WorkloadResource<*, *>> = mutableListOf(),
        private val lists: MutableList<WorkloadListResource<*, *>> = mutableListOf()
    ) : DslBuilder<WorkloadResourceSection> {

        fun controllerReplication(block: ReplicationControllerDslBuilderScope) {
            val controllerReplication = ReplicationControllerDslBuilder().apply(block).build()
            resources.add(controllerReplication)
        }

        fun controllerReplicationList(block: ReplicationControllerListDslBuilderScope) {
            val list = ReplicationControllerListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun controllerRevision(block: ControllerRevisionDslBuilderScope) {
            val controllerRevision = ControllerRevisionDslBuilder().apply(block).build()
            resources.add(controllerRevision)
        }

        fun controllerRevisionList(block: ControllerRevisionListDslBuilderScope) {
            val list = ControllerRevisionListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun cronJob(block: CronJobDslBuilderScope) {
            val cronJob = CronJobDslBuilder().apply(block).build()
            resources.add(cronJob)
        }

        fun cronJobList(block: CronJobListDslBuilderScope) {
            val list = CronJobListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun daemonSet(block: DaemonSetDslBuilderScope) {
            val daemonSet = DaemonSetDslBuilder().apply(block).build()
            resources.add(daemonSet)
        }

        fun daemonSetList(block: DaemonSetListDslBuilderScope) {
            val list = DaemonSetListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun deployment(block: DeploymentDslBuilderScope) {
            val deployment = DeploymentDslBuilder().apply(block).build()
            resources.add(deployment)
        }

        fun deploymentList(block: DeploymentListDslBuilderScope) {
            val list = DeploymentListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun horizontalPodAutoscaler(block: HorizontalPodAutoscalerDslBuilderScope) {
            val horizontalPodAutoscaler = HorizontalPodAutoscalerDslBuilder().apply(block).build()
            resources.add(horizontalPodAutoscaler)
        }

        fun horizontalPodAutoscalerList(block: HorizontalPodAutoscalerListDslBuilderScope) {
            val list = HorizontalPodAutoscalerListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun job(block: JobDslBuilderScope) {
            val job = JobDslBuilder().apply(block).build()
            resources.add(job)
        }

        fun jobList(block: JobListDslBuilderScope) {
            val jobList = JobListDslBuilder().apply(block).build()
            lists.add(jobList)
        }

        fun pod(block: PodDslBuilderScope) {
            val pod = PodDslBuilder().apply(block).build()
            resources.add(pod)
        }

        fun podTemplate(block: PodTemplateDslBuilderScope) {
            val podTemplate = PodTemplateDslBuilder().apply(block).build()
            resources.add(podTemplate)
        }

        fun podTemplateList(block: PodTemplateListDslBuilderScope) {
            val podTemplateList = PodTemplateListDslBuilder().apply(block).build()
            lists.add(podTemplateList)
        }

        fun priorityClass(block: PriorityClassDslBuilderScope) {
            val priorityClass = PriorityClassDslBuilder().apply(block).build()
            resources.add(priorityClass)
        }

        fun priorityClassList(block: PriorityClassListDslBuilderScope) {
            val priorityClassList = PriorityClassListDslBuilder().apply(block).build()
            lists.add(priorityClassList)
        }

        fun replicaSet(block: ReplicaSetDslBuilderScope) {
            val replicaSet = ReplicaSetDslBuilder().apply(block).build()
            resources.add(replicaSet)
        }

        fun replicationController(block: ReplicationControllerDslBuilderScope) {
            val replicationController = ReplicationControllerDslBuilder().apply(block).build()
            resources.add(replicationController)
        }

        fun resourceClaim(block: ResourceClaimDslBuilderScope) {
            val resourceClaim = ResourceClaimDslBuilder().apply(block).build()
            resources.add(resourceClaim)
        }

        fun resourceClaimList(block: ResourceClaimListDslBuilderScope) {
            val resourceClaimList = ResourceClaimListDslBuilder().apply(block).build()
            lists.add(resourceClaimList)
        }

        fun resourceClaimTemplate(block: ResourceClaimTemplateDslBuilderScope) {
            val resourceClaimTemplate = ResourceClaimTemplateDslBuilder().apply(block).build()
            resources.add(resourceClaimTemplate)
        }

        fun resourceClaimTemplateList(block: ResourceClaimTemplateListDslBuilderScope) {
            val resourceClaimTemplateList = ResourceClaimTemplateListDslBuilder().apply(block).build()
            lists.add(resourceClaimTemplateList)
        }

        fun resourceSlice(block: ResourceSliceDslBuilderScope) {
            val resourceSlice = ResourceSliceDslBuilder().apply(block).build()
            resources.add(resourceSlice)
        }

        fun resourceSliceList(block: ResourceSliceListDslBuilderScope) {
            val resourceSliceList = ResourceSliceListDslBuilder().apply(block).build()
            lists.add(resourceSliceList)
        }

        fun statefulSet(block: StatefulSetDslBuilderScope) {
            val statefulSet = StatefulSetDslBuilder().apply(block).build()
            resources.add(statefulSet)
        }

        fun statefulSetList(block: StatefulSetListDslBuilderScope) {
            val list = StatefulSetListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun binding(block: BindingDslBuilderScope) {
            val binding = BindingDslBuilder().apply(block).build()
            resources.add(binding)
        }

        override fun build(): WorkloadResourceSection {
            return WorkloadResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}