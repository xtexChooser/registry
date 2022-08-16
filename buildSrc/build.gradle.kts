plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.7.20-Beta"
    kotlin("plugin.serialization") version "1.7.20-Beta"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.mamoe.yamlkt:yamlkt:0.12.0")
    implementation("com.github.seancfoley:ipaddress:5.3.4")
}
