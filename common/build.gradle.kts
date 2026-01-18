
plugins {
    `java-library`
    `maven-publish`
}

val commonVersion: String by rootProject.extra

group = "io.violabs.picard"
version = commonVersion

tasks.jar {
    archiveBaseName.set("picard-common")
}


publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId    = "io.violabs.picard"
            artifactId = "common"
            version    = version
        }
    }
}