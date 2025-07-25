package io.violabs.picard.v2.common

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.APIVersion

/**
 * ObjectReference contains enough information to let you inspect or modify the referred object.
 * import "k8s.io/api/core/v1"
 *
 * ObjectReference contains enough information to let you inspect or modify the referred object.
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/common-definitions/object-reference/#ObjectReference
 */
@GeneratedDsl(withListGroup = true)
data class ObjectReference(
    /**
     * API version of the referent.
     */
    val apiVersion: APIVersion? = null,
    /**
     * If referring to a piece of an object instead of an entire object, this string
     * should contain a valid JSON/Go field access statement, such as desiredState.manifest.containers[2].
     * For example, if the object reference is to a container within a pod, this would take on a
     * value like: "spec.containers{name}" (where "name" refers to the name of the container that
     * triggered the event) or if no container name is specified "spec.containers[2]" (container with
     * index 2 in this pod). This syntax is chosen only to have some well-defined way of referencing a
     * part of an object.
     */
    val fieldPath: String? = null,
    /**
     * Kind of the referent.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds
     */
    val kind: String? = null,
    /**
     * Name of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String? = null,
    /**
     * Namespace of the referent.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces/
     */
    val namespace: String? = null,
    /**
     * Specific resourceVersion to which this reference is made, if any.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#concurrency-control-and-consistency
     */
    val resourceVersion: String? = null,
    /**
     * UID of the referent. More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#uids
     */
    val uid: String? = null
)