package uk.co.grahamcox.elloria.oauth2.webapp.scopes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeFinder
import uk.co.grahamcox.elloria.oauth2.scopes.Scope as InternalScope

/**
 * Convert the internal representation of a scope to the HTTP representation of one
 * @param scope The scope to convert
 * @return the converted scope
 */
private fun toHttpScope(scope: InternalScope) = Scope(
    namespace = scope.id.namespace,
    scope = scope.id.scope,
    description = scope.description
)

/**
 * Controller for working with scopes
 * @property scopeFinder The mechanism for finding scopes
 */
@Controller
@RequestMapping("/api/scopes")
class ScopesController(private val scopeFinder: ScopeFinder) {

    /**
     * List all of the scopes
     */
    @RequestMapping
    @ResponseBody
    fun list() = scopeFinder.listScopes().map { s -> toHttpScope(s) }
}