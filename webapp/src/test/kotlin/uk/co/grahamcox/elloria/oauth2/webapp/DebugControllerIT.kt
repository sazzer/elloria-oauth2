package uk.co.grahamcox.elloria.oauth2.webapp

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Integration test for the Debug Controller
 */
@RunWith(JUnitParamsRunner::class)
class DebugControllerIT : SpringTestBase() {
    /**
     * Test the Ping handler works as expected
     */
    @Test
    fun testPing() {
        perform(get("/api/debug/ping"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
            .andExpect(content().string("Pong"))
    }

    /**
     * Test the Now handler works as expected
     */
    @Test
    fun testNow() {
        perform(get("/api/debug/now"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").value("2015-11-26T07:50:25Z"))
    }

    /**
     * Test the Now handler works as expected
     */
    @Test
    @Parameters(method = "parametersForTestStatus")
    fun testStatus(statusCode: Int) {
        perform(get("/api/debug/status/${statusCode}"))
            .andExpect(status().`is`(statusCode))
    }

    /**
     * Generate the parameters for the testStatus method
     * @return all of the statuses to test with
     */
    fun parametersForTestStatus() = HttpStatus.values.map {
        s -> arrayOf(s.value())
    }
}
