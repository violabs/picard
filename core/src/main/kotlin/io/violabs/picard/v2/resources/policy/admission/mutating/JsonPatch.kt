package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * JsonPatch defines a JSON Patch.
 */
@GeneratedDsl
data class JsonPatch(
    /**
     * expression will be evaluated by CEL to create a JSON patch.
     * ref: https://github.com/google/cel-spec
     *
     * expression must return an array of JSONPatch values.
     *
     * For example, this CEL expression returns a JSON patch to conditionally modify a value:
     *
     * [
     *   JSONPatch{op: "test", path: "/spec/example", value: "Red"},
     *   JSONPatch{op: "replace", path: "/spec/example", value: "Green"}
     * ]
     *
     * To define an object for the patch value, use Object types. For example:
     *
     * [
     *   JSONPatch{
     *     op: "add",
     *     path: "/spec/selector",
     *     value: Object.spec.selector{matchLabels: {"environment": "test"}}
     *   }
     * ]
     *
     * To use strings containing '/' and '~' as JSONPatch path keys, use "jsonpatch.escapeKey". For example:
     *
     * [
     *   JSONPatch{
     *     op: "add",
     *     path: "/metadata/labels/" + jsonpatch.escapeKey("example.com/environment"),
     *     value: "test"
     *   },
     * ]
     *
     * CEL expressions have access to the types needed to create JSON patches and objects:
     * - 'JSONPatch' - CEL type of JSON Patch operations. JSONPatch has the fields 'op', 'from', 'path' and 'value'.
     *   See JSON patch for more details. The 'value' field may be set to any of: string, integer, array, map or object.
     *   If set, the 'path' and 'from' fields must be set to a JSON pointer string, where the 'jsonpatch.escapeKey()'
     *   CEL function may be used to escape path keys containing '/' and '~'.
     * - 'Object' - CEL type of the resource object.
     * - 'Object.<fieldName>' - CEL type of object field (such as 'Object.spec')
     * - 'Object.<fieldName1>.<fieldName2>...<fieldNameN>' - CEL type of nested field (such as 'Object.spec.containers')
     *
     * CEL expressions have access to the contents of the API request, organized into CEL variables as well as
     * some other useful variables:
     * - 'object' - The object from the incoming request. The value is null for DELETE requests.
     * - 'oldObject' - The existing object. The value is null for CREATE requests.
     * - 'request' - Attributes of the API request(ref).
     * - 'params' - Parameter resource referred to by the policy binding being evaluated. Only populated if the policy has a ParamKind.
     * - 'namespaceObject' - The namespace object that the incoming object belongs to. The value is null for cluster-scoped resources.
     * - 'variables' - Map of composited variables, from its name to its lazily evaluated value. For example, a variable named 'foo' can be accessed as 'variables.foo'.
     * - 'authorizer' - A CEL Authorizer. May be used to perform authorization checks for the principal (user or service account) of the request.
     * - 'authorizer.requestResource' - A CEL ResourceCheck constructed from the 'authorizer' and configured with the request resource.
     *
     * CEL expressions have access to Kubernetes CEL function libraries as well as:
     * - 'jsonpatch.escapeKey' - Performs JSONPatch key escaping. '~' and '/' are escaped as '~0' and '~1' respectively.
     *
     * Only property names of the form [a-zA-Z_.-/][a-zA-Z0-9_.-/]* are accessible. Required.
     */
    val expression: String
)