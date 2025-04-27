
# Overview
This contains code DSL and core domain classes to
use for building kubernetes manifests.

# Code Structure

## Domain Classes

These are the standard objects that the builder classes create. Each interface
will require the resource to define its own `Version` interface that extends
the `APIVersion` interface to align specific versions to specific resources.

Under the `APIVersion` file, there is a `KAPIVersion` implementation that contains
its child objects within itself. These child objects are all possible version,
that extend both the `KAPIVersion` and any resource version that this could belong to.
e.g. `object V1 : KAPIVersion(), ConfigMap.Version, ConfigMapList.Version, ...`

### Resource Single

These are resources for k8s that always at least has
an apiVersion and metadata.

They extend the `K8sResource<Version>` for abstracted use in
main flow and testing.

Some will have `Spec` classes, some will have `Status` classes,
and some will have custom properties.

#### Builder

The resource class can extend up to 4 different types of classes based
on the shared functionality.

- `ResourceDSLBuilder<Resource>` - for resource with no spec or status
- `ResourceSpecDSLBuilder<Resource, Spec, Spec.Builder>` - for resource with only spec
- `ResourceStatusDSLBuilder<Resource, Status, Status.Builder>` - for resource with only status
- `ResourceSpecStatusDSLBuilder<Resource, Spec, Spec.Builder, Status, Status.Builder>` - for resource with both

#### Group

The resource will most likely have a group (builder) that the list version of this
will utilize. It extends `K8sListResource.ItemGroup<Resource, Builder>` to encapsulate
shared logic. There is only a custom defined method to take in the builder scope.

### Resource List

These are the list version of the resources. Many have required `items` property
and all extend the `K8sListResource<ResourceList.Version, Resource>`. They also
all have a builder that extends `ResourceListDSLBuilder<Resource, Resource.Builder, Resource.Group, ResourceList>`.

### Non-Resource

There are many nested objects that do not fall in the Resource category.
Some classes will have a shared top level interface for tracking and reference.
They are found in the `DevTags` class file.

#### Builder

These extend the `DSLBuilder<TargetClass>` interface to provide a `build` function
for shared usage.

#### Group

Some have groups if any builder references them in a list. Currently, it is all over the
place, but I am working towards bring the `Group` class into the main object.

## Builder Classes

### Structure Details

The builder classes use a scope based builder structure:

```kotlin
// example
class TopScope {
    fun bottomScope(scope: MutableList<String>.() -> Unit) {
        scope(mutableListOf())
    }
}

fun topScope(scope: TopScope.() -> Unit) {
    scope(TopScope())
}

// usage
topScope { 
    bottomScope { 
        add("example!")
    }
}
```

This basically passes the object into the incoming method (callback). The benefit of
kotlin is the scope moves within that text block, so for the scope of `topScope`, `this`
becomes the implementation of `TopScope`, then the scope for `bottomScope` changes the
`this` to be `mutableListOf()`. That is why we can just do `add("example!")` instead
of `it.add("example!")`, which would happen if we redefined `bottomScope` to be this instead:

```kotlin
fun bottomScope(scope: (MutableList<String>) -> Unit) {
    scope(mutableListOf())
}

bottomScope { 
    it.add("example!")
}
```

#### Special values

Any object property will be private by default and should have an individual scoped
method for that object. Exceptions are for single value objects, which can just have
the method take in the value to assign.

Additional private accessed functions are:

- List - accessed via a `vararg` method.
- Map - accessed via a `vararg` method.
- Boolean - accessed via a method with a default `true` value.

### Validation

Some of the domain objects have required fields. The builder allows for all
null, but to validate quickly, I utilized reflection to return the name of the
field, and not have to maintain changing those names.

#### Validation methods

1. **vRequireNotNull**

Takes in a `KProperty<Object>` reference, if it is private sets the 
property to accessible, and then uses kotlin's `requireNotNull` to 
throw an `IllegalArgumentException` if not correct, with the name of
the property in the exception message.

```kotlin
// example
class RequiredClass(val name: String? = null)

val example = RequiredClass()

vRequireNotNull(example::name)
```

2. **vRequireNotEmpty**

Takes in a `KProperty<List<Object>` reference, if it is private sets the
property to accessible, and then uses kotlin's `requireNotNull` to
throw an `IllegalArgumentException` if not correct, with the name of
the property in the exception message.

```kotlin
// example
class RequiredClass(val names: List<String>? = null)

val example = RequiredClass()

vRequireNotEmpty(example::names)
```

## Testing

The testing for the Domain + Builder has a very particular structure. There are 3 different
types of tests to implement based on the parent class.

- Successful Build Examples - `SuccessBuildSim<Buildable, Builder>`
- Failed Build Examples - `FailureBuildSim<Buildable, Builder>`
- Success and Failed Examples - `FullBuildSim<Buildable, Builder>`

They extend the `BuildSim<Builable, Builder>` class which in part extends `UnitSim` library.
For now, we only need to focus on the `BuildSim`.

### Build Sim

Within the companion object their are default and usable classes and values. Not
all reusable values are here, but if there are cross object values, I tend to add them here.

There are some specific defaults. There are interface and dsl versions too.

- `sharedMetadata` | `METADATA` - for any full or success that want to use the metadata 
- `sharedMetadata` | `LIST_METADATA` - for any full or success list resources
- `PLACEHOLDER` - string based
- `NOW` - local date time based
- `TIMESTAMP` - instant based
- `QUANTITY` - for any `Quantity` objects

There is also a premade companion function to setup the parameterized testing.

The child test classes can either provide: `successScenariosSet` or `failureScenariosSet`
based on what it is trying to test. It utilizes `UnitSim#setup` and `SimulationGroup` to
add test scenarios to the testing context.

The main abstract class defines 2 functions for use if the scenarios require it.
If a child class wants to use the success check, then `happy path` can be overridden.
If a child class wants to use the failure check, then `failure path` can be overridden.

### Success, Failure, and Full BuildSim

Each of these just have a default defined `happy path` and `failure path` depending.
Then any test will only have to provide the scenarios of which to test.

### Scenarios

#### Success

- minimum - the smallest filled out object with any required fields.
- full - all properties and sub properties filled out. 
- boolean - some tests require checking the false boolean option.

#### Failure

 - requireScenario - checks the required field. Have as many as needed for the remaining properties.
 - requireNotEmptyScenario - checks for required field and has at least 1 item.