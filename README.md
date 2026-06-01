# Android AI Provider Core

## Overview

Pluggable AI provider framework for Android SDKs

## Installation

Add the Maven dependency once artifacts are published:

~~~kotlin
dependencies {
  implementation("io.github.amitgaikwad2837:android-ai-provider-core:1.0.0")
}
~~~

## Integration Example

~~~kotlin
import com.sdk.aiprovider.api.AIProviderRegistry

class ExampleUsage {
    fun setup() {
        val sdk = AIProviderRegistry()
        // Configure and call SDK APIs here based on your app flow.
    }
}
~~~

## Feature Highlights

- Feature list is documented in source and metadata.

## Android Permissions

- Permission requirements depend on host app usage.

## Development

~~~bash
./gradlew build
./gradlew test
~~~

## License

MIT
