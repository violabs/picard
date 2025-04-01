
dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
}


application {
    mainClass.set("io.violabs.picard.tutorial.TutorialMainKt") // Change to your main class
}