#  Simter Mail Server

## Requirement

- Maven 3.5.2+
- Kotlin 1.2.31+
- Java 8+
- Spring Framework 5+
- Spring Boot 2+
- Reactor 3+

## Maven Profiles

Environment | Profile            | Persistence        | Remark
------------|--------------------|--------------------|--------
Development |dev-reactive-mongo  | [Embedded MongoDB] | Reactive
Development |dev-jpa-hsql        | [HyperSQL]         | JPA
Production  |prod-reactive-mongo | [MongoDB]          | Reactive
Production  |prod-jpa-postgres   | [PostgreSQL]       | JPA

The default profile is `dev-reactive-mongo`. Run bellow command to start:

```bash
mvn spring-boot:run -P {profile-name}
```

Default server-port is 8087, use `-D port=8080` to change to another port.

## Maven Properties

Property Name | Default Value | Remark
--------------|---------------|--------
port          | 8087          | Web server port
db.host       | localhost     | Database host
db.name       | test_mail     | Database name
db.username   | test          | Database connect username
db.password   | password      | Database connect password
db.init-mode  | never         | `never` or `always`. If `always`, init main database by `spring.datasource.schema/data` config.  

Use `-D {property-name}={property-value}` to override default value. Such as:

```bash
mvn spring-boot:run -D port=8081
```

## Build Production

```bash
mvn clean package -P prod-{xxx}
```

## Run Production

```bash
java -jar {package-name}.jar

# or
nohup java -jar {package-name}.jar > /dev/null &
```

## Run Integration Test

Run test in the real server.

1. Start server. Such as `mvn spring-boot:run`
2. Run [IntegrationTest.kt]


[Embedded MongoDB]: https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo#embedded-mongodb
[MongoDB]: https://www.mongodb.com
[HyperSQL]: http://hsqldb.org
[PostgreSQL]: https://www.postgresql.org
[IntegrationTest.kt]: https://github.com/simter/simter-mail/blob/master/simter-mail-starter/src/test/kotlin/tech/simter/mail/starter/IntegrationTest.kt