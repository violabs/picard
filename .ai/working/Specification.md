
# Working Specifications

## Prerequisites

- Access to the Picard codebase
- Gradle build environment set up
- Understanding of Kotlin and Kubernetes resource structure

## Implementation Workflow

Follow these steps in order:

1. **Create data classes** with proper annotations
2. **Update APIVersion declarations** to include V2 versions
3. **Add deprecation** to v1 classes
4. **Run clean build** to generate DSL builders
5. **Implement tests** using generated builders
6. **Run tests** to verify implementation

## Instruct

Build out objects with a specific format.
They should utilize `GeneratedDsl` and `DefaultValue` annotations. These items represent top
level Kubernetes resources, and should be structured to include the `apiVersion`, `metadata`, and
any specific fields relevant to the resource. There is documentation below to reference. You must include
the documentation as comments. All classes should have their own file in the `v2` package. This will live under
the v2/resources directory

Refer to the example below for guidance. When you are finished,
add items to .git.


### List Group Annotations

If there is a list property that needs DSL builder support, add `withListGroup = true` to the `GeneratedDsl` annotation **on the class level**:

```kotlin
// For the parent class that contains the list
@GeneratedDsl
data class ParentResource(
    val childConditions: List<ChildCondition>
)

// For the child class that will be in the list
@GeneratedDsl(withListGroup = true) 
data class ChildCondition(...)
```

This generates both `conditions(vararg items: ChildCondition)` and `conditions { childCondition { ... } }` DSL methods.

### Map Group Annotations

If there are any properties that contain a entity that has `@GeneratedDsl` that is a value in a map, you should use
`MapGroupTypes.SINGLE`:

```kotlin
@GeneratedDsl
data class ParentResource(
    val mappableChild: Map<String, MappableChild>
)

@GeneratedDsl(withMapGroup = GeneratedDsl.MapGroupTypes.SINGLE)
data class MappableChild(...)
```

### JsonProperty Usage

When field names need to preserve original JSON casing that differs from Kotlin conventions:

```kotlin
data class ComponentCondition(
    val status: String,
    val type: String,
    @JsonProperty("error")  // JSON field is "error", Kotlin property is "errorValue"
    val errorValue: String? = null,
    val message: String? = null
)
```

### APIVersion Integration

You will need to look at the package `io.violabs.picard.domain.k8sResources.APIVersion` to
add the V2 version as an extension of the existing version.

### Deprecation

Finally, update the v1 to have `@Deprecated("Use v2", ReplaceWith(<package for v2>))`

## Rules

- Time = LocalDateTime
- Quantity = io.violabs.picard.domain.k8sResources.Quantity
- Capitalized acronyms and initialisms should use camelCase (e.g., `APIService` -> `ApiService`, `CA` -> `Ca`)
  - Use `@JsonProperty` for properties to preserve the original casing if necessary.
  - Override `getKind(): String` to return the original casing for the kind if it extends a Resource

## Build Process

When you are finished creating the files:

1. **First**: Run `./gradlew clean build -x test` to generate DSL builders
2. **Then**: Implement tests using the generated builders
3. **Finally**: Run `./gradlew test` to verify everything works

If you do not fix the build after 3 times, you can ask for help.

## Troubleshooting

### Common Issues

1. **DSL builders not found during test compilation**
   - **Solution**: Run `./gradlew clean build -x test` first to generate the DSL classes

2. **Missing list group methods (e.g., `conditions { ... }`)**
   - **Solution**: Ensure both parent and child classes have `@GeneratedDsl(withListGroup = true)`

3. **JsonProperty compilation errors**
   - **Solution**: Import `com.fasterxml.jackson.annotation.JsonProperty`

4. **APIVersion compilation errors**
   - **Solution**: Verify the import was added and the Version interface extends APIVersion


## Documentation

HorizontalPodAutoscaler
HorizontalPodAutoscaler is the configuration for a horizontal pod autoscaler, which automatically manages the replica count of any resource implementing the scale subresource based on the metrics specified.
apiVersion: autoscaling/v2

import "k8s.io/api/autoscaling/v2"

HorizontalPodAutoscaler
HorizontalPodAutoscaler is the configuration for a horizontal pod autoscaler, which automatically manages the replica count of any resource implementing the scale subresource based on the metrics specified.

apiVersion: autoscaling/v2

