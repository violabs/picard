package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource

interface WorkloadResource<T : APIVersion, META> : K8sResource<T, META>
interface WorkloadListResource<T : APIVersion, E> : K8sListResource<T, E>

data class WorkloadResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<WorkloadResource<*, *>> = mutableListOf(),
        private val lists: MutableList<WorkloadListResource<*, *>> = mutableListOf()
    ) : DslBuilder<WorkloadResourceSection> {

//        fun controllerRevision(block: ControllerRevision.Builder.() -> Unit) {
//            resources += ControllerRevision.Builder().apply(block).build()
//        }
//
//        fun controllerRevisionList(block: ControllerRevisionList.Builder.() -> Unit) {
//            lists += ControllerRevisionList.Builder().apply(block).build()
//        }
//
//        fun cronJob(block: CronJob.Builder.() -> Unit) {
//            resources += CronJob.Builder().apply(block).build()
//        }
//
//        fun cronJobList(block: CronJobList.Builder.() -> Unit) {
//            lists += CronJobList.Builder().apply(block).build()
//        }
//
//        fun daemonSet(block: DaemonSet.Builder.() -> Unit) {
//            resources += DaemonSet.Builder().apply(block).build()
//        }
//
//        fun daemonSetList(block: DaemonSetList.Builder.() -> Unit) {
//            lists += DaemonSetList.Builder().apply(block).build()
//        }
//
//        fun deployment(block: Deployment.Builder.() -> Unit) {
//            resources += Deployment.Builder().apply(block).build()
//        }
//
//        fun deploymentList(block: DeploymentList.Builder.() -> Unit) {
//            lists += DeploymentList.Builder().apply(block).build()
//        }
//
//        fun horizontalPodAutoscaler(block: HorizontalPodAutoscaler.Builder.() -> Unit) {
//            resources += HorizontalPodAutoscaler.Builder().apply(block).build()
//        }
//
//        fun horizontalPodAutoscalerList(block: HorizontalPodAutoscalerList.Builder.() -> Unit) {
//            lists += HorizontalPodAutoscalerList.Builder().apply(block).build()
//        }
//
//        fun job(block: Job.Builder.() -> Unit) {
//            resources += Job.Builder().apply(block).build()
//        }
//
//        fun jobList(block: JobList.Builder.() -> Unit) {
//            lists += JobList.Builder().apply(block).build()
//        }
//
//        fun pod(block: Pod.Builder.() -> Unit) {
//            resources += Pod.Builder().apply(block).build()
//        }
//
//        fun podList(block: PodList.Builder.() -> Unit) {
//            lists += PodList.Builder().apply(block).build()
//        }
//
//        fun podTemplate(block: PodTemplate.Builder.() -> Unit) {
//            resources += PodTemplate.Builder().apply(block).build()
//        }
//
//        fun podTemplateList(block: PodTemplateList.Builder.() -> Unit) {
//            lists += PodTemplateList.Builder().apply(block).build()
//        }
//
//        fun priorityClass(block: PriorityClass.Builder.() -> Unit) {
//            resources += PriorityClass.Builder().apply(block).build()
//        }
//
//        fun priorityClassList(block: PriorityClassList.Builder.() -> Unit) {
//            lists += PriorityClassList.Builder().apply(block).build()
//        }
//
//        fun replicaSet(block: ReplicaSet.Builder.() -> Unit) {
//            resources += ReplicaSet.Builder().apply(block).build()
//        }
//
//        fun replicaSetList(block: ReplicaSetList.Builder.() -> Unit) {
//            lists += ReplicaSetList.Builder().apply(block).build()
//        }
//
//        fun replicationController(block: ReplicationController.Builder.() -> Unit) {
//            resources += ReplicationController.Builder().apply(block).build()
//        }
//
//        fun replicationControllerList(block: ReplicationControllerList.Builder.() -> Unit) {
//            lists += ReplicationControllerList.Builder().apply(block).build()
//        }
//
//        fun resourceClaim(block: ResourceClaim.Builder.() -> Unit) {
//            resources += ResourceClaim.Builder().apply(block).build()
//        }
//
//        fun resourceClaimList(block: ResourceClaimList.Builder.() -> Unit) {
//            lists += ResourceClaimList.Builder().apply(block).build()
//        }
//
//        fun resourceClaimTemplate(block: ResourceClaimTemplate.Builder.() -> Unit) {
//            resources += ResourceClaimTemplate.Builder().apply(block).build()
//        }
//
//        fun resourceClaimTemplateList(block: ResourceClaimTemplateList.Builder.() -> Unit) {
//            lists += ResourceClaimTemplateList.Builder().apply(block).build()
//        }
//
//        fun resourceSlice(block: ResourceSlice.Builder.() -> Unit) {
//            resources += ResourceSlice.Builder().apply(block).build()
//        }
//
//        fun resourceSliceList(block: ResourceSliceList.Builder.() -> Unit) {
//            lists += ResourceSliceList.Builder().apply(block).build()
//        }
//
//        fun statefulSet(block: StatefulSet.Builder.() -> Unit) {
//            resources += StatefulSet.Builder().apply(block).build()
//        }
//
//        fun statefulSetList(block: StatefulSetList.Builder.() -> Unit) {
//            lists += StatefulSetList.Builder().apply(block).build()
//        }


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