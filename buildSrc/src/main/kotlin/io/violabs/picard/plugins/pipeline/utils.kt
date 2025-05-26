package io.violabs.picard.plugins.pipeline

import org.gradle.api.Project

fun Project.hasLibrary(group: String? = null, name: String? = null): Boolean {
    return configurations.any { config ->
        config.dependencies.any { dep ->
            dep.group == group || dep.name == name
        }
    }
}

fun Project.hasLibrary(pattern: String): Boolean {
    return configurations.any { config ->
        config.dependencies.any { dep ->
            pattern in dep.group.toString() || pattern in dep.name
        }
    }
}