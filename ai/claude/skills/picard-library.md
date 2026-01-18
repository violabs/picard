# Picard Library Skill

Use this skill when writing Kotlin code that generates Kubernetes manifests using the Picard DSL library.

## Overview

Picard is a Kotlin DSL for generating Kubernetes manifests. It provides type-safe, expressive manifest generation with full IDE support.

## Core Entry Points

### Building a Manifest

```kotlin
import io.violabs.picard.buildManifest
import io.violabs.picard.YamlBuilder

val manifest = buildManifest {
    // Add sections here
}

// Convert to YAML
val yaml = YamlBuilder.build(manifest)

// For a single resource
val singleYaml = YamlBuilder.singleBuild(resource)
```

## Manifest Sections

A Manifest is organized into logical sections. Use the appropriate section for each resource type:

| Section | Method | Resource Types |
|---------|--------|----------------|
| Authentication | `authenticationSection { }` | TokenRequest, TokenReview, SelfSubjectReview |
| Authorization | `authorizationSection { }` | Role, RoleBinding, ClusterRole, ClusterRoleBinding |
| Cluster | `clusterSection { }` | Namespace, Node, Event, Lease, APIService |
| Config | `configSection { }` | ConfigMap, Secret |
| Extend | `extendSection { }` | CustomResourceDefinition, MutatingWebhookConfiguration |
| Policy | `policySection { }` | ResourceQuota, LimitRange, PodDisruptionBudget |
| Service | `serviceSection { }` | Service, Ingress, IngressClass, EndpointSlice |
| Storage | `storageSection { }` | PersistentVolume, PersistentVolumeClaim, StorageClass |
| Workload | `workloadSection { }` | Pod, Deployment, StatefulSet, DaemonSet, Job, CronJob, ReplicaSet |

## DSL Patterns

### Basic Resource Structure

Every Kubernetes resource follows this pattern:

```kotlin
resourceType {
    metadata {
        name = "resource-name"
        namespace = "namespace"  // optional
        labels("key" to "value")
        annotations("key" to "value")
    }

    spec {
        // Resource-specific configuration
    }
}
```

### Common Patterns

#### Creating a Pod

```kotlin
buildManifest {
    workloadSection {
        pod {
            metadata {
                name = "my-pod"
                namespace = "default"
            }
            spec {
                containers {
                    container {
                        name = "app"
                        image = "nginx:1.14.2"
                        ports {
                            addContainerPort {
                                containerPort = 80
                            }
                        }
                    }
                }
            }
        }
    }
}
```

#### Creating a Deployment

```kotlin
buildManifest {
    workloadSection {
        deployment {
            metadata {
                name = "my-deployment"
                namespace = "default"
            }
            spec {
                replicas = 3
                selector {
                    matchLabels {
                        label("app", "my-app")
                    }
                }
                template {
                    metadata {
                        labels {
                            label("app", "my-app")
                        }
                    }
                    templateSpec {
                        containers {
                            container {
                                name = "app"
                                image = "my-image:latest"
                                ports {
                                    addContainerPort {
                                        containerPort = 8080
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
```

#### Creating a Service

```kotlin
buildManifest {
    serviceSection {
        service {
            metadata {
                name = "my-service"
                namespace = "default"
            }
            spec {
                selector("app" to "my-app")
                ports {
                    addServicePort {
                        port = 80
                        targetPort(8080)
                    }
                }
                type = Service.Spec.Type.ClusterIP
            }
        }
    }
}
```

#### Creating ConfigMaps and Secrets

```kotlin
buildManifest {
    configSection {
        configMap {
            metadata {
                name = "my-config"
                namespace = "default"
            }
            data(
                "KEY1" to "value1",
                "KEY2" to "value2"
            )
        }

        secret {
            metadata {
                name = "my-secret"
                namespace = "default"
            }
            type = Secret.Type.OPAQUE
            data(
                "username" to "admin",
                "password" to "secret123"
            )
        }
    }
}
```

#### Creating a PersistentVolumeClaim

```kotlin
import io.violabs.picard.domain.AccessMode

buildManifest {
    storageSection {
        persistentVolumeClaim {
            metadata {
                name = "my-pvc"
                namespace = "default"
            }
            spec {
                accessModes(AccessMode.ReadWriteOnce)
                resources {
                    requests("storage" to "10Gi")
                }
                storageClassName = "standard"
            }
        }
    }
}
```

#### Using Environment Variables

```kotlin
container {
    name = "app"
    image = "my-image"

    // Direct environment variables
    env {
        add {
            name = "SIMPLE_VAR"
            value = "simple-value"
        }

        // From Secret
        add {
            name = "SECRET_VAR"
            valueFrom {
                secretKeyRef {
                    name = "my-secret"
                    key = "password"
                }
            }
        }

        // From ConfigMap
        add {
            name = "CONFIG_VAR"
            valueFrom {
                configMapKeyRef {
                    name = "my-config"
                    key = "KEY1"
                }
            }
        }
    }

    // Bulk load from ConfigMap/Secret
    envFrom {
        add {
            configMapRef {
                name = "my-config"
            }
        }
        add {
            secretRef {
                name = "my-secret"
            }
        }
    }
}
```

