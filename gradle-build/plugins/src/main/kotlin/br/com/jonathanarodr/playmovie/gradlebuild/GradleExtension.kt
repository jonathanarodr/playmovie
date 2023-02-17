package br.com.jonathanarodr.playmovie.gradlebuild

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

@Suppress("UNCHECKED_CAST")
fun <T> Project.findExtension(name: String, block: T.() -> Unit) {
    (extensions.findByName(name) as? T)?.run(block)
}

fun Project.android(block: BaseExtension.() -> Unit) {
    findExtension("android", block)
}

fun Project.androidComponents(block: AndroidComponentsExtension<*, *, *>.() -> Unit) {
    findExtension("androidComponents", block)
}

fun <T> CommonExtension<*, *, *, *>.findExtension(name: String, block: T.() -> Unit) {
    (this as ExtensionAware).extensions.configure(name, block)
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    findExtension("kotlinOptions", block)
}

fun PluginManager.apply(vararg plugins: String) {
    plugins.forEach {
        apply(it)
    }
}

fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandlerScope.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandlerScope.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandlerScope.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

fun DependencyHandlerScope.detektPlugins(dependencyNotation: Any): Dependency? =
    add("detektPlugins", dependencyNotation)
