package uk.co.grahamcox.elloria.oauth2.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.Clock

/**
 * Controller for performing debug operations
 * @property The clock to use
 */
@Controller
@RequestMapping("/api/debug")
class DebugController(private val clock: Clock) {
    /**
     * Trivial handler that simply sends a response back
     */
    @ResponseBody
    @RequestMapping("/ping")
    fun ping() = "Pong"

    /**
     * Get the current server time
     */
    @ResponseBody
    @RequestMapping("/now")
    fun now() = clock.instant()
}
