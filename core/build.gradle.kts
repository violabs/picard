import io.violabs.plugins.open.secrets.gradleloader.domain.getPropertyOrEnv

val coreVersion: String by rootProject.extra

val dslVersion: String by project
val metaDslVersion: String by project

plugins {
    id("io.violabs.plugins.open.publishing.maven-generated-artifacts")
    id("io.violabs.plugins.open.publishing.digital-ocean-spaces")
    id("io.violabs.plugins.open.secrets.gradle-loader")
    id("com.google.devtools.ksp")
}

group = "io.violabs.picard"
version = coreVersion

dependencies {
    implementation(project(":common"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.19.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.0")
    ksp("io.violabs.konstellation:dsl:$dslVersion")
    implementation("io.violabs.konstellation:meta-dsl:$metaDslVersion")

    testImplementation(project(":core-test"))
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.19.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.0")
}

tasks.jar {
    archiveBaseName.set("core")
    from(project(":common").sourceSets.main.get().output)
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


digitalOceanSpacesPublishing {
    bucket = "open-reliquary"
    accessKey = project.getPropertyOrEnv("spaces.key", "DO_SPACES_API_KEY")
    secretKey = project.getPropertyOrEnv("spaces.secret", "DO_SPACES_SECRET")
    publishedVersion = version.toString()
    dryRun = false
}

mavenGeneratedArtifacts {
    publicationName = "digitalOceanSpaces"
    name = "Picard Core"
    description = """
            Kubernetes DSL with Kotlin
        """
    websiteUrl = "https://github.com/violabs/picard/tree/main/core"

    licenses {
        license {
            name = "Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        }
    }

    developers {
        developer {
            id = "violabs"
            name = "Violabs Team"
            email = "support@violabs.io"
            organization = "Violabs Software"
        }
    }

    scm {
        connection = "https://github.com/violabs/picard.git"
    }
}


ksp {
    arg("projectRootClasspath", "io.violabs.picard")
    arg("dslBuilderClasspath", "io.violabs.picard.common")
    arg("dslMarkerClass", "io.violabs.picard.common.PicardDsl")
}