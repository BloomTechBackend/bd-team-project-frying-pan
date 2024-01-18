package com.amazon.ata.testGenerator.service.dependency;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaoModule {
    @Singleton
    @Provides
    DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .build());
    }

}
