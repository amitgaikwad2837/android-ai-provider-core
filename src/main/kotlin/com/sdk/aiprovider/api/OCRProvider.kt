package com.sdk.aiprovider.api

/**
 * Interface for OCR (Optical Character Recognition) capabilities.
 *
 * Platform: Android
 * Category: AI Provider Interface
 * Supports: Multi-language, Structured extraction
 */
interface OCRProvider : AIProvider {
    /**
     * Extract text from an image.
     *
     * @param request The OCR request
     * @return The OCR response with extracted text
     */
    suspend fun extractText(request: OCRRequest): OCRResponse
}

/**
 * OCR request parameters.
 */
data class OCRRequest(
    val imageData: ByteArray,
    val language: String = "eng",
    val metadata: Map<String, String> = emptyMap()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OCRRequest) return false
        if (!imageData.contentEquals(other.imageData)) return false
        if (language != other.language) return false
        if (metadata != other.metadata) return false
        return true
    }

    override fun hashCode(): Int {
        var result = imageData.contentHashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + metadata.hashCode()
        return result
    }
}

/**
 * OCR response with extracted content.
 */
data class OCRResponse(
    val rawText: String,
    val lines: List<TextLine> = emptyList(),
    val confidence: Float = 0f,
    val language: String = "eng",
    val metadata: Map<String, String> = emptyMap()
)

/**
 * A line of recognized text.
 */
data class TextLine(
    val text: String,
    val confidence: Float = 0f,
    val boundingBox: BoundingBox? = null
)

/**
 * Bounding box for a text region.
 */
data class BoundingBox(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
)
