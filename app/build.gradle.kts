plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt.android)
}

android {
	namespace = "com.example.starwarscharacters"
	compileSdk {
		version = release(36)
	}

	defaultConfig {
		applicationId = "com.example.starwarscharacters"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	buildFeatures {
		buildConfig = true
		compose = true
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.lifecycle.runtime.compose)
	implementation(libs.androidx.compose.foundation.layout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	debugImplementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.androidx.compose.ui.test.manifest)

	val nav_version = "2.8.0"

	// --- Room ---
	implementation(libs.hilt.android)
	ksp(libs.hilt.compiler)

	// Room
	implementation(libs.room.runtime)
	implementation(libs.room.ktx)
	ksp(libs.room.compiler)
	// Интеграция Hilt с Navigation и Compose (если используете)
	implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

	// Для Jetpack Compose Navigation
	implementation("androidx.navigation:navigation-compose:$nav_version")

	//coil
	implementation("io.coil-kt.coil3:coil-compose:3.4.0")
	implementation("io.coil-kt.coil3:coil-network-okhttp:3.4.0")

	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
	implementation("androidx.compose.foundation:foundation:1.6.0")
}