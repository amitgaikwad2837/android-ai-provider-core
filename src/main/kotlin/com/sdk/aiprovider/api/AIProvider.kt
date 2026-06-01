package com.sdk.aiprovider.api

/**
 * Core AI Provider interface that all AI providers must implement.
 *
 * Platform: Android
 * Category: Shared Foundation
 * Supports: Local AI, Cloud APIs, Custom Endpoints
 *
 * This is the foundation of the pluggable AI provider architecture.
 * Implementations can be:
 * - Local AI (MLC LLM, llama.cpp)
 * - Cloud-based (OpenAI, Gemini, Anthropic)
 * - Self-hosted (Ollama, custom endpoints)
 */
interface AIProvider {
    /**
     * Verify that the provider is properly configured and accessible.
     *
     * @return true if the provider is ready to use
     */
    suspend fun isAvailable(): Boolean

    /**
     * Initialize the provider (load models, connect to API, etc).
     */
    suspend fun initialize()

    /**
     * Clean up resources.
     */
    suspend fun shutdown()

    /**
     * Get metadata about this provider.
     */
    fun getMetadata(): ProviderMetadata
}

/**
 * Metadata describing an AI provider.
 */
data class ProviderMetadata(
    val id: String,
    val name: String,
    val version: String,
    val isLocal: Boolean,
    val supportsStreaming: Boolean,
    val capabilityFlags: Set<ProviderCapability> = emptySet()
)

/**
 * Capability flags indicating what features a provider supports.
 */
enum class ProviderCapability {
    CHAT,
    TEXT_COMPLETION,
    EMBEDDING,
    OCR,
    VISION,
    SPEECH_TO_TEXT,
    TEXT_TO_SPEECH,
    CUSTOM
}
