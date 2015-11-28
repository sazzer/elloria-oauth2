package uk.co.grahamcox.elloria.oauth2.webapp.scopes

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeFinder
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeId
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeNotFoundException
import uk.co.grahamcox.elloria.oauth2.scopes.Scope as InternalScope

/**
 * Convert the internal representation of a scope to the HTTP representation of one
 * @param scope The scope to convert
 * @return the converted scope
 */
private fun toHttpScope(scope: InternalScope) = Scope(
    id = scope.id.toString(),
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
    /** the logger to use */
    private val LOG = LoggerFactory.getLogger(ScopesController::class.java)

    /**
     * Handler for when a requested scope wasn't found
     * @param e The exception to handle
     */
    @ExceptionHandler(ScopeNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun scopeNotFound(e: ScopeNotFoundException) {
        LOG.warn("Failed to find scope: {}", e.scope)
    }

    /**
     * Get a single scope by it's ID
     * @param id The ID of the scope to get
     * @return the scope
     */
    @RequestMapping("/{id}")
    @ResponseBody
    fun get(@PathVariable id: String) =
        toHttpScope(scopeFinder.getById(ScopeId.parse(id)))

    /**
     * List all of the scopes
     * @return the list of all of the scopes
     */
    @RequestMapping
    @ResponseBody
    fun list() = scopeFinder.listScopes().map { s -> toHttpScope(s) }
}