@file:Suppress("TooManyFunctions")

package br.com.jonathanarodr.playmovie.gradlebuild

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

fun PluginManager.apply(vararg plugins: String) {
    plugins.forEach(::apply)
}

val ExtensionContainer.libs: VersionCatalog
    get() = run {
        getByType<VersionCatalogsExtension>().named("libs")
    }

fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandlerScope.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandlerScope.androidTestUtil(dependencyNotation: Any): Dependency? =
    add("androidTestUtil", dependencyNotation)

fun DependencyHandlerScope.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandlerScope.detektPlugins(dependencyNotation: Any): Dependency? =
    add("detektPlugins", dependencyNotation)

fun DependencyHandlerScope.kover(dependencyNotation: Any): Dependency? =
    add("kover", dependencyNotation)
