package uk.co.grahamcox.elloria.oauth2.scopes

/**
 * The Namespaced ID of a scope
 * @property namespace the namespace
 * @property scope the scope
 */
data class ScopeId(val namespace: String?,
                   val scope: String) {
    /**
     * Generate a simple string representation of a Scope ID
     * This is the namespace and scope, separated with a colon character
     */
    override fun toString(): String = listOf(namespace, scope)
        .filterNotNull()
        .joinToString(":")
}

/**
 * Representation of a scope
 * @param id The Namespaced ID of the scope
 * @param description The description of the scope
 */
data class Scope(val id: ScopeId, val description: String)