package ru.nikeshkin.coroutinesdemo.configuration

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

@Configuration
class DatabaseConfiguration(
    private val dbConfigurationProperties: DbConfigurationProperties
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory {
        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(dbConfigurationProperties.host)
                .port(dbConfigurationProperties.port)
                .database(dbConfigurationProperties.database)
                .username(dbConfigurationProperties.username)
                .password(dbConfigurationProperties.password).build()
        )
    }
}