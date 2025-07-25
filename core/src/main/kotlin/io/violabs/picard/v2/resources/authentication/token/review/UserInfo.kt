package io.violabs.picard.v2.resources.authentication.token.review

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * UserInfo holds the information about the user needed to implement the user.Info interface.
 */
@GeneratedDsl
data class UserInfo(
    /**
     * Any additional information provided by the authenticator.
     */
    val extra: Map<String, List<String>>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * The names of groups this user is a part of.
     */
    val groups: List<String>? = null,
    /**
     * A unique value that identifies this user across time. If this
     * user is deleted and another user by the same name is added,
     * they will have different UIDs.
     */
    val uid: String? = null,
    /**
     * The name that uniquely identifies this user among all active users.
     */
    val username: String? = null
)

