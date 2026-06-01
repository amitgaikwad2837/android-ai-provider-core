package com.sdk.aiprovider.api

/**
 * Interface for vision/image analysis capabilities.
 *
 * Platform: Android
 * Category: AI Provider Interface
 * Supports: Object detection, Scene understanding
 */
interface VisionProvider : AIProvider {
    /**
     * Analyze an image.
     *
     * @param request The vision request
     * @return The vision response with analysis results
     */
    suspend fun analyzeImage(request: VisionRequest): VisionResponse
}

/**
 * Vision request parameters.
 */
data class VisionRequest(
    val imageData: ByteArray,
    val analysisType: VisionAnalysisType = VisionAnalysisType.GENERAL,
    val metadata: Map<String, String> = emptyMap()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VisionRequest) return false
        if (!imageData.contentEquals(other.imageData)) return false
        if (analysisType != other.analysisType) return false
        if (metadata != other.metadata) return false
        return true
    }

    override fun hashCode(): Int {
        var result = imageData.contentHashCode()
        result = 31 * result + analysisType.hashCode()
        result = 31 * result + metadata.hashCode()
        return result
    }
}

/**
 * Types of vision analysis.
 */
enum class VisionAnalysisType {
    GENERAL,
    OBJECT_DETECTION,
    SCENE_UNDERSTANDING,
    TEXT_DETECTION,
    FACE_DETECTION,
    CUSTOM
}

/**
 * Vision response with analysis results.
 */
data class VisionResponse(
    val description: String,
    val objects: List<DetectedObject> = emptyList(),
    val confidence: Float = 0f,
    val metadata: Map<String, String> = emptyMap()
)

/**
 * A detected object in an image.
 */
data class DetectedObject(
    val label: String,
    val confidence: Float,
    val boundingBox: BoundingBox? = null
)
