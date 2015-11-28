package uk.co.grahamcox.elloria.oauth2.scopes

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
     * @return the scope, or null if one wasn't found
     */
    fun getById(id: ScopeId) = scopes.find { s -> id == s.id }

    /**
     * List all of the scopes
     * @return the scopes
     */
    fun listScopes() = scopes
}