package tech.simter.mail.dao.reactive.mongo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tech.simter.mail.dao.MailDao

/**
 * The Reactive MongoDB implementation of [MailDao].
 *
 * @author RJ
 */
@Component
class MailDaoImpl @Autowired constructor(
  private val repository: MailReactiveRepository
) : MailDao