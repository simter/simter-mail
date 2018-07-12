package tech.simter.mail.rest.webflux

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

private const val MODULE = "tech.simter.mail.rest.webflux"

/**
 * All configuration for this module.
 *
 * Register a `RouterFunction<ServerResponse>` with all routers for this module.
 * The default context-path of this router is '/'. And can be config by property `simter.rest.context-path.mail`.
 *
 * @author RJ
 */
@Configuration("$MODULE.ModuleConfiguration")
@ComponentScan(MODULE)
@EnableWebFlux
class ModuleConfiguration @Autowired constructor(
  @Value("\${simter.rest.context-path.mail:/}") private val contextPath: String
) {
  private val logger = LoggerFactory.getLogger(ModuleConfiguration::class.java)

  init {
    logger.warn("simter.rest.context-path.mail='{}'", contextPath)
  }

  /** Register a `RouterFunction<ServerResponse>` with all routers for this module */
  @Bean("$MODULE.Routes")
  @ConditionalOnMissingBean(name = ["$MODULE.Routes"])
  fun mailRoutes() = router {
    contextPath.nest {
      // GET /
      GET("/", { ok().contentType(TEXT_PLAIN).syncBody("simter-mail module") })
    }
  }
}