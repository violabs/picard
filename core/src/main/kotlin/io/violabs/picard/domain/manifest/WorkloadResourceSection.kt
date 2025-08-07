package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobDslBuilder
import io.violabs.picard.v2.resources.workload.batch.cron.CronJobDslBuilderScope
import io.violabs.picard.v2.resources.workload.batch.job.JobDslBuilder
import io.violabs.picard.v2.resources.workload.batch.job.JobDslBuilderScope
import io.violabs.picard.v2.resources.workload.binding.BindingDslBuilder
import io.violabs.picard.v2.resources.workload.binding.BindingDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerDslBuilder
import io.violabs.picard.v2.resources.workload.controller.replication.ReplicationControllerDslBuilderScope
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionDslBuilder
import io.violabs.picard.v2.resources.workload.controller.revision.ControllerRevisionDslBuilderScope
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetDslBuilder
import io.violabs.picard.v2.resources.workload.daemon.DaemonSetDslBuilderScope
import io.violabs.picard.v2.resources.workload.deployment.DeploymentDslBuilder
import io.violabs.picard.v2.resources.workload.deployment.DeploymentDslBuilderScope
import io.violabs.picard.v2.resources.workload.deployment.DeploymentListDslBuilder
import io.violabs.picard.v2.resources.workload.deployment.DeploymentListDslBuilderScope
import io.violabs.picard.v2.resources.workload.pod.PodDslBuilder
import io.violabs.picard.v2.resources.workload.pod.PodDslBuilderScope
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateV2DslBuilder
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateV2DslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimDslBuilder
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimDslBuilderScope
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceDslBuilder
import io.violabs.picard.v2.resources.workload.resource.slice.ResourceSliceDslBuilderScope
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassDslBuilder
import io.violabs.picard.v2.resources.workload.scheduling.PriorityClassDslBuilderScope
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSetDslBuilder
import io.violabs.picard.v2.resources.workload.set.replica.ReplicaSetDslBuilderScope
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetDslBuilder
import io.violabs.picard.v2.resources.workload.set.stateful.StatefulSetDslBuilderScope

interface WorkloadResource<T : APIVersion, META> : K8sResource<T, META>
interface WorkloadListResource<T : APIVersion, E> : K8sListResource<T, E>

data class WorkloadResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<WorkloadResource<*, *>> = mutableListOf(),
        private val lists: MutableList<WorkloadListResource<*, *>> = mutableListOf()
    ) : DslBuilder<WorkloadResourceSection> {

        fun controllerRevision(block: ControllerRevisionDslBuilderScope) {
            val controllerRevision = ControllerRevisionDslBuilder().apply(block).build()
            resources.add(controllerRevision)
        }

        fun cronJob(block: CronJobDslBuilderScope) {
            val cronJob = CronJobDslBuilder().apply(block).build()
            resources.add(cronJob)
        }

        fun daemonSet(block: DaemonSetDslBuilderScope) {
            val daemonSet = DaemonSetDslBuilder().apply(block).build()
            resources.add(daemonSet)
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

        fun job(block: JobDslBuilderScope) {
            val job = JobDslBuilder().apply(block).build()
            resources.add(job)
        }

        fun pod(block: PodDslBuilderScope) {
            val pod = PodDslBuilder().apply(block).build()
            resources.add(pod)
        }

        fun podTemplate(block: PodTemplateV2DslBuilderScope) {
            val podTemplate = PodTemplateV2DslBuilder().apply(block).build()
            resources.add(podTemplate)
        }

        fun priorityClass(block: PriorityClassDslBuilderScope) {
            val priorityClass = PriorityClassDslBuilder().apply(block).build()
            resources.add(priorityClass)
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

        fun resourceSlice(block: ResourceSliceDslBuilderScope) {
            val resourceSlice = ResourceSliceDslBuilder().apply(block).build()
            resources.add(resourceSlice)
        }

        fun statefulSet(block: StatefulSetDslBuilderScope) {
            val statefulSet = StatefulSetDslBuilder().apply(block).build()
            resources.add(statefulSet)
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