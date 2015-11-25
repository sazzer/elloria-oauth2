package uk.co.grahamcox.elloria.oauth2.webapp.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.elloria.oauth2.webapp.DebugController
import java.time.Clock

/**
 * The configuration to use for the Spring MVC Controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Create the Debug Controller
     */
    @Autowired
    @Bean
    open fun debugController(clock: Clock) = DebugController(clock)
}
