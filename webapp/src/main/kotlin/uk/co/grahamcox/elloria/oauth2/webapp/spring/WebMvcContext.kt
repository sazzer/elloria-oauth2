package uk.co.grahamcox.elloria.oauth2.webapp.spring

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * The configuration to use for the Spring MVC Configuration
 */
@Configuration
@EnableWebMvc
open class WebMvcContext : WebMvcConfigurerAdapter() {

}
