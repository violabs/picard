
plugins {
    `maven-publish`
}

group = "io.violabs.picard"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(project(":core"))
    implementation(project(":cmd"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")

    testImplementation(project(":core-test"))
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.18.3")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
}

tasks.jar {
    archiveBaseName.set("picard-star-charts")
    from(project(":common").sourceSets.main.get().output)
    from(project(":core").sourceSets.main.get().output)
}

publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId    = "io.violabs.picard"
            artifactId = "star-charts"
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