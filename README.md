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
- [ ] Create yaml mapping
  - [ ] Test yaml mapping
- [ ] Work on command line
- [ ] Work on helm

## Follow up

- [ ] Documentation at builder level
- [ ] Cloud resources


### left
https://grafana.com/docs/loki/latest/configure/#s3_storage_config
