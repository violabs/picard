package io.violabs.picard.domain.k8sResources.workload

/**
 * These are basically just label to track useage
 */
interface BaseK8s

interface BaseEnvSource {
    val name: String?
    val optional: Boolean?
}

interface BaseKeySelector {

}

interface BaseNodeAffinity

interface BaseNodeSelector

interface BaseResourceClaim : BaseK8s

interface BaseSecurityContext : BaseK8s

interface BaseSpec : BaseK8s

interface BaseStatus : BaseK8s