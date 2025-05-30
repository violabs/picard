
plugins {
    `maven-publish`
}

group = "io.violabs.picard"
version = "0.1.2-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.0")

    testImplementation(project(":core-test"))
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.19.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
}

tasks.jar {
    archiveBaseName.set("picard-core")
    from(project(":common").sourceSets.main.get().output)
}

publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId    = "io.violabs.picard"
            artifactId = "core"
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