package tech.simter.mail.javamail

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


/**
 * <https://www.dingdangss.com/2017/03/02/使用JavaMail收发邮件>
 */
class SendMailTest {
  private val logger = LoggerFactory.getLogger(SendMailTest::class.java)
  private val SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"
  @Test
  fun test1() {
  }

  fun sendMail(sendMailVO: SendMailVO): Boolean {
    // 配置发送邮件的环境属性
    val props = Properties()
    props.setProperty("mail.smtp.auth", "true")// 发送服务器需要身份验证
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY)
    props.setProperty("mail.smtp.socketFactory.fallback", "false")
    props.setProperty("mail.smtp.socketFactory.port", "465")// 发送邮件服务器端口号
    props.setProperty("mail.host", sendMailVO.host)// 发送邮件服务器主机名，如qq邮箱为：smtp.qq.com
    props.put("mail.user", sendMailVO.username)// 发件人的账号
    props.put("mail.password", sendMailVO.password)// 访问SMTP服务时需要提供的密码，qq邮箱时需要使用授权码
    try {
      val receiver = sendMailVO.receiver// 收件人邮箱，此处是一个List集合，收件人可以有多个
      val copyReceiver = sendMailVO.copyReceiver// 抄送人邮箱，可以有多个
      logger.info("============== start sendMail ================")
      logger.info("Sender: " + props.getProperty("mail.user"))
      logger.info("To: " + receiver)
      logger.info("cc: " + copyReceiver)
      logger.info("Subject: " + sendMailVO.topic)
      logger.info("Body: " + sendMailVO.content)
      // 构建授权信息，用于进行SMTP进行身份验证
      val authenticator = object : Authenticator() {
        protected// 用户名、密码
        val passwordAuthentication: PasswordAuthentication
          get() {
            val userName = props.getProperty("mail.user")
            val password = props.getProperty("mail.password")
            return PasswordAuthentication(userName, password)
          }
      }
      val mailSession = Session.getInstance(props, authenticator)// 使用环境属性和授权信息，创建邮件会话
      val message = MimeMessage(mailSession)// 创建邮件消息
      // 设置发件人
      val form = InternetAddress(props.getProperty("mail.user"))
      message.setFrom(form)
      // 设置收件人
      val to = arrayOfNulls<InternetAddress>(receiver.size)
      for (i in receiver.indices) {
        to[i] = InternetAddress(receiver.get(i))
      }
      message.setRecipients(Message.RecipientType.TO, to)
      // 设置抄送人
      if (copyReceiver != null && copyReceiver!!.size > 0) {
        val cc = arrayOfNulls<InternetAddress>(copyReceiver!!.size)
        for (i in copyReceiver!!.indices) {
          cc[i] = InternetAddress(copyReceiver!!.get(i))
        }
        message.setRecipients(Message.RecipientType.CC, cc)
      }
      // 设置邮件标题
      message.setSubject(sendMailVO.topic)
      // 设置邮件的内容体
      message.setContent(sendMailVO.content, "text/html;charset=UTF-8")
      // 发送邮件
      Transport.send(message)
      logger.info("email send success!")
      logger.info("============== end sendMail ================")
      return true
    } catch (e: MessagingException) {
      logger.error("email send failed!", e)
      return false
    }
  }
}