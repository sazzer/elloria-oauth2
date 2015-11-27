package uk.co.grahamcox.elloria.oauth2.verification.spring

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import uk.co.grahamcox.elloria.oauth2.verification.facades.DebugControllerFacade

/**
 * The root of the context to use for cucumber tests
 */
@Configuration
open class CucumberContext {
    /**
     * The Rest Template to use to access the services under test
     */
    @Bean
    open fun restTemplate(): RestTemplate {
        /**
         * Error Handler that indicates that no error occurred
         */
        class NoErrorHandler : ResponseErrorHandler {
            /**
             * Indicate that no error occurred
             */
            override fun hasError(p0: ClientHttpResponse) = false

            /**
             * Handle the errors by doing nothing
             */
            override fun handleError(p0: ClientHttpResponse) {}
        }

        val restTemplate = RestTemplate()
        restTemplate.errorHandler = NoErrorHandler()
        return restTemplate
    }

    /**
     * The Facade layer for working with the Debug Controller
     * @param restTemplate The test template to use
     */
    @Bean
    @Autowired
    open fun debugControllerFacade(restTemplate: RestTemplate) =
            DebugControllerFacade("http://localhost:9998/oauth2", restTemplate)
}
