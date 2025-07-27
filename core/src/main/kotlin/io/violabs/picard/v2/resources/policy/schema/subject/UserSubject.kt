package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.konstellation.metaDsl.annotation.SingleEntryTransformDsl

/**
 * UserSubject holds detailed information for user-kind subject.
 */
@SingleEntryTransformDsl<String>(String::class)
data class UserSubject(
    /**
     * name is the username that matches, or "*" to match all usernames. Required.
     */
    val name: String
)

