package io.violabs.picard.v2.resources.workload.batch.cron

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectReference
import java.time.LocalDateTime

/**
 * CronJobStatus represents the current state of a cron job.
 */
@GeneratedDsl(withListGroup = true)
data class CronJobStatus(
    /**
     * A list of pointers to currently running jobs.
     */
    val active: List<ObjectReference>? = null,
    /**
     * Information when was the last time the job was successfully scheduled.
     */
    val lastScheduleTime: LocalDateTime? = null,
    /**
     * Information when was the last time the job successfully completed.
     */
    val lastSuccessfulTime: LocalDateTime? = null
)