# Simter Mail Modules

## Requirement

- Maven 3.5.2+
- Kotlin 1.2.31+
- Java 8+
- Spring Framework 5+
- Spring Boot 2+
- Reactor 3+

## Maven Modules

Sn | Name                              | Parent                   | Remark
---|-----------------------------------|--------------------------|--------
1  | [simter-mail-build]               | [simter-build:0.5.0]     | Build modules and define global properties and pluginManagement
2  | [simter-mail-dependencies]        | simter-mail-build        | Define global dependencyManagement
3  | [simter-mail-parent]              | simter-mail-dependencies | All sub modules parent module, Define global dependencies and plugins
4  | [simter-mail-data]                | simter-mail-parent       | Define Service and Dao Interfaces
5  | [simter-mail-data-reactive-mongo] | simter-mail-parent       | Dao Implementation By Reactive MongoDB
6  | [simter-mail-data-jpa]            | simter-mail-parent       | Dao Implementation By JPA
7  | [simter-mail-rest-webflux]        | simter-mail-parent       | Rest API By WebFlux
8  | [simter-mail-starter]             | simter-mail-parent       | Microservice Starter


Remark : Module 1, 2, 3 all has maven-enforcer-plugin and flatten-maven-plugin config. Other modules must not configure them.


[simter-build:0.5.0]: https://github.com/simter/simter-build/tree/0.5.0
[simter-mail-build]: https://github.com/simter/simter-mail
[simter-mail-dependencies]: https://github.com/simter/simter-mail/tree/master/simter-mail-dependencies
[simter-mail-parent]: https://github.com/simter/simter-mail/tree/master/simter-mail-parent
[simter-mail-data]: https://github.com/simter/simter-mail/tree/master/simter-mail-data
[simter-mail-data-jpa]: https://github.com/simter/simter-mail/tree/master/simter-mail-data-jpa
[simter-mail-data-reactive-mongo]: https://github.com/simter/simter-mail/tree/master/simter-mail-data-reactive-mongo
[simter-mail-rest-webflux]: https://github.com/simter/simter-mail/tree/master/simter-mail-rest-webflux
[simter-mail-starter]: https://github.com/simter/simter-mail/tree/master/simter-mail-starter