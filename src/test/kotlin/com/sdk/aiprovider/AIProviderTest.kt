package com.sdk.aiprovider

import com.sdk.aiprovider.api.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AIProviderTest {
    @Test
    fun testProviderRegistry() {
        val registry = AIProviderRegistry(android.app.Application())
        
        // Test empty state
        assertTrue(registry.getAvailableProviders().isEmpty())
        
        // Test registering a provider
        val mockProvider = object : AIProvider {
            override suspend fun isAvailable() = true
            override suspend fun initialize() {}
            override suspend fun shutdown() {}
            override fun getMetadata() = ProviderMetadata(
                id = "test-provider",
                name = "Test Provider",
                version = "1.0.0",
                isLocal = true,
                supportsStreaming = false
            )
        }
        
        registry.registerProvider("test", mockProvider)
        assertEquals(1, registry.getAvailableProviders().size)
        
        // Test getting active provider
        val active = registry.getActiveProvider()
        assertEquals("test-provider", active?.getMetadata()?.id)
    }

    @Test
    fun testResultType() {
        val successResult: Result<String> = Result.Success("test")
        assertEquals("test", successResult.getOrNull())
        assertTrue(successResult.isSuccess())

        val errorResult: Result<String> = Result.Error(Exception("error"))
        assertTrue(errorResult.isError())
    }

    @Test
    fun testProviderMetadata() {
        val metadata = ProviderMetadata(
            id = "test",
            name = "Test Provider",
            version = "1.0.0",
            isLocal = true,
            supportsStreaming = true,
            capabilityFlags = setOf(ProviderCapability.CHAT, ProviderCapability.VISION)
        )
        
        assertEquals("test", metadata.id)
        assertTrue(metadata.capabilityFlags.contains(ProviderCapability.CHAT))
    }

    @Test
    fun testResultSuccess() {
        val result: Result<Int> = Result.Success(42)
        assertEquals(42, result.getOrNull())
        assertTrue(result.isSuccess())
        assertTrue(!result.isError())
    }

    @Test
    fun testResultError() {
        val exception = Exception("Test error")
        val result: Result<String> = Result.Error(exception)
        assertTrue(result.isError())
        assertTrue(!result.isSuccess())
        assertEquals(null, result.getOrNull())
    }

    @Test
    fun testProviderCapabilities() {
        val capabilities = setOf(
            ProviderCapability.CHAT,
            ProviderCapability.VISION,
            ProviderCapability.EMBEDDING
        )
        
        assertEquals(3, capabilities.size)
        assertTrue(capabilities.contains(ProviderCapability.CHAT))
        assertTrue(capabilities.contains(ProviderCapability.VISION))
    }

    @Test
    fun testMultipleProviders() {
        val registry = AIProviderRegistry(android.app.Application())
        
        val provider1 = object : AIProvider {
            override suspend fun isAvailable() = true
            override suspend fun initialize() {}
            override suspend fun shutdown() {}
            override fun getMetadata() = ProviderMetadata(
                id = "provider-1",
                name = "Provider 1",
                version = "1.0",
                isLocal = true,
                supportsStreaming = true
            )
        }
        
        val provider2 = object : AIProvider {
            override suspend fun isAvailable() = true
            override suspend fun initialize() {}
            override suspend fun shutdown() {}
            override fun getMetadata() = ProviderMetadata(
                id = "provider-2",
                name = "Provider 2",
                version = "2.0",
                isLocal = false,
                supportsStreaming = false
            )
        }
        
        registry.registerProvider("p1", provider1)
        registry.registerProvider("p2", provider2)
        assertEquals(2, registry.getAvailableProviders().size)
    }
}
