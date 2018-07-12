package tech.simter.mail.starter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
  runApplication<App>(*args)
}

@SpringBootApplication(
  scanBasePackages = ["tech.simter.mail"],
  scanBasePackageClasses = [ProjectInfoAutoConfiguration::class]
)
class App