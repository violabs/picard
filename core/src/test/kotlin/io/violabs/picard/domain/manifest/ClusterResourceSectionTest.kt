//package io.violabs.picard.domain.manifest
//
//
//import io.violabs.picard.FullBuildSim
//import io.violabs.picard.domain.k8sResources.cluster.apiService.APIService
//import io.violabs.picard.domain.k8sResources.cluster.apiService.APIServiceList
//import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatus
//import io.violabs.picard.domain.k8sResources.cluster.componentStatus.ComponentStatusList
//import io.violabs.picard.domain.k8sResources.cluster.event.Event
//import io.violabs.picard.domain.k8sResources.cluster.event.EventList
//import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddress
//import io.violabs.picard.domain.k8sResources.cluster.ipAddress.IPAddressList
//import io.violabs.picard.domain.k8sResources.cluster.lease.Lease
//import io.violabs.picard.domain.k8sResources.cluster.lease.LeaseList
//import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidate
//import io.violabs.picard.domain.k8sResources.cluster.lease.candidate.LeaseCandidateList
//import io.violabs.picard.domain.k8sResources.cluster.namespace.Namespace
//import io.violabs.picard.domain.k8sResources.cluster.namespace.NamespaceList
//import io.violabs.picard.domain.k8sResources.cluster.node.Node
//import io.violabs.picard.domain.k8sResources.cluster.node.NodeList
//import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClass
//import io.violabs.picard.domain.k8sResources.cluster.runtimeClass.RuntimeClassList
//import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDR
//import io.violabs.picard.domain.k8sResources.cluster.serviceCIDR.ServiceCIDRList
//import io.violabs.picard.possibilities
//import org.junit.jupiter.api.BeforeAll
//
//class ClusterResourceSectionTest : FullBuildSim<ClusterResourceSection, ClusterResourceSectionDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            ClusterResourceSectionTest::class,
//            SUCCESS_POSSIBILITIES,
//            FAILURE_POSSIBILITIES
//        )
//
//        private val EVENT = Event(
//            eventTime = TIMESTAMP
//        )
//
//        private val LEASE_CANDIDATE = LeaseCandidate(
//            spec = LeaseCandidateSpec(
//                leaseName = PLACEHOLDER,
//                preferredStrategies = PLACEHOLDER_LIST,
//            )
//        )
//
//        private val RUNTIME_CLASS = RuntimeClass(
//            handler = PLACEHOLDER
//        )
//
//        private val SUCCESS_POSSIBILITIES = possibilities<ClusterResourceSection, ClusterResourceSectionDslBuilder> {
//            scenario {
//                id = "full"
//                given(ClusterResourceSectionDslBuilder()) {
//                    apiService { }
//                    apiServiceList {
//                        items {
//                            service { }
//                        }
//                    }
//                    componentStatus { }
//                    componentStatusList {
//                        items {
//                            status { }
//                        }
//                    }
//                    event {
//                        eventTime = TIMESTAMP
//                    }
//                    eventList {
//                        items {
//                            eventItem {
//                                eventTime = TIMESTAMP
//                            }
//                        }
//                    }
//                    ipAddress { }
//                    ipAddressList {
//                        items {
//                            address { }
//                        }
//                    }
//                    lease { }
//                    leaseList {
//                        items {
//                            leaseItem { }
//                        }
//                    }
//                    leaseCandidate {
//                        spec {
//                            leaseName = PLACEHOLDER
//                            preferredStrategies(PLACEHOLDER)
//                        }
//                    }
//                    leaseCandidateList {
//                        items {
//                            candidate {
//                                spec {
//                                    leaseName = PLACEHOLDER
//                                    preferredStrategies(PLACEHOLDER)
//                                }
//                            }
//                        }
//                    }
//                    namespace { }
//                    namespaceList {
//                        items {
//                            name { }
//                        }
//                    }
//                    node { }
//                    nodeList {
//                        items {
//                            nodeItem { }
//                        }
//                    }
//                    runtimeClass { handler = PLACEHOLDER }
//                    runtimeClassList {
//                        items {
//                            runtime { handler = PLACEHOLDER }
//                        }
//                    }
//                    serviceCidr {
//                    }
//                    serviceCIDRList {
//                        items {
//                            serviceCIDRItem { }
//                        }
//                    }
//                    expected = ClusterResourceSection(
//                        resources = listOf(
//                            APIService(),
//                            APIServiceList(items = listOf(APIService())),
//                            ComponentStatus(),
//                            ComponentStatusList(items = listOf(ComponentStatus())),
//                            EVENT,
//                            EventList(items = listOf(EVENT)),
//                            IPAddress(),
//                            IPAddressList(items = listOf(IPAddress())),
//                            Lease(),
//                            LEASE_CANDIDATE,
//                            LeaseCandidateList(items = listOf(LEASE_CANDIDATE)),
//                            LeaseList(items = listOf(Lease())),
//                            Namespace(),
//                            NamespaceList(items = listOf(Namespace())),
//                            Node(),
//                            NodeList(items = listOf(Node())),
//                            RUNTIME_CLASS,
//                            RuntimeClassList(items = listOf(RUNTIME_CLASS)),
//                            ServiceCIDR(),
//                            ServiceCIDRList(items = listOf(ServiceCIDR()))
//                        )
//                    )
//                }
//            }
//        }
//
//        private val FAILURE_POSSIBILITIES = possibilities<ClusterResourceSection, ClusterResourceSectionDslBuilder> {
//            requireNotEmptyScenario("resources") {
//                given(ClusterResourceSectionDslBuilder())
//            }
//        }
//    }
//}