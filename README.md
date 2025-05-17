# picard

> :warning: **This is a work in progress.**

A DSL for generating kubernetes manifests, helm charts,
and cli runs. 

* Kubernetes manifests are first priority
* CLI is more experimental
* Helm is a future work in progress

# Docker / Containers

## Enterprise Test App

A spring boot app that can be used to test containerization.

```shell
./build_container.sh -m generation-test -j enterprise -i picard-enterprise -v 0.0.1-SNAPSHOT
```

# Road map

- [x] Work through basic objects
- [x] Create builders for basic objects
  - [x] Test
- [x] Create yaml mapping
  - [ ] Test yaml mapping
- [x] Pivot to use KSP + KotlinPoet to autogen builders
  - [x] Alternate timeline where we just do this. (See Below)
- [ ] Work on command line
- [ ] Work on helm

## Pivot - DSL

More useful for helm and additional libraries.
Slowly move original DSL I made over - testing is covered so that helps.

## Follow up

- [ ] Documentation at builder level
- [ ] Cloud resources

### left
https://grafana.com/docs/loki/latest/configure/#s3_storage_config
https://square.github.io/kotlinpoet/

## Tiered Logging Example

The `Logger` methods now accept optional `tier` and `branch` parameters.
`tier` controls the indentation level, while `branch` keeps the vertical
`|` indicator for nested logs when there are additional sibling entries.

```kotlin
val logger = Logger("DSL_BUILDER").enableDebug()

logger.debug("+++ DOMAIN: MyDomain +++")
logger.debug("package: com.example", tier = 1)
logger.debug("type: MyDomain", tier = 1)
logger.debug("Properties", tier = 1)
logger.debug("myProperty", tier = 2)
logger.debug("type: kotlin.String", tier = 3, branch = true)
logger.debug("singleEntryTransform: null", tier = 3, branch = true)
```

