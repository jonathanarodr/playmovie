package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.pluginManager.apply(
            "playmovie.android.library",
            "playmovie.codestyle",
            "playmovie.codecoverage",
            "androidx.navigation.safeargs.kotlin",
        )
    }
}
