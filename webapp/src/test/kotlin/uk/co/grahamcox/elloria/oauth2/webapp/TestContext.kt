package uk.co.grahamcox.elloria.oauth2.webapp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import uk.co.grahamcox.elloria.oauth2.spring.CoreContext
import uk.co.grahamcox.elloria.oauth2.webapp.spring.ServletContext
import java.time.Clock

/**
 * Root configuration for the Spring Context to use for tests
 */
@Configuration
@Import(
        CoreContext::class,
        ServletContext::class
)
open class TestContext {
    /** The clock to use for the tests */
    @Bean
    @Profile("test")
    open fun clock() = Clock.systemUTC()
}