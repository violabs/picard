package io.violabs.picard.v2.resources.config.secret

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigListResource

/**
 * SecretList is a list of Secrets.
 */
@GeneratedDsl
data class SecretListV2(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<SecretV2>,
    override val metadata: ListMeta? = null
) : ConfigListResource<SecretListV2.Version, SecretV2> {
    interface Version : APIVersion
}
