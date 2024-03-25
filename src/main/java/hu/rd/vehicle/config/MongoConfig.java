package hu.rd.vehicle.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import hu.rd.vehicle.model.UuidIdentifiedEntity;
import lombok.extern.slf4j.Slf4j;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = "hu.rd.vehicle.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${app.mongodb.url}")
    private String url;

    @Override
    protected String getDatabaseName() {
        return "rd";
    }

    @Override
    public MongoClient mongoClient() {
        log.info("Mongo URL: " + url);
        ConnectionString connectionString = new ConnectionString(url);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("hu.rd.vehicle");
    }

    @Override
    public boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public BeforeConvertCallback<UuidIdentifiedEntity> beforeSaveCallback() {

        return (entity, collection) -> {

            if (entity.getId() == null) {
                entity.setId(UUID.randomUUID());
            }
            return entity;
        };
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(
            final LocalValidatorFactoryBean factory) {
        return new ValidatingMongoEventListener(factory);
    }
}