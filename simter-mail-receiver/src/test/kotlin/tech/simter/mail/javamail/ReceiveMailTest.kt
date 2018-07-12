package tech.simter.mail.javamail

import com.sun.mail.imap.IMAPFolder
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.*
import javax.mail.Flags
import javax.mail.Folder
import javax.mail.FolderClosedException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.search.FlagTerm

/**
 * <https://www.dingdangss.com/2017/03/02/使用JavaMail收发邮件>
 */
class ReceiveMailTest {
  private val logger = LoggerFactory.getLogger(ReceiveMailTest::class.java)

  @Test
  fun test1() {
    receiveMail(host = "imap.139.com", username = "rongjih@139.com", password = "Ji80106791")
  }

  /**
   * @param[host] IMAP 服务器
   * @param[username] 当前用户的邮箱账号
   * @param[host] 当前用户的邮箱账号密码
   */
  private fun receiveMail(host: String, username: String, password: String) {
    val time = System.currentTimeMillis()

    // 准备连接服务器的会话信息
    val props = Properties()
    props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
    props.setProperty("mail.imap.socketFactory.fallback", "false")
    props.setProperty("mail.imap.socketFactory.port", "993")// 接收邮件服务器端口号
    props.setProperty("mail.imap.host", host)// 接收邮件服务器

    // 创建Session实例对象
    val session = Session.getInstance(props)

    // 创建IMAP协议的Store对象
    val store = session.getStore("imaps")
    store.connect(host, username, password)

    // 获得收件箱
    val folder = store.getFolder("INBOX") as IMAPFolder

    // 以读写模式打开收件箱
    folder.open(Folder.READ_WRITE)

    // 获得收件箱中未读邮件列表
    val messages = folder.search(FlagTerm(Flags(Flags.Flag.SEEN), false))// 已读的历史邮件不处理,只处理未读的
    logger.info("unread mail count=" + messages.size)

    messages.forEach { }
    //val mails = ArrayList<Any>()
    // 解析邮件
    var i = 0
    while (i < messages.size) {
      if(i > 1) break
      try {
        val mail = messages[i] as MimeMessage
        logger.debug("i=$i----------------------------")
        logger.debug("messageID=${mail.messageID}") //

        // 发送人
        val sender = mail.sender as InternetAddress
        logger.debug("sender=${mail.sender}") // Address
        logger.debug("sender.type=${mail.sender.type}")
        logger.debug("sender.hashCode=${mail.sender.hashCode()}")
        logger.debug("sender.personal=${sender.personal}")
        logger.debug("sender.address=${sender.address}")
        logger.debug("from.count=${mail.from.size}")
        logger.debug("from.0.hashCode=${mail.from[0].hashCode()}")

        logger.debug("subject=${mail.subject}") // 主题
        logger.debug("sentDate=${mail.sentDate}") // 发送时间
        logger.debug("receivedDate=${mail.receivedDate}") //
        logger.debug("contentID=${mail.contentID}") //
        logger.debug("contentType=${mail.contentType}") //
        logger.debug("encoding=${mail.encoding}") //
        logger.debug("size=${mail.size}") //
        logger.debug("lineCount=${mail.lineCount}") //
        logger.debug("isExpunged=${mail.isExpunged}") //
        logger.debug("fileName=${mail.fileName}") //
        logger.debug("description=${mail.description}") //
        logger.debug("disposition=${mail.disposition}") //


//        // 将邮件服务器的邮件Message拷贝到本地中，提高解析邮件内容的效率
//        val cmsg = copyToClientMessage(smsg, session)
//        val mailReceiveVO = MailReceiveVO()
//        mailReceiveVO.setTopic(cmsg.getSubject())// 主题
//        mailReceiveVO.setSendTime(cmsg.getSentDate())// 发送时间
//        // 发件人信息
//        val froms = cmsg.getFrom()
//        if (froms != null) {
//          val addr = froms!![0] as InternetAddress
//          val mailContactDO = MailContactDO()
//          mailContactDO.setEmail(addr.address)// 发件人邮件地址
//          mailContactDO.setName(addr.personal) // 发件人邮件昵称
//          mailReceiveVO.setSender(mailContactDO)
//        }
//        // 收件人信息
//        val receiverAddrs = cmsg.getRecipients(Message.RecipientType.TO)
//        val receiver = ArrayList<MailContactDO>()
//        if (receiverAddrs != null && receiverAddrs!!.size > 0) {
//          for (tmpAddr in receiverAddrs!!) {
//            val addr = tmpAddr as InternetAddress
//            val mailContactDO = MailContactDO()
//            mailContactDO.setEmail(addr.address)
//            mailContactDO.setName(addr.personal)
//            receiver.add(mailContactDO)
//          }
//        } else { // 如果没有收件人信息，则默认将自己添加到收件人中
//          val mailContactDO = MailContactDO()
//          mailContactDO.setEmail(receiveMailVO.getUsername())
//          receiver.add(mailContactDO)
//        }
//        mailReceiveVO.setReceiver(receiver)
//        // 抄送人信息
//        val copyReceiverAddrs = cmsg.getRecipients(Message.RecipientType.CC)
//        if (copyReceiverAddrs != null && copyReceiverAddrs!!.size > 0) {
//          val copyReceiver = ArrayList<MailContactDO>()
//          for (tmpAddr in copyReceiverAddrs!!) {
//            val addr = tmpAddr as InternetAddress
//            val mailContactDO = MailContactDO()
//            mailContactDO.setEmail(addr.address)
//            mailContactDO.setName(addr.personal)
//            copyReceiver.add(mailContactDO)
//          }
//          mailReceiveVO.setCopyReceiver(copyReceiver)
//        }
//        // 解析邮件内容
//        val content = getText(cmsg)
//        mailReceiveVO.setContent(content)
//        mailReceiveVO.setState(MailState.UNREAD)
//        smsg.setFlag(Flags.Flag.SEEN, true) // 修改邮件服务器的邮件状态，把邮件标记为已读
//        mails.add(mailReceiveVO)
      } catch (e: FolderClosedException) { // 收件箱打开超时后会自动关闭，这次捕获此异常，并重新打开收件箱，重新处理当前邮件
        logger.warn("reopen folder")
        folder.open(Folder.READ_WRITE)
        --i
      } catch (e: IOException) { // 获取邮件内容失败
        logger.error("Failed to get mail content", e)
      } catch (e: Exception) { // 解析邮件内容失败
        logger.error("Failed to resolve mail content", e)
      }
      i++
    }

    // 释放资源
    folder.close(true)
    store.close()
    logger.info("接收邮件耗时：" + (System.currentTimeMillis() - time))
    logger.info("============== end receiveMail ================")
    //return mails
  }
}