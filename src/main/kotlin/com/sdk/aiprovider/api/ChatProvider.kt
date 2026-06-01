package com.sdk.aiprovider.api

import kotlinx.coroutines.flow.Flow

/**
 * Interface for chat/conversation capabilities.
 * 
 * Platform: Android
 * Category: AI Provider Interface
 * Supports: Streaming, Multi-turn conversations
 */
interface ChatProvider : AIProvider {
    /**
     * Send a chat message and get a response.
     *
     * @param request The chat request
     * @return The chat response
     */
    suspend fun chat(request: ChatRequest): ChatResponse

    /**
     * Send a chat message and stream the response.
     *
     * @param request The chat request
     * @return A flow of response chunks
     */
    fun chatStream(request: ChatRequest): Flow<ChatChunk>
}

/**
 * A chat message.
 */
data class ChatMessage(
    val role: ChatRole,
    val content: String
)

/**
 * Chat role enumeration.
 */
enum class ChatRole {
    USER,
    ASSISTANT,
    SYSTEM
}

/**
 * Chat request parameters.
 */
data class ChatRequest(
    val messages: List<ChatMessage>,
    val model: String? = null,
    val temperature: Float = 0.7f,
    val maxTokens: Int? = null,
    val topP: Float = 1.0f,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Chat response from provider.
 */
data class ChatResponse(
    val id: String,
    val message: ChatMessage,
    val model: String,
    val usage: TokenUsage = TokenUsage(),
    val metadata: Map<String, String> = emptyMap()
)

/**
 * A chunk of streamed response.
 */
data class ChatChunk(
    val id: String,
    val delta: String,
    val isEnd: Boolean = false
)

/**
 * Token usage statistics.
 */
data class TokenUsage(
    val promptTokens: Int = 0,
    val completionTokens: Int = 0,
    val totalTokens: Int = 0
)
