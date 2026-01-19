import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "2.0.27"
    kotlin("jvm") version "2.3.0"
}

taboolib {
    env {
        install(
            Basic,
            Metrics,
            Database,
            CommandHelper,
            JavaScript,
            Bukkit,
            BukkitUI,
            BukkitHook,
            BukkitUtil
        )
    }
    description {
        contributors {
            name("CPJiNan")
        }
    }
    version { taboolib = "6.2.4-65252583" }
}

repositories {
    mavenCentral()
    // PlaceholderAPI
    maven("https://repo.extendedclip.com/releases/")
}

dependencies {
    compileOnly("ink.ptms.core:v11200:11200")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    // PlaceholderAPI
    compileOnly("me.clip:placeholderapi:2.11.6")
    // nashorn
    compileOnly("org.openjdk.nashorn:nashorn-core:15.4")
    // OkHttp
    compileOnly("com.squareup.okhttp3:okhttp:5.3.2")
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
        freeCompilerArgs.set(listOf("-Xjvm-default=all"))
    }
}