
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    ksp(project(":dsl"))
    implementation(project(":meta-dsl"))
    testImplementation(project(":common"))
    testImplementation(project(":core-test"))
    testImplementation(project(":core"))
    testImplementation(project(":cmd"))
    testImplementation(project(":star-charts:loki"))
}

kotlin {
    sourceSets {
        main {
            kotlin {
                // add KSP’s output dir for main
                srcDir("${layout.buildDirectory}/generated/ksp/main/kotlin")
            }
        }

        test {
            kotlin {
                // add KSP’s output dir for test
                srcDir("${layout.buildDirectory}/generated/ksp/test/kotlin")
            }
        }
    }
}


ksp {
    arg("dslBuilderClasspath", "io.violabs.picard.generateTest")
    arg("dslMarkerClass", "io.violabs.picard.generateTest.TestDslMarker")
}