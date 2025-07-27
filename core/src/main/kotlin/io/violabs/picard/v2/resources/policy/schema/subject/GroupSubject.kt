package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 * GroupSubject holds detailed information for group-kind subject.
 */
@SingleEntryTransformDsl<String>(String::class)
data class GroupSubject(
    /**
     * name is the user group that matches, or "*" to match
     * all user groups. See https://github.com/kubernetes/apiserver/blob/master/pkg/authentication/user/user.go
     * for some well-known group names. Required.
     */
    val name: String
)