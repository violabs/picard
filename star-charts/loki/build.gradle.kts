plugins {
    `maven-publish`
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":star-charts"))
    implementation(project(":dsl"))
    ksp(project(":dsl"))
}

//src/main/resources/META-INF/services/com.google.devtools.ksp.processing.SymbolProcessorProvider
kotlin.sourceSets["main"].kotlin.srcDir("build/generated/ksp/main/kotlin")

tasks.jar {
    archiveBaseName.set("loki")
    from(project(":star-charts").sourceSets.main.get().output)
}

publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId    = "io.violabs.picard.star-charts"
            artifactId = "loki"
            version    = version
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