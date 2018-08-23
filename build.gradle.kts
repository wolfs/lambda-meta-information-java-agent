plugins {
    id("com.gradle.build-scan").version("1.16")
}

subprojects {
    apply(plugin = "java-library")

    repositories {
        jcenter()
    }

    the<JavaPluginExtension>().apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

buildScan {
    publishAlways()
}