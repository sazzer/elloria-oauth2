package uk.co.grahamcox.elloria.oauth2.webapp.scopes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller for working with scopes
 */
@Controller
@RequestMapping("/api/scopes")
class ScopesController {
    val scopes = listOf(
            Scope("oauth2", "read", "Ability to read OAuth2 Client details"),
            Scope("oauth2", "write", "Ability to edit OAuth2 Client details"),
            Scope("oauth2", "internal", "Internal access to everything")
    )

    /**
     * List all of the scopes
     */
    @RequestMapping
    @ResponseBody
    fun list() = scopes
}