package ru.nikeshkin.coroutinesdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import ru.nikeshkin.coroutinesdemo.configuration.DbConfigurationProperties

@SpringBootApplication
@EnableR2dbcRepositories
@EnableConfigurationProperties(DbConfigurationProperties::class)
class CoroutinesDemoApplication

fun main(args: Array<String>) {
	runApplication<CoroutinesDemoApplication>(*args)
}
