package io.violabs.picard

class Pod(
    val apiVersion: Version,
    val kind: Kind,
    val metadata: Metadata,
    val spec: Spec
) {
    enum class Version {
        V1;

        override fun toString(): String = name.lowercase()
    }
}

class Metadata(
    val name: String
)

class Spec(
    val containers: List<Container>? = null,
    val restartPolicy: RestartPolicy? = null,
    val template: Spec? = null
)

class Container(
    val name: String,
    val image: String,
    val command: List<String>? = null,
    val ports: List<Port>? = null
) {

    fun toYaml(): String {
        return """
        |- name: $name
        |  image: $image
        |  
        """.trimMargin("|")
    }

    class Port(
        val containerPort: Int
    ) {
        fun toYaml(): String = "- containerPort: $containerPort"
    }
}

enum class Kind {
    JOB,
    POD;

    override fun toString(): String = name
        .lowercase()
        .replaceFirstChar(Char::uppercase)
}

enum class RestartPolicy {
    //        Always,
//        Never,
    OnFailure
}

fun buildPodYaml(pod: Pod): String = buildYaml {
    property("apiVersion", pod.apiVersion)
    property("kind", pod.kind)
    property("metadata") {
        property("name", pod.metadata.name)
    }
    property("spec") {
        properties("containers") {
            val firstContainer = pod.spec.containers!!.first()
            listItem("name", firstContainer.name, listDelimiter = true)
            listItem("image", firstContainer.image)
            properties("ports") {
                listItem("containerPort", firstContainer.ports!!.first().containerPort, listDelimiter = true)
            }
        }
    }
}