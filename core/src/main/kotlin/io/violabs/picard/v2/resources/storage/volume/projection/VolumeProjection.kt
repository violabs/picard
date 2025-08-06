package io.violabs.picard.v2.resources.storage.volume.projection

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class VolumeProjection(
    /**
     * ClusterTrustBundle allows a pod to access the .spec.trustBundle
     * field of ClusterTrustBundle objects in an auto-updating file.
     *
     * Alpha, gated by the ClusterTrustBundleProjection feature gate.
     *
     * ClusterTrustBundle objects can either be selected by name, or by the
     * combination of signer name and a label selector.
     *
     * Kubelet performs aggressive normalization of the PEM contents written into
     * the pod filesystem. Esoteric PEM features such as inter-block comments
     * and block headers are stripped. Certificates are deduplicated. The ordering
     * of certificates within the file is arbitrary, and Kubelet may change the
     * order over time.
     *
     * ClusterTrustBundleProjection describes how to select a set of ClusterTrustBundle
     * objects and project their contents into the pod filesystem.
     */
    val clusterTrustBundle: ClusterTrustBundleProjection? = null,
    /**
     * configMap information about the configMap data to project
     *
     * *Adapts a ConfigMap into a projected volume.
     *
     * The contents of the target ConfigMap's Data field will be presented in a projected
     * volume as files using the keys in the Data field as the file names, unless the items
     * element is populated with specific mappings of keys to paths. Note that this is identical
     * to a configmap volume source without the default mode.*
     */
    val configMap: ConfigMapProjection? = null,
    /**
     * downwardAPI information about the downwardAPI data to project
     *
     * Represents downward API info for projecting into a projected volume. Note that this
     * is identical to a downwardAPI volume source without the default mode.
     */
    val downwardApi: DownwardApiProjection? = null,
    /**
     * secret information about the secret data to project
     *
     * *Adapts a secret into a projected volume.
     *
     * The contents of the target Secret's Data field will be presented in a
     * projected volume as files using the keys in the Data field as the file names. Note that
     * this is identical to a secret volume source without the default mode.*
     */
    val secret: SecretProjection? = null,
    /**
     * serviceAccountToken is information about the serviceAccountToken data to project
     *
     * ServiceAccountTokenProjection represents a projected service account token volume. This projection can be used to insert a service account token into the pods runtime filesystem for use against APIs (Kubernetes API Server or otherwise).
     */
    val serviceAccountToken: ServiceAccountTokenProjection? = null
)