package io.violabs.picard.domain

import io.violabs.picard.common.enumSetOf
import java.util.EnumSet

enum class APIGroup(private val ref: String? = null, val versions: EnumSet<Version>) {
    ADMISSION_REGISTRATION("admissionregistration.k8s.io", enumSetOf(Version.V1, Version.V1BETA1, Version.V1ALPHA1)),
    API_EXTENSIONS("apiextensions.k8s.io", Version.V1.asSingleSet()),
    APPS(versions = Version.V1.asSingleSet()),
    AUTHENTICATION("authentication.k8s.io", enumSetOf(Version.V1, Version.V1BETA1)),
    AUTHORIZATION("authorization.k8s.io", Version.V1.asSingleSet()),
    AUTOSCALING(versions = enumSetOf(Version.V2, Version.V1)),

    BATCH(versions = Version.V1.asSingleSet()),

    CERTIFICATES("certificates.k8s.io", enumSetOf(Version.V1, Version.V1ALPHA1)),
    COORDINATION("coordination.k8s.io", enumSetOf(Version.V1, Version.V1ALPHA2)),
    CORE(versions = Version.V1.asSingleSet()),

    DISCOVERY("discovery.k8s.io", Version.V1.asSingleSet()),

    EVENTS("events.k8s.io", Version.V1.asSingleSet()),

    FLOW_CONTROL("flowcontrol.apiserver.k8s.io", Version.V1.asSingleSet()),

    INTERNAL_API_SERVER("internal.apiserver.k8s.io", Version.V1ALPHA1.asSingleSet()),

    NETWORKING("networking.k8s.io", enumSetOf(Version.V1, Version.V1BETA1)),
    NODE("node.k8s.io", Version.V1.asSingleSet()),

    POLICY("policy", Version.V1.asSingleSet()),

    RBAC("rbac.authorization.k8s.io", Version.V1.asSingleSet()),
    RESOURCE("resource.k8s.io", enumSetOf(Version.V1BETA1, Version.V1ALPHA3)),

    SCHEDULING("scheduling.k8s.io", Version.V1.asSingleSet()),
    STORAGE("storage.k8s.io", enumSetOf(Version.V1, Version.V1BETA1, Version.V1ALPHA1)),
    STORAGE_MIGRATION("storagemigration.k8s.io", Version.V1ALPHA1.asSingleSet());

    override fun toString(): String = ref ?: name.lowercase()

    enum class Version {
        V1,
        V1BETA1,
        V1ALPHA1,
        V1ALPHA2,
        V1ALPHA3,
        V2;

        fun asSingleSet(): EnumSet<Version> = enumSetOf(this)

        override fun toString(): String = name.lowercase()
    }
}