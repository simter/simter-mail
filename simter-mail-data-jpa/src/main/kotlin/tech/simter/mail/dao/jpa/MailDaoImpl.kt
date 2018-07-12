package tech.simter.mail.dao.jpa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import tech.simter.mail.dao.MailDao
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * The JPA implementation of [MailDao].
 *
 * @author RJ
 */
@Component
class MailDaoImpl @Autowired constructor(
  @PersistenceContext private val em: EntityManager,
  private val repository: MailJpaRepository
) : MailDao