
dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":cmd"))
    implementation(project(":star-charts"))
    implementation(project(":star-charts:loki"))
}


application {
    mainClass.set("io.violabs.picard.sandbox.SandboxMainKt") // Change to your main class
}