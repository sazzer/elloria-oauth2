package uk.co.grahamcox.elloria.oauth2.verification.facades

import org.springframework.web.client.RestTemplate
import java.time.Instant

/**
 * Facade layer for working with the Debug controller
 * @param serviceUrl The URL to the service under test
 * @param restTemplate The rest template to use
 */
class DebugControllerFacade(
        private val serviceUrl: String,
        private val restTemplate: RestTemplate) {
    /**
     * Go and get the requested status
     * @param status the status to request
     * @return the response from getting the status code
     */
    fun getStatus(status: Int) =
        restTemplate.getForEntity("${serviceUrl}/api/debug/status/${status}", String::class.java)

    /**
     * Ping the server
     * @return the response from pinging the server
     */
    fun pingServer() =
        restTemplate.getForEntity("${serviceUrl}/api/debug/ping", String::class.java)

    /**
     * Get the current time from the server
     * @return the response from getting the server time
     */
    fun getServerTime() =
        restTemplate.getForEntity("${serviceUrl}/api/debug/now", Instant::class.java)
}
