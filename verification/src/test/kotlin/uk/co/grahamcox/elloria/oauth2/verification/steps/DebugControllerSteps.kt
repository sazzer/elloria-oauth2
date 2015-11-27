package uk.co.grahamcox.elloria.oauth2.verification.steps

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import uk.co.grahamcox.elloria.oauth2.verification.facades.DebugControllerFacade
import java.time.Duration
import java.time.Instant

/**
 * Cucumber steps for working with the Debug Controller
 */
class DebugControllerSteps {
    /** Facade to use for working with the Debug controller */
    @Autowired
    private lateinit var debugControllerFacade: DebugControllerFacade

    /** The response from getting the debug status code */
    private var debugStatusResponse: ResponseEntity<String>? = null

    /** The response from pinging the debug controller */
    private var debugPingResponse: ResponseEntity<String>? = null

    /** The response from getting the current time */
    private var debugTimeResponse: ResponseEntity<Instant>? = null

    /**
     * Make a request to the debug status handler
     * @param status The status to request
     */
    @When("^I get the debug status for (\\d\\d\\d)$")
    fun getDebugStatus(status: Int) {
        debugStatusResponse = debugControllerFacade.getStatus(status)
    }

    /**
     * Check that the status code returned is the one expected
     * @param status The status code that we expected
     */
    @Then("^the debug status code is (\\d\\d\\d)$")
    fun checkDebugStatus(status: Int) {
        Assert.assertNotNull(debugStatusResponse)
        Assert.assertEquals(status, debugStatusResponse?.statusCode?.value())
    }

    /**
     * Send a Ping to the debug controller
     */
    @When("^I ping the debug controller$")
    fun pingDebugController() {
        debugPingResponse = debugControllerFacade.pingServer()
    }

    /**
     * Assert that the debug controller returned a Pong
     */
    @Then("^I the debug response is a pong$")
    fun checkDebugPong() {
        Assert.assertNotNull(debugPingResponse)
        Assert.assertEquals(HttpStatus.OK, debugPingResponse?.statusCode)
        Assert.assertEquals("Pong", debugPingResponse?.body)
    }

    /**
     * Request the current server time from the debug controller
     */
    @When("I request the current time from the debug controller$")
    fun getCurrentTime() {
        debugTimeResponse = debugControllerFacade.getServerTime()
    }

    /**
     * Assert that the debug controller returned the current time to within a couple of seconds
     */
    @Then("I get roughly the current time from the debug controller$")
    fun checkCurrentTime() {
        val now = Instant.now()

        Assert.assertNotNull(debugTimeResponse)
        Assert.assertEquals(HttpStatus.OK, debugTimeResponse?.statusCode)

        val serverTime = debugTimeResponse?.body
        val timeOffset = Duration.between(now, serverTime)

        Assert.assertTrue(timeOffset.abs().toMillis() < 2000)
    }
}
