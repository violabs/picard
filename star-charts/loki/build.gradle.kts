plugins {
    `maven-publish`
    id("com.google.devtools.ksp")
}

version = "0.0.1-SNAPSHOT"

//small update
dependencies {
    implementation(project(":star-charts"))
    implementation("io.violabs.picard:command:0.0.1")
    implementation("io.violabs.konstellation:meta-dsl:0.0.2")
    ksp("io.violabs.konstellation:dsl:0.0.1")
}

application {
    mainClass.set("io.violabs.picard.starCharts.loki.LokiMainKt")
}

kotlin
    .sourceSets["main"]
    .kotlin {
        srcDir("build/generated/ksp/main/kotlin")
    }

tasks.jar {
    archiveBaseName.set("loki")
    from(project(":star-charts").sourceSets.main.get().output)
}

publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId = "io.violabs.picard.star-charts"
            artifactId = "loki"
            version = version
        }
    }
}

kover {
    reports {
        filters {
            excludes {
                annotatedBy("io.violabs.picard.common.ExcludeFromCoverage")
            }
        }
    }
}

ksp {
    arg("projectRootClasspath", "io.violabs.picard.starCharts.loki")
    arg("dslBuilderClasspath", "io.violabs.picard.starCharts.loki.utils")
    arg("dslMarkerClass", "io.violabs.picard.starCharts.loki.utils.LokiDsl")
}