kind: HorizontalPodAutoscaler

metadata (ObjectMeta)

metadata is the standard object metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

spec (HorizontalPodAutoscalerSpec)

spec is the specification for the behaviour of the autoscaler. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status.

status (HorizontalPodAutoscalerStatus)

status is the current information about the autoscaler.

HorizontalPodAutoscalerSpec
HorizontalPodAutoscalerSpec describes the desired functionality of the HorizontalPodAutoscaler.

maxReplicas (int32), required

maxReplicas is the upper limit for the number of replicas to which the autoscaler can scale up. It cannot be less that minReplicas.

scaleTargetRef (CrossVersionObjectReference), required

scaleTargetRef points to the target resource to scale, and is used to the pods for which metrics should be collected, as well as to actually change the replica count.

CrossVersionObjectReference contains enough information to let you identify the referred resource.

scaleTargetRef.kind (string), required

kind is the kind of the referent; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds

scaleTargetRef.name (string), required

name is the name of the referent; More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names

scaleTargetRef.apiVersion (string)

apiVersion is the API version of the referent

minReplicas (int32)

minReplicas is the lower limit for the number of replicas to which the autoscaler can scale down. It defaults to 1 pod. minReplicas is allowed to be 0 if the alpha feature gate HPAScaleToZero is enabled and at least one Object or External metric is configured. Scaling is active as long as at least one metric value is available.

behavior (HorizontalPodAutoscalerBehavior)

behavior configures the scaling behavior of the target in both Up and Down directions (scaleUp and scaleDown fields respectively). If not set, the default HPAScalingRules for scale up and scale down are used.

HorizontalPodAutoscalerBehavior configures the scaling behavior of the target in both Up and Down directions (scaleUp and scaleDown fields respectively).

behavior.scaleDown (HPAScalingRules)

scaleDown is scaling policy for scaling Down. If not set, the default value is to allow to scale down to minReplicas pods, with a 300 second stabilization window (i.e., the highest recommendation for the last 300sec is used).

*HPAScalingRules configures the scaling behavior for one direction via scaling Policy Rules and a configurable metric tolerance.

Scaling Policy Rules are applied after calculating DesiredReplicas from metrics for the HPA. They can limit the scaling velocity by specifying scaling policies. They can prevent flapping by specifying the stabilization window, so that the number of replicas is not set instantly, instead, the safest value from the stabilization window is chosen.

The tolerance is applied to the metric values and prevents scaling too eagerly for small metric variations. (Note that setting a tolerance requires enabling the alpha HPAConfigurableTolerance feature gate.)*

behavior.scaleDown.policies ([]HPAScalingPolicy)

Atomic: will be replaced during a merge

policies is a list of potential scaling polices which can be used during scaling. If not set, use the default values: - For scale up: allow doubling the number of pods, or an absolute change of 4 pods in a 15s window. - For scale down: allow all pods to be removed in a 15s window.

HPAScalingPolicy is a single policy which must hold true for a specified past interval.

behavior.scaleDown.policies.type (string), required

type is used to specify the scaling policy.

behavior.scaleDown.policies.value (int32), required

value contains the amount of change which is permitted by the policy. It must be greater than zero

behavior.scaleDown.policies.periodSeconds (int32), required

periodSeconds specifies the window of time for which the policy should hold true. PeriodSeconds must be greater than zero and less than or equal to 1800 (30 min).

behavior.scaleDown.selectPolicy (string)

selectPolicy is used to specify which policy should be used. If not set, the default value Max is used.

behavior.scaleDown.stabilizationWindowSeconds (int32)

stabilizationWindowSeconds is the number of seconds for which past recommendations should be considered while scaling up or scaling down. StabilizationWindowSeconds must be greater than or equal to zero and less than or equal to 3600 (one hour). If not set, use the default values: - For scale up: 0 (i.e. no stabilization is done). - For scale down: 300 (i.e. the stabilization window is 300 seconds long).

behavior.scaleDown.tolerance (Quantity)

tolerance is the tolerance on the ratio between the current and desired metric value under which no updates are made to the desired number of replicas (e.g. 0.01 for 1%). Must be greater than or equal to zero. If not set, the default cluster-wide tolerance is applied (by default 10%).

