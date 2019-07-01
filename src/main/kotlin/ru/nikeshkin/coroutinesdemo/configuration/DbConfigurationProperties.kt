package ru.nikeshkin.coroutinesdemo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.data.postgres")
class DbConfigurationProperties {

    lateinit var host: String
    lateinit var database: String
    lateinit var username: String
    lateinit var password: String
    var port: Int = 3333
}