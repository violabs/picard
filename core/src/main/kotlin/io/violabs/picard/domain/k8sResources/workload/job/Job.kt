package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.K8sResource

class Job(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<Job.Version> {
    interface Version : APIVersion
}