For example, if autoscaling is configured with a memory consumption target of 100Mi, and scale-down and scale-up tolerances of 5% and 1% respectively, scaling will be triggered when the actual consumption falls below 95Mi or exceeds 101Mi.

This is an alpha field and requires enabling the HPAConfigurableTolerance feature gate.

behavior.scaleUp (HPAScalingRules)

scaleUp is scaling policy for scaling Up. If not set, the default value is the higher of:

increase no more than 4 pods per 60 seconds
double the number of pods per 60 seconds No stabilization is used.
*HPAScalingRules configures the scaling behavior for one direction via scaling Policy Rules and a configurable metric tolerance.

Scaling Policy Rules are applied after calculating DesiredReplicas from metrics for the HPA. They can limit the scaling velocity by specifying scaling policies. They can prevent flapping by specifying the stabilization window, so that the number of replicas is not set instantly, instead, the safest value from the stabilization window is chosen.

The tolerance is applied to the metric values and prevents scaling too eagerly for small metric variations. (Note that setting a tolerance requires enabling the alpha HPAConfigurableTolerance feature gate.)*

behavior.scaleUp.policies ([]HPAScalingPolicy)

Atomic: will be replaced during a merge

policies is a list of potential scaling polices which can be used during scaling. If not set, use the default values: - For scale up: allow doubling the number of pods, or an absolute change of 4 pods in a 15s window. - For scale down: allow all pods to be removed in a 15s window.

HPAScalingPolicy is a single policy which must hold true for a specified past interval.

behavior.scaleUp.policies.type (string), required

type is used to specify the scaling policy.

behavior.scaleUp.policies.value (int32), required

value contains the amount of change which is permitted by the policy. It must be greater than zero

behavior.scaleUp.policies.periodSeconds (int32), required

periodSeconds specifies the window of time for which the policy should hold true. PeriodSeconds must be greater than zero and less than or equal to 1800 (30 min).

behavior.scaleUp.selectPolicy (string)

selectPolicy is used to specify which policy should be used. If not set, the default value Max is used.

behavior.scaleUp.stabilizationWindowSeconds (int32)

stabilizationWindowSeconds is the number of seconds for which past recommendations should be considered while scaling up or scaling down. StabilizationWindowSeconds must be greater than or equal to zero and less than or equal to 3600 (one hour). If not set, use the default values: - For scale up: 0 (i.e. no stabilization is done). - For scale down: 300 (i.e. the stabilization window is 300 seconds long).

behavior.scaleUp.tolerance (Quantity)

tolerance is the tolerance on the ratio between the current and desired metric value under which no updates are made to the desired number of replicas (e.g. 0.01 for 1%). Must be greater than or equal to zero. If not set, the default cluster-wide tolerance is applied (by default 10%).

For example, if autoscaling is configured with a memory consumption target of 100Mi, and scale-down and scale-up tolerances of 5% and 1% respectively, scaling will be triggered when the actual consumption falls below 95Mi or exceeds 101Mi.

This is an alpha field and requires enabling the HPAConfigurableTolerance feature gate.

metrics ([]MetricSpec)

Atomic: will be replaced during a merge

metrics contains the specifications for which to use to calculate the desired replica count (the maximum replica count across all metrics will be used). The desired replica count is calculated multiplying the ratio between the target value and the current value by the current number of pods. Ergo, metrics used must decrease as the pod count is increased, and vice-versa. See the individual metric source types for more information about how each type of metric must respond. If not set, the default metric will be set to 80% average CPU utilization.

MetricSpec specifies how to scale based on a single metric (only type and one other matching field should be set at once).

metrics.type (string), required

type is the type of metric source. It should be one of "ContainerResource", "External", "Object", "Pods" or "Resource", each mapping to a matching field in the object.

metrics.containerResource (ContainerResourceMetricSource)

containerResource refers to a resource metric (such as those specified in requests and limits) known to Kubernetes describing a single container in each pod of the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

ContainerResourceMetricSource indicates how to scale on a resource metric known to Kubernetes, as specified in requests and limits, describing each pod in the current scale target (e.g. CPU or memory). The values will be averaged together before being compared to the target. Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source. Only one "target" type should be set.

metrics.containerResource.container (string), required

container is the name of the container in the pods of the scaling target

metrics.containerResource.name (string), required

name is the name of the resource in question.

metrics.containerResource.target (MetricTarget), required

target specifies the target value for the given metric

