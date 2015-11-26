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
}