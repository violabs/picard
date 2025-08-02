package io.violabs.picard.v2.resources.workload.batch

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CronJobSpec describes how the job execution will look like and when it will actually run.
 */
@GeneratedDsl
data class CronJobSpec(
    /**
     * Specifies the job that will be created when executing a CronJob.
     */
    val jobTemplate: JobTemplateSpec,
    /**
     * The schedule in Cron format, see https://en.wikipedia.org/wiki/Cron.
     */
    val schedule: String,
    /**
     * The time zone name for the given schedule, see https://en.wikipedia.org/wiki/List_of_tz_database_time_zones.
     * If not specified, this will default to the time zone of the kube-controller-manager process.
     */
    val timeZone: String? = null,
    /**
     * Specifies how to treat concurrent executions of a Job. Valid values are:
     * "Allow" (default): allows CronJobs to run concurrently; - "Forbid": forbids concurrent runs,
     * skipping next run if previous run hasn't finished yet; - "Replace": cancels currently running job
     * and replaces it with a new one
     */
    val concurrencyPolicy: String? = null,
    /**
     * Optional deadline in seconds for starting the job if it misses scheduled time for any reason.
     * Missed jobs executions will be counted as failed ones.
     */
    val startingDeadlineSeconds: Long? = null,
    /**
     * This flag tells the controller to suspend subsequent executions, it does not apply to already
     * started executions. Defaults to false.
     */
    val suspend: Boolean? = null,
    /**
     * The number of successful finished jobs to retain. Value must be non-negative integer. Defaults to 3.
     */
    val successfulJobsHistoryLimit: Int? = null,
    /**
     * The number of failed finished jobs to retain. Value must be non-negative integer. Defaults to 1.
     */
    val failedJobsHistoryLimit: Int? = null
)