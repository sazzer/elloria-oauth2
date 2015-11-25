package uk.co.grahamcox.elloria.oauth2.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.time.Clock

/**
 * The root configuration to use for the core webapp Spring context
 */
@Configuration
open class CoreContext {
    /**
     * Configure the clock to use
     */
    @Bean(name = arrayOf("clock"))
    @Profile("!test")
    open fun clock() = Clock.systemUTC()
}
