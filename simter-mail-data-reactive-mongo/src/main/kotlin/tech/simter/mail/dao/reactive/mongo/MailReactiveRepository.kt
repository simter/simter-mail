package tech.simter.mail.dao.reactive.mongo

import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import tech.simter.mail.po.Mail

/**
 * See interfaces [ReactiveSortingRepository], [ReactiveQueryByExampleExecutor], [ReactiveCrudRepository], [ReactiveMongoOperations].
 *
 * See implements [ReactiveMongoTemplate], [SimpleReactiveMongoRepository] .
 *
 * @author RJ
 */
interface MailReactiveRepository : ReactiveCrudRepository<Mail, String>