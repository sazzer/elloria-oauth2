package uk.co.grahamcox.elloria.oauth2.webapp

import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Integration test for the Debug Controller
 */
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
}