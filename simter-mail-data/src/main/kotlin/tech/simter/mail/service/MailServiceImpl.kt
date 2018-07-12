package tech.simter.mail.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import tech.simter.mail.dao.MailDao

@Component
@Transactional
class MailServiceImpl @Autowired constructor(
  private val dao: MailDao
) : MailService