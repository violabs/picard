package io.violabs.picard.starCharts.grafana

import io.violabs.picard.cmd.dsl.helm.Helm

object StarChartRepoManager {
    fun addGrafana(helm: Helm) = with(helm) {
        addRepo {
            repoName = "grafana"
            repoUrl = "https://grafana.github.io/helm-charts"
        }
    }
}