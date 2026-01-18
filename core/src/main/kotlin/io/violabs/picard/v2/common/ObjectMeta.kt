package io.violabs.picard.v2.common

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.serialization.RetainQuotesMapSerializer

/**
 * "k8s.io/apimachinery/pkg/apis/meta/v1"
 *
 * ObjectMeta is metadata that all persisted resources must have, which includes all
 * objects users must create.
 */
@GeneratedDsl
data class ObjectMeta(
    /**
     * must be unique within a namespace. Is required when creating resources, although some
     * resources may allow a client to request the generation of an appropriate name automatically.
     * Name is primarily intended for creation idempotence and configuration definition. Cannot be
     * updated. More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names#names
     */
    val name: String? = null,
    /**
     * An optional prefix, used by the server, to generate a unique name ONLY IF the Name
     * field has not been provided. If this field is used, the name returned to the client will
     * be different than the name passed. This value will also be combined with a unique suffix.
     * The provided value has the same validation rules as the Name field, and may be truncated
     * by the length of the suffix required to make the value unique on the server.
     *
     * If this field is specified and the generated name exists, the server will return a 409.
     *
     * Applied only if Name is not specified. More info:
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#idempotency
     */
    val generateName: String? = null,
    /**
     * Defines the space within which each name must be unique. An empty namespace is equivalent
     * to the "default" namespace, but "default" is the canonical representation. Not all objects
     * are required to be scoped to a namespace - the value of this field for those objects will be empty.
     *
     * Must be a DNS_LABEL. Cannot be updated. More info:
     * https://kubernetes.io/docs/concepts/overview/working-with-objects/namespaces
     */
    val namespace: String? = null,
    /**
     * Map of string keys and values that can be used to organize and categorize
     * (scope and select) objects. May match selectors of replication controllers and services.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/labels
     */
    @JsonSerialize(using = RetainQuotesMapSerializer::class)
    val labels: Map<String, String>? = null,
    /**
     * Annotations is an unstructured key value map stored with a resource that may be set by
     * external tools to store and retrieve arbitrary metadata. They are not queryable and should
     * be preserved when modifying objects. More info:
     * https://kubernetes.io/docs/concepts/overview/working-with-objects/annotations
     */
    @JsonSerialize(using = RetainQuotesMapSerializer::class)
    val annotations: Map<String, String>? = null,

    //region system
    /**
     * Set: unique values will be kept during a merge
     *
     * Must be empty before the object is deleted from the registry. Each entry is an identifier for
     * the responsible component that will remove the entry from the list. If the deletionTimestamp
     * of the object is non-nil, entries in this list can only be removed. Finalizers may be processed
     * and removed in any order. Order is NOT enforced because it introduces significant risk of stuck
     * finalizers. finalizers is a shared field, any actor with permission can reorder it. If the
     * finalizer list is processed in order, then this can lead to a situation in which the component
     * responsible for the first finalizer in the list is waiting for a signal (field value, external
     * \system, or other) produced by a component responsible for a finalizer later in the list, resulting
     * in a deadlock. Without enforced ordering finalizers are free to order amongst themselves and are not
     * vulnerable to ordering changes in the list.
     */
    val finalizers: List<String>? = null,
    /**
     * ManagedFieldsEntry is a workflow-id, a FieldSet and the group version of the
     * resource that the fieldset applies to.
     */
    val managedFields: List<ManagedFieldsEntry>? = null,
    /**
     * OwnerReference contains enough information to let you identify an owning object. An owning
     * object must be in the same namespace as the dependent, or be cluster-scoped, so there is no
     * namespace field.
     */
    val ownerReferences: List<OwnerReference>? = null,
    //endregion

    //region read-only
    /**
     * CreationTimestamp is a timestamp representing the server time when this object was created. It is
     * not guaranteed to be set in happens-before order across separate operations. Clients may not set
     * this value. It is represented in RFC3339 form and is in UTC.
     *
     * Populated by the system. Read-only. Null for lists.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers
     * are provided for many of the factory methods that the time package offers.
     */
    val creationTimestamp: String? = null,
    /**
     * Number of seconds allowed for this object to gracefully terminate before it will be removed from
     * the system. Only set when deletionTimestamp is also set. May only be shortened. Read-only.
     */
    val deletionGracePeriodSeconds: Long? = null,
    /**
     * DeletionTimestamp is RFC 3339 date and time at which this resource will be deleted. This field
     * is set by the server when a graceful deletion is requested by the user, and is not directly
     * settable by a client. The resource is expected to be deleted (no longer visible from resource
     * lists, and not reachable by name) after the time in this field, once the finalizers list is
     * empty. As long as the finalizers list contains items, deletion is blocked. Once the
     * deletionTimestamp is set, this value may not be unset or be set further into the future,
     * although it may be shortened or the resource may be deleted prior to this time. For example,
     * a user may request that a pod is deleted in 30 seconds. The Kubelet will react by sending
     * a graceful termination signal to the containers in the pod. After that 30 seconds, the Kubelet
     * will send a hard termination signal (SIGKILL) to the container and after cleanup, remove the
     * pod from the API. In the presence of network partitions, this object may still exist after
     * this timestamp, until an administrator or automated process can determine the resource is
     * fully terminated. If not set, graceful deletion of the object has not been requested.
     *
     * Populated by the system when a graceful deletion is requested. Read-only.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val deletionTimestamp: String? = null,
    /**
     * A sequence number representing a specific generation of the desired state.
     * Populated by the system. Read-only.
     */
    val generation: Long? = null,
    /**
     * An opaque value that represents the internal version of this object that can be used by
     * clients to determine when objects have changed. May be used for optimistic concurrency,
     * change detection, and the watch operation on a resource or set of resources. Clients must
     * treat these values as opaque and passed unmodified back to the server. They may only be valid
     * for a particular resource or set of resources.
     *
     * Populated by the system. Read-only. Value must be treated as opaque by clients and .
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#concurrency-control-and-consistency
     */
    val resourceVersion: String? = null,
    /**
     * UID is the unique in time and space value for this object. It is typically generated by the
     * server on successful creation of a resource and is not allowed to change on PUT operations.
     *
     * Populated by the system. Read-only.
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names#uids
     */
    val uid: String? = null
    //endregion
)