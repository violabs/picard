plugins {
    `maven-publish`
}

dependencies {
    implementation(project(":star-charts"))
}

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