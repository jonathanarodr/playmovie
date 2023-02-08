package br.com.jonathanarodr.playmovie.gradlebuild

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

@Suppress("UNCHECKED_CAST")
fun <T> Project.findExtension(name: String, block: T.() -> Unit) {
    (extensions.findByName(name) as? T)?.run(block)
}

fun Project.android(block: BaseExtension.() -> Unit) {
    findExtension("android", block)
}

fun Project.androidComponents(block: ApplicationAndroidComponentsExtension.() -> Unit) {
    findExtension("androidComponents", block)
}

fun <T> CommonExtension<*, *, *, *>.findExtension(name: String, block: T.() -> Unit) {
    (this as ExtensionAware).extensions.configure(name, block)
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    findExtension("kotlinOptions", block)
}
