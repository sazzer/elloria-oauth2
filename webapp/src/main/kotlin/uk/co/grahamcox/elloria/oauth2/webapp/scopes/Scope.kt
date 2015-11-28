package uk.co.grahamcox.elloria.oauth2.webapp.scopes

/**
 * Representation of a single scope
 * @property id The actual ID of the scope
 * @property namespace The namespace of the scope
 * @property scope The name of the scope
 * @property description The description of the scope
 */
data class Scope(val id: String,
                 val namespace: String?,
                 val scope: String,
                 val description: String)