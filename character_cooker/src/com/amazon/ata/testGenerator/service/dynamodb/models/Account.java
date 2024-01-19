package com.amazon.ata.testGenerator.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.Objects;

@DynamoDBTable(tableName = "LBC_Accounts")
public class Account {

    private String username;
    private String password;
    private String status;

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

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public Account setStatus(String userStatus) {
        this.status = userStatus;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username)
                && Objects.equals(password, account.password)
                && Objects.equals(status, account.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, status);
    }
}
