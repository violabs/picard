
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    ksp(project(":dsl"))
    implementation(project(":dsl"))
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
    arg("dslBuilder.classpath", "io.violabs.picard.generateTest")
    arg("dslMarker.classpath", "io.violabs.picard.generateTest.TestDSLMarker")
}