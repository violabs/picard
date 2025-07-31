package io.violabs.picard.v2.resources.cluster.lease

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.Instant

/**
 * LeaseSpec is a specification of a Lease.
 */
@GeneratedDsl
data class LeaseSpec(
    /**
     * acquireTime is a time when the current lease was acquired.
     * MicroTime is version of Time with microsecond level precision.
     */
    val acquireTime: Instant? = null,
    /**
     * holderIdentity contains the identity of the holder of a current lease. If Coordinated Leader Election is used, 
     * the holder identity must be equal to the elected LeaseCandidate.metadata.name field.
     */
    val holderIdentity: String? = null,
    /**
     * leaseDurationSeconds is a duration that candidates for a lease need to wait to force acquire it. 
     * This is measured against the time of last observed renewTime.
     */
    val leaseDurationSeconds: Int? = null,
    /**
     * leaseTransitions is the number of transitions of a lease between holders.
     */
    val leaseTransitions: Int? = null,
    /**
     * PreferredHolder signals to a lease holder that the lease has a more optimal holder and should be given up. 
     * This field can only be set if Strategy is also set.
     */
    val preferredHolder: String? = null,
    /**
     * renewTime is a time when the current holder of a lease has last updated the lease.
     * MicroTime is version of Time with microsecond level precision.
     */
    val renewTime: Instant? = null,
    /**
     * Strategy indicates the strategy for picking the leader for coordinated leader election. If the field is not specified, 
     * there is no active coordination for this lease. (Alpha) Using this field requires the CoordinatedLeaderElection feature gate to be enabled.
     */
    val strategy: String? = null
)