package uk.co.grahamcox.elloria.oauth2.scopes

/**
 * Exception when looking up a scope that wasn't found
 * @property scope The scope ID that wasn't found
 */
class ScopeNotFoundException(val scope: ScopeId) : Exception("Failed to find scope: ${scope}")

/**
 * Mechanism to find scopes
 */
class ScopeFinder {
    private val scopes = listOf(
            Scope(ScopeId("oauth2", "read"), "Ability to read OAuth2 Client details"),
            Scope(ScopeId("oauth2", "write"), "Ability to edit OAuth2 Client details"),
            Scope(ScopeId("oauth2", "internal"), "Internal access to everything")
    )

    /**
     * Get a single scope by it's ID
     * @param id The ID of the scope to get
     * @return the scope
     * @throw ScopeNotFoundException if the scope wasn't found
     */
    fun getById(id: ScopeId) = scopes.find { s -> id == s.id } ?: throw ScopeNotFoundException(id)

    /**
     * List all of the scopes
     * @return the scopes
     */
    fun listScopes() = scopes
}