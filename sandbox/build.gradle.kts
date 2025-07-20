
dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":cmd"))
}


application {
    mainClass.set("io.violabs.picard.sandbox.SandboxMainKt") // Change to your main class
}