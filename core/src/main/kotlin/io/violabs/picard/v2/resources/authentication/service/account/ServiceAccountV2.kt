package io.violabs.picard.v2.resources.authentication.service.account

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.LocalObjectReference
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.common.ObjectReference

/**
 * ServiceAccount binds together: * a name, understood by users, and perhaps
 * by peripheral systems, for an identity * a principal that can be
 * authenticated and authorized * a set of secrets.
 *
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/service-account-v1/
 */
@GeneratedDsl
data class ServiceAccountV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * AutomountServiceAccountToken indicates whether pods running as this service
     * account should have an API token automatically mounted. Can be overridden at the pod level.
     */
    val automountServiceAccountToken: Boolean? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * ImagePullSecrets is a list of references to secrets in the same namespace to
     * use for pulling any images in pods that reference this ServiceAccount.
     * ImagePullSecrets are distinct from Secrets because Secrets can be mounted in
     * the pod, but ImagePullSecrets are only accessed by the kubelet.
     * More info: https://kubernetes.io/docs/concepts/containers/images/#specifying-imagepullsecrets-on-a-pod
     */
    val imagePullSecrets: List<LocalObjectReference>? = null,
    /**
     * Patch strategy: merge on key name
     *
     * Map: unique values on key name will be kept during a merge
     *
     * Secrets is a list of the secrets in the same namespace that pods running
     * using this ServiceAccount are allowed to use. Pods are only limited to this
     * list if this service account has a "kubernetes.io/enforce-mountable-secrets"
     * annotation set to "true". The "kubernetes.io/enforce-mountable-secrets" annotation
     * is deprecated since v1.32. Prefer separate namespaces to isolate access to mounted
     * secrets. This field should not be used to find auto-generated service account
     * token secrets for use outside of pods. Instead, tokens can be requested directly
     * using the TokenRequest API, or service account token secrets can be manually
     * created. More info: https://kubernetes.io/docs/concepts/configuration/secret
     */
    val secrets: List<ObjectReference>? = null
) : AuthenticationResource<ServiceAccountV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

