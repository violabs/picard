package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * JobStatus represents the current state of a Job.
 */
@GeneratedDsl(withListGroup = true)
data class JobStatus(
    /**
     * Represents time when the job controller started processing a job. When a Job is created
     * in the suspended state, this field is not set until the first time it is resumed. This
     * field is reset every time a Job is resumed from suspension. It is represented in
     * RFC3339 form and is in UTC.
     */
    val startTime: LocalDateTime? = null,
    /**
     * Represents time when the job was completed. It is not guaranteed to be set in
     * happens-before order across separate operations. It is represented in RFC3339 form
     * and is in UTC. The completion time is set when the job finishes successfully,
     * and only then. The value cannot be updated or removed. The value indicates the same
     * or later point in time as the startTime field.
     */
    val completionTime: LocalDateTime? = null,
    /**
     * The number of pending and running pods which are not terminating (without a deletionTimestamp).
     * The value is zero for finished jobs.
     */
    val active: Int? = null,
    /**
     * The number of pods which reached phase Failed. The value increases monotonically.
     */
    val failed: Int? = null,
    /**
     * The number of pods which reached phase Succeeded. The value increases monotonically for
     * a given spec. However, it may decrease in reaction to scale down of elastic indexed jobs.
     */
    val succeeded: Int? = null,
    /**
     * completedIndexes holds the completed indexes when .spec.completionMode = "Indexed" in a
     * text format. The indexes are represented as decimal integers separated by commas.
     * The numbers are listed in increasing order. Three or more consecutive numbers are
     * compressed and represented by the first and last element of the series, separated by
     * a hyphen. For example, if the completed indexes are 1, 3, 4, 5 and 7,
     * they are represented as "1,3-5,7".
     */
    val completedIndexes: String? = null,
    /**
     * The latest available observations of an object's current state. When a Job fails, one of
     * the conditions will have type "Failed" and status true. When a Job is suspended, one of
     * the conditions will have type "Suspended" and status true; when the Job is resumed, the
     * status of this condition will become false. When a Job is completed, one of the conditions
     * will have type "Complete" and status true.
     */
    val conditions: List<JobCondition>? = null,
    /**
     * uncountedTerminatedPods holds the UIDs of Pods that have terminated but the job controller
     * hasn't yet accounted for in the status counters.
     */
    val uncountedTerminatedPods: UncountedTerminatedPods? = null,
    /**
     * The number of active pods which have a Ready condition and
     * are not terminating (without a deletionTimestamp).
     */
    val ready: Int? = null,
    /**
     * FailedIndexes holds the failed indexes when spec.backoffLimitPerIndex is set.
     * The indexes are represented in the text format analogous as for the completedIndexes
     * field, ie. they are kept as decimal integers separated by commas. The numbers are
     * listed in increasing order. Three or more consecutive numbers are compressed and
     * represented by the first and last element of the series, separated by a hyphen.
     * For example, if the failed indexes are 1, 3, 4, 5 and 7, they are represented as
     * "1,3-5,7". The set of failed indexes cannot overlap with the set of completed indexes.
     */
    val failedIndexes: String? = null,
    /**
     * The number of pods which are terminating (in phase Pending or Running and have a deletionTimestamp).
     */
    val terminating: Int? = null
)