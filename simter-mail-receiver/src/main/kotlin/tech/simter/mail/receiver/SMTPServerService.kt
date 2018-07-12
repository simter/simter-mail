package tech.simter.mail.receiver

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Service
class SMTPServerService @Autowired constructor(
  @Value("\${app.mail.disabled: false}")
  private val disabled: Boolean,
  @Value("\${app.mail.host: 127.0.0.1}")
  private val host: String,
  @Value("\${app.mail.port: 25}")
  private val port: String
) {
  private val logger = LoggerFactory.getLogger(SMTPServerService::class.java)

  @PostConstruct
  fun start() {
    if (disabled) {
      logger.warn("SMTP Server NOT ENABLED")
    }

//    SimpleMessageListenerImpl l = new SimpleMessageListenerImpl();
//    smtpServer = new SMTPServer (new SimpleMessageListenerAdapter (l));
//    smtpServer.setHostName(this.hostName);
//    smtpServer.setPort(Integer.valueOf(port));
//    smtpServer.start();
  }

  @PreDestroy
  fun stop() {
//    if (enabled.equalsIgnoreCase(“ true”)){
//      System.out.println(“ * * * * * * Stopping SMTP Server for domain “+smtpServer.getHostName()+” on port “+smtpServer.getPort());
//      smtpServer.stop();
//    }
  }

  fun isRunning(): Boolean {
    return false//smtpServer.isRunning();
  }
}