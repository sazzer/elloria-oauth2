package uk.co.grahamcox.elloria.oauth2.verification.facades

import org.springframework.web.client.RestTemplate

/**
 * Facade layer for working with the Debug controller
 */
class DebugControllerFacade(private val restTemplate: RestTemplate) {
    /**
     * Go and get the requested status
     * @param status the status to request
     * @return the response from getting the status code
     */
    fun getStatus(status: Int) =
        restTemplate.getForEntity("http://localhost:9998/oauth2/api/debug/status/${status}", String::class.java)
}