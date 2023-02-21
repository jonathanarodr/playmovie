package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maybeCreate
import java.util.Locale

private data class DeviceConfig(
    val device: String,
    val apiLevel: Int,
    val systemImageSource: String,
) {
    val taskName = buildString {
        append(device.toLowerCase(Locale.ROOT).replace(" ", ""))
        append("api")
        append(apiLevel)
        append(systemImageSource.replace("-", ""))
    }
}

internal fun Project.configureDeviceManagerConvention(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val deviceConfigs = listOf(
        DeviceConfig("Pixel 6", AndroidConfig.SDK_TARGET, "aosp-atd"),
    )

    commonExtension.testOptions {
        managedDevices {
            devices {
                deviceConfigs.forEach { deviceConfig ->
                    maybeCreate<ManagedVirtualDevice>(deviceConfig.taskName).apply {
                        device = deviceConfig.device
                        apiLevel = deviceConfig.apiLevel
                        systemImageSource = deviceConfig.systemImageSource
                    }
                }
            }
        }
    }
}
