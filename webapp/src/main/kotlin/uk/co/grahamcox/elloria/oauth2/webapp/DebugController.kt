package uk.co.grahamcox.elloria.oauth2.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller for performing debug operations
 */
@Controller
@RequestMapping("/api/debug")
class DebugController {
    /**
     * Trivial handler that simply sends a response back
     */
    @ResponseBody
    @RequestMapping("/ping")
    fun ping() = "Pong"
}
