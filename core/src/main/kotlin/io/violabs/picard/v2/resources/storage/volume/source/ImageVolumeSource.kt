package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ImageVolumeSource represents a image volume resource.
 */
@GeneratedDsl
data class ImageVolumeSource(
    /**
     * Policy for pulling OCI objects. Possible values are: Always: the kubelet always attempts to
     * pull the reference. Container creation will fail If the pull fails. Never: the kubelet
     * never pulls the reference and only uses a local image or artifact. Container creation will
     * fail if the reference isn't present. IfNotPresent: the kubelet pulls if the reference isn't
     * already present on disk. Container creation will fail if the reference isn't present and the
     * pull fails. Defaults to Always if :latest tag is specified, or IfNotPresent otherwise.
     */
    val imagePullPolicy: String? = null,
    /**
     * Required: Image or artifact reference to be used. Behaves in the same way as
     * pod.spec.containers[*].image. Pull secrets will be assembled in the same way as for the
     * container image by looking up node credentials, SA image pull secrets, and pod spec image pull
     * secrets. More info: https://kubernetes.io/docs/concepts/containers/images This field is
     * optional to allow higher level config management to default or override container images in
     * workload controllers like Deployments and StatefulSets.
     */
    val reference: String? = null
)