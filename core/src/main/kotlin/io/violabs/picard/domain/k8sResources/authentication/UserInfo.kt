package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.common.DSLBuilder

data class UserInfo(
    val extra: Map<String, List<String>>? = null,
    val groups: List<String>? = null,
    val uid: String? = null,
    val username: String? = null
) {
    class Builder : DSLBuilder<UserInfo> {
        private var extra: Map<String, List<String>>? = null
        private var groups: List<String>? = null
        var uid: String? = null
        var username: String? = null

        fun extra(vararg pairs: Pair<String, List<String>>) {
            extra = pairs.toMap()
        }

        fun groups(vararg groups: String) {
            this.groups = groups.toList()
        }

        override fun build(): UserInfo {
            return UserInfo(
                extra = extra,
                groups = groups,
                uid = uid,
                username = username
            )
        }
    }
}
