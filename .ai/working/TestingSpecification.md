
# Overview

You should copy over the existing test that matches the specification. It should match the same
structure as the code. Make sure the test does not contain V2.

# Details

There is a `Common.OBJECT_META` to use and the new structure of the shared one for the builder is:

```kotlin
import io.violabs.picard.Common.sharedObjectMeta
    //.. other code
    metadata {
        sharedObjectMeta()
    }
    //.. other code
```

Also available `sharedSelector()` from `Common` object

# Example Spec

codeLocation: core/src/main/kotlin/io/violabs/picard/v2/resources/policy/resource/quota/ResourceQuotaV2.kt
testLocation: core/src/test/kotlin/io/violabs/picard/v2/resources/policy/resource/quota/ResourceQuotaTest.kt
copyTestFor: ResourceQuota
replaceTestContent:
    - ResourceQuota -> ResourceQuotaV2
    - ResourceQuota.Spec -> ResourceQuotaSpec
    - ResourceQuota.Status -> ResourceQuotaStatus
    - ResourceQuota.Builder -> ResourceQuotaV2DslBuilder
    - imports with v2 imports