package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Subject matches the originator of a request, as identified by the request
 * authentication system. There are three ways of matching an originator;
 * by user, group, or service account.
 */
@GeneratedDsl(withListGroup = true)
data class Subject(
    /**
     * kind indicates which one of the other fields is non-empty. Required
     */
    val kind: String,
    /**
     * group matches based on user group name.
     */
    val group: GroupSubject? = null,
    /**
     * serviceAccount matches ServiceAccounts.
     */
    val serviceAccount: ServiceAccountSubject? = null,
    /**
     * user matches based on username.
     */
    val user: UserSubject? = null
)

