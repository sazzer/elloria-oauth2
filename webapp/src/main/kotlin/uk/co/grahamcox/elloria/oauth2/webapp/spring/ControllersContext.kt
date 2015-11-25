package uk.co.grahamcox.elloria.oauth2.webapp.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.elloria.oauth2.webapp.DebugController

/**
 * The configuration to use for the Spring MVC Controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Create the Debug Controller
     */
    @Bean
    open fun debugController() = DebugController()
}
