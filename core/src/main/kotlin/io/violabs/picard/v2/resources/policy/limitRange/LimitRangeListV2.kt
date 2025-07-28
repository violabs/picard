package io.violabs.picard.v2.resources.policy.limitRange

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyListResource

/**
 * List of LimitRange resources.
 */
@GeneratedDsl
data class LimitRangeListV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<LimitRangeV2>,
    override val metadata: ListMeta? = null,
) : PolicyListResource<LimitRangeListV2.Version, LimitRangeV2> {
    interface Version : APIVersion
}
