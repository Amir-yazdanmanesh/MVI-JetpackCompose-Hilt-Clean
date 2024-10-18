import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 33
    const val minSdk = 23
    const val targetSdk = 33
    const val applicationId = "com.yazdanmanesh.githubcompose"
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"

    val javaJvmTarget = JavaVersion.VERSION_11.toString()
    val javaCompatibility = JavaVersion.VERSION_11
}