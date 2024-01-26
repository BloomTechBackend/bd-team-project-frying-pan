package com.amazon.ata.testGenerator.service.dependency;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
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
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("AKIA6AYIOXNSB473SQ7H",
                                "a5DMoJfMLoBfbSjcBNhaNsUXN9styJT/Zued78sD")))
                .withRegion(Regions.US_WEST_2)
                .build();
        return new DynamoDBMapper(client);
    }
}
