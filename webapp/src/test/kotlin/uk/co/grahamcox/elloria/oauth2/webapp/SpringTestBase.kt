package uk.co.grahamcox.elloria.oauth2.webapp

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * Base Class for all tests that depend on the Spring Context
 */
@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = arrayOf(TestContext::class))
open class SpringTestBase {
    /** The Web Application Context, used to access the webapp during tests */
    @Autowired
    private lateinit var webAppContext: WebApplicationContext

    /** The Mock MVC Wrapper */
    private lateinit var mockMvc: MockMvc

    /**
     * Ensure that we have a Mock MVC Wrapper before the tests run
     */
    @Before
    fun setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
            .build()
    }


}