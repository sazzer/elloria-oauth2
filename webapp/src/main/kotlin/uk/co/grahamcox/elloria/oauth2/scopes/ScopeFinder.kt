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
     * List all of the scopes
     * @return the scopes
     */
    fun listScopes() = scopes
}