MetricTarget defines the target value, average value, or average utilization of a specific metric

metrics.containerResource.target.type (string), required

type represents whether the metric type is Utilization, Value, or AverageValue

metrics.containerResource.target.averageUtilization (int32)

averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

metrics.containerResource.target.averageValue (Quantity)

averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

metrics.containerResource.target.value (Quantity)

value is the target value of the metric (as a quantity).

metrics.external (ExternalMetricSource)

external refers to a global metric that is not associated with any Kubernetes object. It allows autoscaling based on information coming from components running outside of cluster (for example length of queue in cloud messaging service, or QPS from loadbalancer running outside of cluster).

ExternalMetricSource indicates how to scale on a metric not associated with any Kubernetes object (for example length of queue in cloud messaging service, or QPS from loadbalancer running outside of cluster).

metrics.external.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

metrics.external.metric.name (string), required

name is the name of the given metric

metrics.external.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

metrics.external.target (MetricTarget), required

target specifies the target value for the given metric

MetricTarget defines the target value, average value, or average utilization of a specific metric

metrics.external.target.type (string), required

type represents whether the metric type is Utilization, Value, or AverageValue

metrics.external.target.averageUtilization (int32)

averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

metrics.external.target.averageValue (Quantity)

averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

metrics.external.target.value (Quantity)

value is the target value of the metric (as a quantity).

metrics.object (ObjectMetricSource)

object refers to a metric describing a single kubernetes object (for example, hits-per-second on an Ingress object).

ObjectMetricSource indicates how to scale on a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).

metrics.object.describedObject (CrossVersionObjectReference), required

describedObject specifies the descriptions of a object,such as kind,name apiVersion

CrossVersionObjectReference contains enough information to let you identify the referred resource.

metrics.object.describedObject.kind (string), required

kind is the kind of the referent; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds

metrics.object.describedObject.name (string), required

name is the name of the referent; More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names

metrics.object.describedObject.apiVersion (string)

apiVersion is the API version of the referent

metrics.object.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

metrics.object.metric.name (string), required

name is the name of the given metric

metrics.object.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

metrics.object.target (MetricTarget), required

target specifies the target value for the given metric

MetricTarget defines the target value, average value, or average utilization of a specific metric

metrics.object.target.type (string), required

type represents whether the metric type is Utilization, Value, or AverageValue

metrics.object.target.averageUtilization (int32)

averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

metrics.object.target.averageValue (Quantity)

averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

metrics.object.target.value (Quantity)

value is the target value of the metric (as a quantity).

metrics.pods (PodsMetricSource)

pods refers to a metric describing each pod in the current scale target (for example, transactions-processed-per-second). The values will be averaged together before being compared to the target value.

PodsMetricSource indicates how to scale on a metric describing each pod in the current scale target (for example, transactions-processed-per-second). The values will be averaged together before being compared to the target value.

metrics.pods.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

metrics.pods.metric.name (string), required

name is the name of the given metric

metrics.pods.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

metrics.pods.target (MetricTarget), required

target specifies the target value for the given metric

MetricTarget defines the target value, average value, or average utilization of a specific metric

metrics.pods.target.type (string), required

type represents whether the metric type is Utilization, Value, or AverageValue

metrics.pods.target.averageUtilization (int32)

averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

metrics.pods.target.averageValue (Quantity)

averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

metrics.pods.target.value (Quantity)

value is the target value of the metric (as a quantity).

metrics.resource (ResourceMetricSource)

resource refers to a resource metric (such as those specified in requests and limits) known to Kubernetes describing each pod in the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

ResourceMetricSource indicates how to scale on a resource metric known to Kubernetes, as specified in requests and limits, describing each pod in the current scale target (e.g. CPU or memory). The values will be averaged together before being compared to the target. Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source. Only one "target" type should be set.

metrics.resource.name (string), required

name is the name of the resource in question.

metrics.resource.target (MetricTarget), required

target specifies the target value for the given metric

MetricTarget defines the target value, average value, or average utilization of a specific metric

metrics.resource.target.type (string), required

type represents whether the metric type is Utilization, Value, or AverageValue

metrics.resource.target.averageUtilization (int32)

averageUtilization is the target value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods. Currently only valid for Resource metric source type

metrics.resource.target.averageValue (Quantity)

