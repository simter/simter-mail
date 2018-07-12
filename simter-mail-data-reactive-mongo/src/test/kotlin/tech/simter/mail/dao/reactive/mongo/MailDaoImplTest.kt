package tech.simter.mail.dao.reactive.mongo

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import tech.simter.mail.dao.MailDao
import tech.simter.mail.po.Mail

/**
 * See [SimpleReactiveMongoRepository] implementation.
 * @author RJ
 */
@SpringJUnitConfig(ModuleConfiguration::class)
@DataMongoTest
class MailDaoImplTest @Autowired constructor(
  private val operations: ReactiveMongoOperations,
  private val dao: MailDao
) {
  @BeforeEach
  fun setup() {
    // drop and create a new collection
    StepVerifier.create(
      operations.collectionExists(Mail::class.java)
        .flatMap { if (it) operations.dropCollection(Mail::class.java) else Mono.just(it) }
        .then(operations.createCollection(Mail::class.java))
    ).expectNextCount(1).verifyComplete()
  }
}