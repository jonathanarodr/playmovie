import org.gradle.api.Project

@Suppress("UNCHECKED_CAST")
fun <T> Project.findExtension(name: String): T {
    return extensions.findByName(name) as T
}