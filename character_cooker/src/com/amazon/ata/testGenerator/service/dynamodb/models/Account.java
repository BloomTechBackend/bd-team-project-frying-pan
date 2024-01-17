package com.amazon.ata.testGenerator.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Objects;

@DynamoDBTable(tableName = "LBC_Accounts")
public class Account {
    private String username;
    private String password;

    public Account() {}

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username, password);
    }
}
