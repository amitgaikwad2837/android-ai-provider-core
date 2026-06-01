package com.sdk.aiprovider.api

import kotlinx.coroutines.flow.Flow

/**
 * Interface for speech-to-text and text-to-speech capabilities.
 *
 * Platform: Android
 * Category: AI Provider Interface
 * Supports: Real-time streaming, Batch processing
 */
interface SpeechProvider : AIProvider {
    /**
     * Convert speech audio to text.
     *
     * @param request The speech recognition request
     * @return The recognition response with transcript
     */
    suspend fun speechToText(request: SpeechToTextRequest): SpeechToTextResponse

    /**
     * Stream speech recognition for real-time use cases.
     *
     * @param audioStream Flow of audio chunks
     * @return Flow of recognition results
     */
    fun speechToTextStream(audioStream: Flow<AudioChunk>): Flow<RecognitionChunk>

    /**
     * Convert text to speech.
     *
     * @param request The text-to-speech request
     * @return The audio response
     */
    suspend fun textToSpeech(request: TextToSpeechRequest): TextToSpeechResponse
}

/**
 * Speech-to-text request.
 */
data class SpeechToTextRequest(
    val audioData: ByteArray,
    val language: String = "en-US",
    val metadata: Map<String, String> = emptyMap()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SpeechToTextRequest) return false
        if (!audioData.contentEquals(other.audioData)) return false
        if (language != other.language) return false
        if (metadata != other.metadata) return false
        return true
    }

    override fun hashCode(): Int {
        var result = audioData.contentHashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + metadata.hashCode()
        return result
    }
}

/**
 * A chunk of audio data.
 */
data class AudioChunk(
    val data: ByteArray,
    val isEnd: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AudioChunk) return false
        if (!data.contentEquals(other.data)) return false
        if (isEnd != other.isEnd) return false
        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + isEnd.hashCode()
        return result
    }
}

/**
 * Speech-to-text response.
 */
data class SpeechToTextResponse(
    val transcript: String,
    val confidence: Float = 0f,
    val language: String = "en-US",
    val isFinal: Boolean = false,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * A chunk of recognition result (for streaming).
 */
data class RecognitionChunk(
    val transcript: String,
    val confidence: Float = 0f,
    val isFinal: Boolean = false
)

/**
 * Text-to-speech request.
 */
data class TextToSpeechRequest(
    val text: String,
    val language: String = "en-US",
    val voice: String? = null,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * Text-to-speech response.
 */
data class TextToSpeechResponse(
    val audioData: ByteArray,
    val mimeType: String = "audio/mpeg",
    val metadata: Map<String, String> = emptyMap()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextToSpeechResponse) return false
        if (!audioData.contentEquals(other.audioData)) return false
        if (mimeType != other.mimeType) return false
        if (metadata != other.metadata) return false
        return true
    }

    override fun hashCode(): Int {
        var result = audioData.contentHashCode()
        result = 31 * result + mimeType.hashCode()
        result = 31 * result + metadata.hashCode()
        return result
    }
}
