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

## Logging

Should have a structure if there are nested processes:

```
picard DEBUG [····DSL_BUILDER] *>> +++ DOMAIN: io.violabs.picard.starCharts.loki.bloomBuild.BloomBuildPlannerQueue  +++
picard DEBUG [····DSL_BUILDER] *>>   |__ package: io.violabs.picard.starCharts.loki.bloomBuild
picard DEBUG [····DSL_BUILDER] *>>   |__ type: BloomBuildPlannerQueue
picard DEBUG [····DSL_BUILDER] *>>   |__ builder: BloomBuildPlannerQueueBuilder
picard DEBUG [····DSL_BUILDER] *>>   |__ DSL Marker added
picard DEBUG [····DSL_BUILDER] *>>   |__ DSL Builder Interface added
picard DEBUG [····DSL_BUILDER] *>>   |__ Properties added
picard DEBUG [····DSL_BUILDER] *>>       |__ maxQueuedTasksPerTenant
picard DEBUG [····DSL_BUILDER] *>>       |   |__ type: kotlin.Int
picard DEBUG [····DSL_BUILDER] *>>       |   |__ singleEntryTransform: null
picard DEBUG [····DSL_BUILDER] *>>       |__ storeTasksOnDisk
picard DEBUG [····DSL_BUILDER] *>>       |   |__ type: kotlin.Boolean
picard DEBUG [····DSL_BUILDER] *>>       |   |__ singleEntryTransform: null
picard DEBUG [····DSL_BUILDER] *>>       |__ tasksDiskDirectory
picard DEBUG [····DSL_BUILDER] *>>       |   |__ type: kotlin.String
picard DEBUG [····DSL_BUILDER] *>>       |   |__ singleEntryTransform: null
picard DEBUG [····DSL_BUILDER] *>>       |__ cleanTasksDirectory
picard DEBUG [····DSL_BUILDER] *>>           |__ type: kotlin.Boolean
picard DEBUG [····DSL_BUILDER] *>>           |__ singleEntryTransform: null
```