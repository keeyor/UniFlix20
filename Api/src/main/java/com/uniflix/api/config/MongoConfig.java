package com.uniflix.api.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value( "${spring.mongodb.host}" )
    private String mongoDbHost;
    @Value( "${spring.mongodb.port}" )
    private Integer mongoDbPort;
    @Value( "${spring.mongodb.database}" )
    private String mongoDbDatabase;
    @Value( "${spring.mongodb.username}" )
    private String mongoDbUsername;
    @Value( "${spring.mongodb.password}" )
    private String mongoDbPassword;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://" + mongoDbHost + ":" + mongoDbPort +"/" + mongoDbDatabase);
        MongoCredential mongoCredential = MongoCredential.createCredential(mongoDbUsername,  mongoDbDatabase, mongoDbPassword.toCharArray());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(mongoCredential)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
}

