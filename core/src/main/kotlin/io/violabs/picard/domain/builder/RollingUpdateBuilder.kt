package io.violabs.picard.domain.builder
//
//class RollingUpdateBuilder : Builder<Strategy.RollingUpdate> {
//    var maxUnavailable: Int? = null
//    var maxSurge: Int? = null
//    var progressDeadlineSeconds: Int? = null
//    var minReadySeconds: Int? = null
//    var revisionHistoryLimit: Int? = null
//    var paused: Boolean? = null
//
//    override fun build(): Strategy.RollingUpdate {
//        return Strategy.RollingUpdate(
//            maxUnavailable = maxUnavailable,
//            maxSurge = maxSurge,
//            progressDeadlineSeconds = progressDeadlineSeconds,
//            minReadySeconds = minReadySeconds,
//            revisionHistoryLimit = revisionHistoryLimit,
//            paused = paused
//        )
//    }
//}