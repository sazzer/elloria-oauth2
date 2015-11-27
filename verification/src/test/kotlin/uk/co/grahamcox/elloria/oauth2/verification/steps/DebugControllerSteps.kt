package uk.co.grahamcox.elloria.oauth2.verification.steps

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import uk.co.grahamcox.elloria.oauth2.verification.facades.DebugControllerFacade

/**
 * Cucumber steps for working with the Debug Controller
 */
class DebugControllerSteps {
    /** Facade to use for working with the Debug controller */
    @Autowired
    private lateinit var debugControllerFacade: DebugControllerFacade

    /** The response from getting the debug status code */
    private var debugStatusResponse: ResponseEntity<String>? = null

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
}