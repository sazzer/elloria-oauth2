package uk.co.grahamcox.elloria.oauth2.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.grahamcox.elloria.oauth2.scopes.ScopeFinder

/**
 * Spring configuration for working with scopes
 */
@Configuration
open class ScopesContext {
    /**
     * Bean to allow us to find scopes
     * @return the scope finder
     */
    @Bean
    open fun scopeFinder() = ScopeFinder()
}