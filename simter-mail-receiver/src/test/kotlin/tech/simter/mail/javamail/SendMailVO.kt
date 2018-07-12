package tech.simter.mail.javamail

data class SendMailVO(
  val host: String,
  val username: String,
  val password: String,
  val receiver: Array<String>,
  var copyReceiver: Array<String>?,
  val topic: String,
  val content: String?,
  val a: String
)