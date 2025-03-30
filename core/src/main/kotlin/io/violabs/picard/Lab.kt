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

class PodBuilder : Builder<Pod> {
    var apiVersion: Pod.Version = Pod.Version.V1
    var kind: Kind? = null
    private var metadata: Metadata? = null
    var spec: Spec? = null

    override fun build(): Pod {
        return Pod(
            apiVersion = apiVersion,
            kind = requireNotNull(kind) { "Please provide a kind. ${Kind.entries}" },
            metadata = requireNotNull(metadata) { "Please provide a metadata" },
            spec = requireNotNull(spec) { "Please provide a spec" }
        )
    }

    fun metadata(scope: MetadataBuilder.() -> Unit) {
        metadata = scopedBuild(::MetadataBuilder, scope)
    }

    fun spec(scope: SpecBuilder.() -> Unit) {
        spec = scopedBuild(::SpecBuilder, scope)
    }
}

class MetadataBuilder : Builder<Metadata> {
    var name: String? = null

    override fun build(): Metadata = Metadata(
        name = requireNotNull(name) { "Please provide a name" }
    )
}

class SpecBuilder : Builder<Spec> {
    private var containers: List<Container>? = null
    var restartPolicy: RestartPolicy? = null
    var template: Spec? = null

    override fun build(): Spec {
        if (containers == null && template == null) {
            throw IllegalStateException("Please provide at least one container or template")
        }

        return Spec(
            containers = containers,
            restartPolicy = restartPolicy,
            template = template
        )
    }

    fun containers(scope: ContainersBuilder.() -> Unit) {
        containers = scopedBuild(::ContainersBuilder, scope)
    }
}

class ContainersBuilder : Builder<List<Container>> {
    private val containers = mutableListOf<Container>()

    override fun build(): List<Container> = containers

    fun container(scope: ContainerBuilder.() -> Unit) {
        containers.add(scopedBuild(::ContainerBuilder, scope))
    }
}

class ContainerBuilder : Builder<Container> {
    var name: String? = null
    var image: String? = null
    var command: List<String>? = null
    var ports: List<Container.Port>? = null

    override fun build(): Container = Container(
        name = requireNotNull(name) { "Please provide a name" },
        image = requireNotNull(image) { "Please provide an image" },
        command = command,
        ports = ports
    )

    fun ports(scope: PortsBuilder.() -> Unit) {
        ports = scopedBuild(::PortsBuilder, scope)
    }
}

class PortsBuilder : Builder<List<Container.Port>> {
    private val ports = mutableListOf<Container.Port>()

    override fun build(): List<Container.Port> = ports

    fun port(scope: PortBuilder.() -> Unit) {
        ports.add(scopedBuild(::PortBuilder, scope))
    }
}

class PortBuilder : Builder<Container.Port> {
    var containerPort: Int? = null

    override fun build(): Container.Port = Container.Port(
        containerPort = requireNotNull(containerPort) { "Please provide a container port" }
    )
}

fun pod(scope: PodBuilder.() -> Unit): Pod = scopedBuild(::PodBuilder, scope)

fun <T : Builder<R>, R> scopedBuild(emptyConstructor: () -> T, scope: T.() -> Unit): R {
    val builder = emptyConstructor()
    builder.scope()
    return builder.build()
}

interface Builder<T> {
    fun build(): T
}