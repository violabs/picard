package io.violabs.picard.starCharts.loki.storageConfig

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.NamedStoresConfig
import io.violabs.picard.starCharts.loki.thanosObjectStoreConfig.ThanosObjectStoreConfig

@GeneratedDsl
data class ObjectStore(
    /**
     *   # The thanos_object_store_config block configures the connection to object
     *   # storage backend using thanos-io/objstore clients. This will become the
     *   # default way of configuring object store clients in future releases.
     *   # Currently this is opt-in and takes effect only when `-use-thanos-objstore`
     *   # is set to true.
     *   # The CLI flags prefix for this block configuration is: object-store
     *   [<thanos_object_store_config>]
     */
    val thanos: ThanosObjectStoreConfig? = null,
    val namedStores: NamedStoresConfig? = null
)