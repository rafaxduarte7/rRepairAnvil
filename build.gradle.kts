plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

group = "com.rafaxplugins"
version = "0.1-ALPHA"

repositories {
    mavenLocal()
    mavenCentral()

    maven("https://jitpack.io")
    maven("https://maven.elmakers.com/repository/")
    maven("https://repo.codemc.io/repository/maven-public/")
}

dependencies {
    compileOnly(fileTree("libs") { include("*.jar") })

    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

bukkit {
    name = "rRepairAnvil"
    main = "com.rafaxplugins.repairanvil.RepairAnvilPlugin"
    author = "Rafax"
    version = "1.0.1"
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveFileName.set("rRepairAnvil.jar")
        destinationDirectory = file("C:\\Users\\rafax\\Documents\\Portfolio\\Servidor Teste\\plugins")
    }
}