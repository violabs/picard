package io.violabs.picard.starCharts.loki.distributor

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class OTLPConfig(
    /**
     * # List of default otlp resource attributes to be picked as index labels
     * # CLI flag: -distributor.otlp.default_resource_attributes_as_index_labels
     * [default_resource_attributes_as_index_labels: <list of strings> | default = [service.name service.namespace service.instance.id deployment.environment deployment.environment.name cloud.region cloud.availability_zone k8s.cluster.name k8s.namespace.name k8s.pod.name k8s.container.name container.name k8s.replicaset.name k8s.deployment.name k8s.statefulset.name k8s.daemonset.name k8s.cronjob.name k8s.job.name]]
     */
    val defaultResourceAttributesAsIndexLabels: List<String>? = null
)