package uk.co.grahamcox.elloria.oauth2.webapp.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeFinder
import uk.co.grahamcox.elloria.oauth2.webapp.DebugController
import uk.co.grahamcox.elloria.oauth2.webapp.scopes.ScopesController
import java.time.Clock

/**
 * The configuration to use for the Spring MVC Controllers
 */
@Configuration
open class ControllersContext {
    /**
     * Create the Debug Controller
     * @param clock The clock
     * @return the debug controller
     */
    @Autowired
    @Bean
    open fun debugController(clock: Clock) = DebugController(clock)

    /**
     * Create the Scopes Controller
     * @param scopeFinder The scope finder
     * @return the scopes controller
     */
    @Autowired
    @Bean
    open fun scopesController(scopeFinder: ScopeFinder) = ScopesController(scopeFinder)
}
