> Mirror Policy: This repository is an automated mirror of the monorepo https://github.com/amitgaikwad2837/SDK.
>
> Do not push changes directly here. All changes must be made in the SDK monorepo and synced by workflow.
> Pull requests opened in this repo are for review visibility only and may be overwritten by the next sync.
# Android AI Provider Core

## 📦 Registry & Repository

- **Maven Central**: [io.github.amitgaikwad2837:android-ai-provider-core](https://central.sonatype.com/artifact/io.github.amitgaikwad2837/android-ai-provider-core)
- **GitHub**: [amitgaikwad2837/android-ai-provider-core](https://github.com/amitgaikwad2837/android-ai-provider-core)

## Overview

Pluggable AI provider framework that enables Android apps to work with local and cloud-based AI models. Supports multiple AI providers (OpenAI, Gemini, Claude, Ollama, MLC LLM, llama.cpp) with a unified interface.

## Installation

Add the Maven dependency:

~~~kotlin
dependencies {
  implementation("io.github.amitgaikwad2837:android-ai-provider-core:0.0.9")
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

