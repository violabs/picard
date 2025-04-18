
dependencies {
    implementation(project(":common"))
    testImplementation(project(":core-test"))
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.18.3")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.3")
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

//koverReport {
//    filters {
//        excludes {
//            annotatedBy("ExcludeFromCoverage")
//        }
//    }
//}