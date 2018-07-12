package tech.simter.mail.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import tech.simter.mail.dao.MailDao

@SpringJUnitConfig(MailServiceImpl::class)
@MockBean(MailDao::class)
class MailServiceImplTest @Autowired constructor(
  private val dao: MailDao,
  private val service: MailService
)