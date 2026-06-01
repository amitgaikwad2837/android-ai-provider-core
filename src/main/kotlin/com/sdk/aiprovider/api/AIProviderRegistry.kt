package com.sdk.aiprovider.api

import android.content.Context
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Registry for managing multiple AI provider implementations.
 *
 * Platform: Android
 * Category: Shared Foundation
 *
 * Allows applications to:
 * - Register custom providers
 * - Switch between providers at runtime
 * - Support multiple providers in one app
 */
@Singleton
class AIProviderRegistry(
    private val context: Context
) {
    private val providers = mutableMapOf<String, AIProvider>()
    private var activeProviderId: String? = null

    /**
     * Register an AI provider.
     */
    fun registerProvider(id: String, provider: AIProvider) {
        providers[id] = provider
        if (activeProviderId == null) {
            activeProviderId = id
        }
    }

    /**
     * Get a registered provider by ID.
     */
    fun getProvider(id: String): AIProvider? = providers[id]

    /**
     * Get the currently active provider.
     */
    fun getActiveProvider(): AIProvider? = activeProviderId?.let { providers[it] }

    /**
     * Set the active provider.
     */
    fun setActiveProvider(id: String): Boolean {
        return if (providers.containsKey(id)) {
            activeProviderId = id
            true
        } else {
            false
        }
    }

    /**
     * Get all registered provider IDs.
     */
    fun getAvailableProviders(): List<String> = providers.keys.toList()

    /**
     * Unregister a provider.
     */
    fun unregisterProvider(id: String) {
        providers.remove(id)
        if (activeProviderId == id) {
            activeProviderId = providers.keys.firstOrNull()
        }
    }

    /**
     * Clear all providers.
     */
    fun clearProviders() {
        providers.clear()
        activeProviderId = null
    }
}

/**
 * Hilt module for providing the AI Provider Registry.
 */
@Module
@InstallIn(SingletonComponent::class)
object AIProviderModule {
    @Provides
    @Singleton
    fun provideAIProviderRegistry(
        @ApplicationContext context: Context
    ): AIProviderRegistry = AIProviderRegistry(context)
}