averageValue is the target value of the average of the metric across all relevant pods (as a quantity)

metrics.resource.target.value (Quantity)

value is the target value of the metric (as a quantity).

HorizontalPodAutoscalerStatus
HorizontalPodAutoscalerStatus describes the current status of a horizontal pod autoscaler.

desiredReplicas (int32), required

desiredReplicas is the desired number of replicas of pods managed by this autoscaler, as last calculated by the autoscaler.

conditions ([]HorizontalPodAutoscalerCondition)

Patch strategy: merge on key type

Map: unique values on key type will be kept during a merge

conditions is the set of conditions required for this autoscaler to scale its target, and indicates whether or not those conditions are met.

HorizontalPodAutoscalerCondition describes the state of a HorizontalPodAutoscaler at a certain point.

conditions.status (string), required

status is the status of the condition (True, False, Unknown)

conditions.type (string), required

type describes the current condition

conditions.lastTransitionTime (Time)

lastTransitionTime is the last time the condition transitioned from one status to another

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

conditions.message (string)

message is a human-readable explanation containing details about the transition

conditions.reason (string)

reason is the reason for the condition's last transition.

currentMetrics ([]MetricStatus)

Atomic: will be replaced during a merge

currentMetrics is the last read state of the metrics used by this autoscaler.

MetricStatus describes the last-read state of a single metric.

currentMetrics.type (string), required

type is the type of metric source. It will be one of "ContainerResource", "External", "Object", "Pods" or "Resource", each corresponds to a matching field in the object.

currentMetrics.containerResource (ContainerResourceMetricStatus)

container resource refers to a resource metric (such as those specified in requests and limits) known to Kubernetes describing a single container in each pod in the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

ContainerResourceMetricStatus indicates the current value of a resource metric known to Kubernetes, as specified in requests and limits, describing a single container in each pod in the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

currentMetrics.containerResource.container (string), required

container is the name of the container in the pods of the scaling target

currentMetrics.containerResource.current (MetricValueStatus), required

current contains the current value for the given metric

MetricValueStatus holds the current value for a metric

currentMetrics.containerResource.current.averageUtilization (int32)

currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

currentMetrics.containerResource.current.averageValue (Quantity)

averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

currentMetrics.containerResource.current.value (Quantity)

value is the current value of the metric (as a quantity).

currentMetrics.containerResource.name (string), required

name is the name of the resource in question.

currentMetrics.external (ExternalMetricStatus)

external refers to a global metric that is not associated with any Kubernetes object. It allows autoscaling based on information coming from components running outside of cluster (for example length of queue in cloud messaging service, or QPS from loadbalancer running outside of cluster).

ExternalMetricStatus indicates the current value of a global metric not associated with any Kubernetes object.

currentMetrics.external.current (MetricValueStatus), required

current contains the current value for the given metric

MetricValueStatus holds the current value for a metric

currentMetrics.external.current.averageUtilization (int32)

currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

currentMetrics.external.current.averageValue (Quantity)

averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

currentMetrics.external.current.value (Quantity)

value is the current value of the metric (as a quantity).

currentMetrics.external.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

currentMetrics.external.metric.name (string), required

name is the name of the given metric

currentMetrics.external.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

currentMetrics.object (ObjectMetricStatus)

object refers to a metric describing a single kubernetes object (for example, hits-per-second on an Ingress object).

ObjectMetricStatus indicates the current value of a metric describing a kubernetes object (for example, hits-per-second on an Ingress object).

currentMetrics.object.current (MetricValueStatus), required

current contains the current value for the given metric

MetricValueStatus holds the current value for a metric

currentMetrics.object.current.averageUtilization (int32)

currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

currentMetrics.object.current.averageValue (Quantity)

averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

currentMetrics.object.current.value (Quantity)

value is the current value of the metric (as a quantity).

currentMetrics.object.describedObject (CrossVersionObjectReference), required

DescribedObject specifies the descriptions of a object,such as kind,name apiVersion

CrossVersionObjectReference contains enough information to let you identify the referred resource.

currentMetrics.object.describedObject.kind (string), required

kind is the kind of the referent; More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds

currentMetrics.object.describedObject.name (string), required

name is the name of the referent; More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names

currentMetrics.object.describedObject.apiVersion (string)

apiVersion is the API version of the referent

currentMetrics.object.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

