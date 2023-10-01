plugins {
    id("java")
    id("application")
}

group = "com.up202206970"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //implementation group("com.googlecode.lanterna"); name("lanterna"); version("3.1.1")
    implementation("com.googlecode.lanterna:lanterna:3.1.1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("Application")
}




