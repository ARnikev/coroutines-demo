import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.0.M3"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
	kotlin("kapt") version "1.3.31"
}

group = "ru.nikeshkin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	compileOnly("org.springframework.boot:spring-boot-configuration-processor")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.data:spring-data-r2dbc:1.0.0.M2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-M1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.3.0-M1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.0-M1")

	implementation("com.squareup.retrofit2:retrofit:2.6.0")
	implementation("com.squareup.retrofit2:converter-jackson:2.6.0")

    implementation("io.r2dbc:r2dbc-postgresql:1.0.0.M7")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