currentMetrics.object.metric.name (string), required

name is the name of the given metric

currentMetrics.object.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

currentMetrics.pods (PodsMetricStatus)

pods refers to a metric describing each pod in the current scale target (for example, transactions-processed-per-second). The values will be averaged together before being compared to the target value.

PodsMetricStatus indicates the current value of a metric describing each pod in the current scale target (for example, transactions-processed-per-second).

currentMetrics.pods.current (MetricValueStatus), required

current contains the current value for the given metric

MetricValueStatus holds the current value for a metric

currentMetrics.pods.current.averageUtilization (int32)

currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

currentMetrics.pods.current.averageValue (Quantity)

averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

currentMetrics.pods.current.value (Quantity)

value is the current value of the metric (as a quantity).

currentMetrics.pods.metric (MetricIdentifier), required

metric identifies the target metric by name and selector

MetricIdentifier defines the name and optionally selector for a metric

currentMetrics.pods.metric.name (string), required

name is the name of the given metric

currentMetrics.pods.metric.selector (LabelSelector)

selector is the string-encoded form of a standard kubernetes label selector for the given metric When set, it is passed as an additional parameter to the metrics server for more specific metrics scoping. When unset, just the metricName will be used to gather metrics.

currentMetrics.resource (ResourceMetricStatus)

resource refers to a resource metric (such as those specified in requests and limits) known to Kubernetes describing each pod in the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

ResourceMetricStatus indicates the current value of a resource metric known to Kubernetes, as specified in requests and limits, describing each pod in the current scale target (e.g. CPU or memory). Such metrics are built in to Kubernetes, and have special scaling options on top of those available to normal per-pod metrics using the "pods" source.

currentMetrics.resource.current (MetricValueStatus), required

current contains the current value for the given metric

MetricValueStatus holds the current value for a metric

currentMetrics.resource.current.averageUtilization (int32)

currentAverageUtilization is the current value of the average of the resource metric across all relevant pods, represented as a percentage of the requested value of the resource for the pods.

currentMetrics.resource.current.averageValue (Quantity)

averageValue is the current value of the average of the metric across all relevant pods (as a quantity)

currentMetrics.resource.current.value (Quantity)

value is the current value of the metric (as a quantity).

currentMetrics.resource.name (string), required

name is the name of the resource in question.

currentReplicas (int32)

currentReplicas is current number of replicas of pods managed by this autoscaler, as last seen by the autoscaler.

lastScaleTime (Time)

lastScaleTime is the last time the HorizontalPodAutoscaler scaled the number of pods, used by the autoscaler to control how often the number of pods is changed.

Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON. Wrappers are provided for many of the factory methods that the time package offers.

observedGeneration (int64)

observedGeneration is the most recent generation observed by this autoscaler.




## Example

```kotlin
package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/certificate-signing-request-v1/
 *
 * CertificateSigningRequest objects provide a mechanism to obtain x509
 * certificates by submitting a certificate signing request, and having it
 * asynchronously approved and issued.
 *
 * Kubelets use this API to obtain:
 *
 * 1. client certificates to authenticate to kube-apiserver
 *    (with the "kubernetes.io/kube-apiserver-client-kubelet" signerName).
 * 2. serving certificates for TLS endpoints kube-apiserver can connect to securely
 *    (with the "kubernetes.io/kubelet-serving" signerName).
 *
 * This API can be used to request client certificates to authenticate to kube-apiserver
 * (with the "kubernetes.io/kube-apiserver-client" signerName), or to obtain
 * certificates from custom non-Kubernetes signers.
 *
 * apiVersion: certificates.k8s.io/v1
 *
 * import "k8s.io/api/certificates/v1"
 */
@GeneratedDsl
data class CertificateSigningRequestV2(
    @DefaultValue(
        "KAPIVersion.CertificatesV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec contains the certificate request, and is immutable after creation.
     * Only the request, signerName, expirationSeconds, and usages fields can be set on creation.
     * Other fields are derived by Kubernetes and cannot be modified by users.
     */
    val spec: CertificateSigningRequestSpec,
    /**
     * status contains information about whether the request is approved or denied, and the
     * certificate issued by the signer, or the failure condition indicating signer failure.
     */
    val status: CertificateSigningRequestStatus? = null
) : AuthenticationResource<CertificateSigningRequestV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

```