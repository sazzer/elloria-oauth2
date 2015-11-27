package uk.co.grahamcox.elloria.oauth2.webapp.scopes

/**
 * Representation of a single scope
 * @property namespace The namespace of the scope
 * @property scope The actual scope
 * @property description The description of the scope
 */
data class Scope(val namespace: String?,
                 val scope: String,
                 val description: String)