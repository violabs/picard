package io.violabs.picard.v2.resources.authentication.certificate.trust.bundle

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/cluster-trust-bundle-v1beta1/
 *
 * ClusterTrustBundle is a cluster-scoped container for X.509 trust anchors (root certificates).
 *
 * ClusterTrustBundle objects are considered to be readable by any authenticated
 * user in the cluster, because they can be mounted by pods using the clusterTrustBundle
 * projection. All service accounts have read access to ClusterTrustBundles by default.
 * Users who only have namespace-level access to a cluster can read ClusterTrustBundles by
 * impersonating a serviceaccount that they have access to.
 *
 * It can be optionally associated with a particular assigner, in which case it contains
 * one valid set of trust anchors for that signer. Signers may have multiple associated
 * ClusterTrustBundles; each is an independent set of trust anchors for that signer.
 * Admission control is used to enforce that only users with permissions on the signer
 * can create or modify the corresponding bundle.
 */
@GeneratedDsl
data class ClusterTrustBundleV2(
    @DefaultValue(
        "KAPIVersion.CertificatesV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CertificatesV1Beta1,
    /**
     * metadata contains the object metadata.
     */
    override val metadata: ObjectMeta? = null,
    /**
     * spec contains the signer (if any) and trust anchors.
     */
    val spec: ClusterTrustBundleSpec
) : AuthenticationResource<ClusterTrustBundleV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

