package io.violabs.picard.v2.resources.configstorage.volume.projection

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

@GeneratedDsl
data class ClusterTrustBundleProjection(
    /**
     * Relative path from the volume root to write the bundle.
     */
    val path: String,
    /**
     * Select all ClusterTrustBundles that match this label selector.
     * Only has effect if signerName is set. Mutually-exclusive with name. If unset, interpreted
     * as "match nothing". If set but empty, interpreted as "match everything".
     */
    val labelSelector: LabelSelector? = null,
    /**
     * Select a single ClusterTrustBundle by object name. Mutually-exclusive with signerName and labelSelector.
     */
    val name: String? = null,
    /**
     * If true, don't block pod startup if the referenced ClusterTrustBundle(s) aren't available.
     * If using name, then the named ClusterTrustBundle is allowed not to exist. If using signerName,
     * then the combination of signerName and labelSelector is allowed to match zero ClusterTrustBundles.
     */
    val optional: Boolean? = null,
    /**
     * Select all ClusterTrustBundles that match this signer name. Mutually-exclusive with name. The contents
     * of all selected ClusterTrustBundles will be unified and deduplicated.
     */
    val signerName: String? = null
)