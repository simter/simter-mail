package tech.simter.mail.po

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.Table

/**
 * The PO.
 *
 * @author RJ
 */
@Entity
@Table(name = "st_mail")
@Document(collection = "st_mail")
data class Mail(
  @javax.persistence.Id
  @org.springframework.data.annotation.Id
  val id: Int
)