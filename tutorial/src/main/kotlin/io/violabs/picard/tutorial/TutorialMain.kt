//package io.violabs.picard.tutorial
//
//import io.violabs.picard.common.DefaultLogger
//import io.violabs.picard.tutorial.job.JobFactory
//import io.violabs.picard.tutorial.pod.ComplexPodFactory
//import io.violabs.picard.tutorial.pod.SimplePodFactory.buildSimplePodDsl
//import io.violabs.picard.tutorial.podTemplate.PodTemplateFactory.buildPodTemplateDsl
////import io.violabs.picard.tutorial.job.JobFactory
////import io.violabs.picard.tutorial.kubletConfig.SimpleKubeletConfigurationFactory
////import io.violabs.picard.tutorial.pod.ComplexPodFactory
////import io.violabs.picard.tutorial.pod.SimplePodFactory.buildSimplePod
////import io.violabs.picard.tutorial.pod.SimplePodFactory.buildSimplePodDsl
////import io.violabs.picard.tutorial.podTemplate.PodTemplateFactory.buildPodTemplateDsl
////import io.violabs.picard.tutorial.workload.DeploymentFactory
////import io.violabs.picard.tutorial.workload.ReplicaSetFactory
//import java.io.File
//
//fun main(vararg args: String) = with(FileManager()) {
//    log.info("Beginning Tutorial run")
//
//    addFile(
//        "Simple pod DSL",
//        "./tutorial/src/main/resources/generated/simple-pod-dsl.yaml",
//        buildSimplePodDsl()
//    )
//
//    addFile(
//        "Pod template DSL",
//        "./tutorial/src/main/resources/generated/pod-template-dsl.yaml",
//        buildPodTemplateDsl()
//    )
////
////    addFile(
////        "Simple kubelet config",
////        "./tutorial/src/main/resources/generated/simple-kubelet-config-dsl.yaml",
////        SimpleKubeletConfigurationFactory.buildFromDsl()
////    )
////
//    addFile(
//        "Pod with init containers",
//        "./tutorial/src/main/resources/generated/pod-with-init-containers.yaml",
//        ComplexPodFactory.buildWithInitContainers()
//    )
//
//    addFile(
//        "Pod with sidecar",
//        "./tutorial/src/main/resources/generated/pod-sidecar.yaml",
//        ComplexPodFactory.buildWithSideCar()
//    )
//
//    addFile(
//        "Job with sidecar",
//        "./tutorial/src/main/resources/generated/job-sidecar.yaml",
//        JobFactory.buildJobWithSideCar()
//    )
////
////    addFile(
////        "Simple deployment",
////        "./tutorial/src/main/resources/generated/simple-deployment.yaml",
////        DeploymentFactory.buildSimpleDeployment()
////    )
////
////    addFile(
////        "Rolling update deployment max unavailable",
////        "./tutorial/src/main/resources/generated/rolling-update-deployment-max-unavailable.yaml",
////        DeploymentFactory.buildMaxUnavailableStrategy()
////    )
////
////    addFile(
////        "Rolling update deployment max surge",
////        "./tutorial/src/main/resources/generated/rolling-update-deployment-max-surge.yaml",
////        DeploymentFactory.buildMaxSurgeStrategy()
////    )
////
////    addFile(
////        "Rolling update deployment hybrid",
////        "./tutorial/src/main/resources/generated/rolling-update-deployment-hybrid.yaml",
////        DeploymentFactory.buildHybridStrategy()
////    )
////
////    addFile(
////        "Simple replica set",
////        "./tutorial/src/main/resources/generated/simple-replica-set.yaml",
////        ReplicaSetFactory.buildSimpleReplicaSet()
////    )
//}
//
//class FileManager : DefaultLogger("TUTORIAL") {
//    fun addFile(label: String, fileName: String, content: String) {
//        val file = File(fileName)
//        file.writeText(content)
//
//        log.info(label)
//        content.split("\n").forEach { log.info("  | $it") }
//    }
//}