#### Volume Mounts

```kotlin
templateSpec {
    containers {
        container {
            name = "app"
            image = "my-image"
            volumeMounts {
                volumeMount {
                    name = "data-volume"
                    mountPath = "/data"
                }
            }
        }
    }

    volumes {
        volume {
            name = "data-volume"
            persistentVolumeClaim {
                claimName = "my-pvc"
            }
        }

        // ConfigMap volume
        volume {
            name = "config-volume"
            configMap {
                name = "my-config"
            }
        }

        // EmptyDir volume
        volume {
            name = "cache-volume"
            emptyDir { }
        }
    }
}
```

## Important Conventions

### Labels and Selectors

Labels are key-value pairs. Use consistent patterns:

```kotlin
// In metadata
metadata {
    labels {
        label("app", "my-app")
        label("environment", "production")
    }
}

// As vararg pairs
labels("app" to "my-app", "env" to "prod")

// In selectors
selector {
    matchLabels {
        label("app", "my-app")
    }
}
```

### Namespace Management

Always specify namespace in metadata for namespaced resources:

```kotlin
metadata {
    name = "resource-name"
    namespace = "my-namespace"
}
```

### Resource Quantities

For CPU and memory limits:

```kotlin
resources {
    requests(
        "cpu" to "100m",
        "memory" to "128Mi"
    )
    limits(
        "cpu" to "500m",
        "memory" to "512Mi"
    )
}
```

## Complete Example: Full Application Stack

```kotlin
import io.violabs.picard.buildManifest
import io.violabs.picard.YamlBuilder
import io.violabs.picard.domain.AccessMode
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.service.Service

fun createApplicationManifest(): String {
    val namespace = "my-app"
    val appName = "my-application"

    val manifest = buildManifest {
        // Create namespace
        clusterSection {
            namespace {
                metadata { name = namespace }
            }
        }

        // Configuration
        configSection {
            configMap {
                metadata {
                    name = "$appName-config"
                    namespace = namespace
                }
                data("DATABASE_HOST" to "postgres-service")
            }

            secret {
                metadata {
                    name = "$appName-secrets"
                    namespace = namespace
                }
                type = Secret.Type.OPAQUE
                data(
                    "DB_USERNAME" to "admin",
                    "DB_PASSWORD" to "secure-password"
                )
            }
        }

        // Storage
        storageSection {
            persistentVolumeClaim {
                metadata {
                    name = "$appName-data"
                    namespace = namespace
                }
                spec {
                    accessModes(AccessMode.ReadWriteOnce)
                    resources {
                        requests("storage" to "1Gi")
                    }
                }
            }
        }

        // Workload
        workloadSection {
            deployment {
                metadata {
                    name = appName
                    namespace = namespace
                }
                spec {
                    replicas = 2
                    selector {
                        matchLabels {
                            label("app", appName)
                        }
                    }
                    template {
                        metadata {
                            labels {
                                label("app", appName)
                            }
                        }
                        templateSpec {
                            containers {
                                container {
                                    name = appName
                                    image = "my-registry/$appName:latest"
                                    ports {
                                        addContainerPort {
                                            containerPort = 8080
                                        }
                                    }
                                    envFrom {
                                        add {
                                            configMapRef {
                                                name = "$appName-config"
                                            }
                                        }
                                        add {
                                            secretRef {
                                                name = "$appName-secrets"
                                            }
                                        }
                                    }
                                    volumeMounts {
                                        volumeMount {
                                            name = "data"
                                            mountPath = "/app/data"
                                        }
                                    }
                                }
                            }
                            volumes {
                                volume {
                                    name = "data"
                                    persistentVolumeClaim {
                                        claimName = "$appName-data"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Service exposure
        serviceSection {
            service {
                metadata {
                    name = "$appName-service"
                    namespace = namespace
                }
                spec {
                    selector("app" to appName)
                    ports {
                        addServicePort {
                            port = 80
                            targetPort(8080)
                        }
                    }
                    type = Service.Spec.Type.ClusterIP
                }
            }
        }
    }

    return YamlBuilder.build(manifest)
}
```

## Module Structure

- **core**: Main Kubernetes DSL and resource definitions
- **common**: Shared utilities, logging, validation
- **cmd**: CLI tool for kubectl/helm operations
- **tutorial**: Usage examples
- **sandbox**: Experimental features

## Tips

1. **Use the right section**: Each resource type belongs in a specific section
2. **Check imports**: Import specific types like `Secret.Type.OPAQUE`, `Service.Spec.Type.ClusterIP`
3. **Prefer `templateSpec` in Deployment/StatefulSet**: Use `templateSpec` (not just `spec`) for the pod template spec
4. **Use `addContainerPort` and `addServicePort`**: These are the builder methods for adding ports
5. **Labels consistency**: Keep labels consistent between `metadata.labels` and `selector.matchLabels`