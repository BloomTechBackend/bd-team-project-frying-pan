# [Fyring Pan] Character Cooker

## Character Cooker: A Japanese Character Writing Test Generator - Design

## 1. Problem Statement

The internet provides tons of resources to learn Japanese. Due to the limitations 
of computer inputs, many online character practice resources are limited to online flash cards, multiple choice Q&A, and typing the Romaji (the romanization of Japanese characters). 
What about romaji-to-character tests? Most of these tests are still limited to the options above or using the qwerty keyboard to spell out the japanese characters. 
Customers want to create their own character-to-writing tests. 

Character Cooker: is a Japanese romaji-to-character writing test generator. Customers
can access the website to create, edit and save their own test templates based on their individual
study plan. It is designed to interact with the Amazon Web Service where customers can save their
work to their account and generate romaji-to-character tests. 

Learning symbolic characters takes a lot of memorization and writing is scientifically proven
to help people learn and memorize. Many romaji-to-characters test are limited to full sentences 
or few versions. Character Cooker will cook up all of your orders! (Or until our databases are full which we doubt will happen). 

## 2. Top Questions to Resolve in Review

1. Do I store template data based on each sets of characters or allow for the options of individual characters?
2. Do I add the feature of allowing users to add their own characters? Does this affect how I store my current design of japanese hiragana characters? 
4. The number of Hiragana characters is fixed. Do I need to store the character data in the backend or in the database?

## 3. Use Cases
As a Character Cooking customer...
U1. I want to create a new, empty template with a title

U2. I want to retrieve my test with a given ID.
    
U3. I want to update my test's name with a given ID.

U4. I want to update my test's character list (romaji list) with a given ID.

U5. I want to delete my test. 

U6. I want to generate my test in a provided order (default in order, shuffled);

U7. I want create custom terms.

## 4. Project Scope

### 4.1. In Scope

* Creating accounts to log in and log out. 
* Creating, retrieving, saving, or deleting a test template
* Creating and deleting custom terms
* Generate Writing tests with the characters listed in order or in random order

### 4.2. Out of Scope

* Allow users to automatically grade their test answers
  * There is an answer sheet that users can manuelly compare 
* Allow users to edit the layout of the generated tests
* The ability to interact with other users and sharing templates or terms

# 5. Proposed Architecture Overview

I will use API Gateway and Lambda to create 4 paths: Accounts, Templates, Terms and Test. These corresponding to the respective resources. Each path will have endpoints to create, get, update or delete their resource.  

Accounts, test Templates, and terms are stored on AWS DynamoDB. Custom Terms and Regular Terms will be stored on the same table.  

Character Cooker will also provide a web interface for users to interact with their
tests. The main page will let the user sign in. Once the user signs in, they can 
view a list of the templates they have created. Users can edit temlates and generate tests from their saved templates or create a new one

# 6. API

## 6.1. Public Models
```
// TemplateModel

String templateId;
String title;
String username;
String dateModified;
List<String> hiraganaIdList;
List<String> katakanaIdList;
```

```
// TermModel

String termId;
String romanization;
String symbol;
```

```
TestModel 

String title;
String testQuestions;
String testAnswers;
```

## 6.2.1 Create Account Endpoint
* Accepts the `POST` request to `/Accounts`
* Accepts data to create a new account with a provided username, password, password confirmation. 
* Returns the username to signal a successful account creation

For security reasons:
* Validate the username is at least 5 characters long
  * If the username contains less then 5 characters, then throw an InvalidAttributeValueException
* Validate the username does not contain invalid characters: `" ' \` and spaces
  * If the username contains any invalid characters, then throw an InvalidAttributeValueException 
* Validate the username does not match an existing accounts' username
  * If the username matches any existing account's username, then throw an InvalidAttributeValueException
* Validate the password is at least 5 characters long
  * If the password contains less then 5 characters, then throw an InvalidAttributeValueException
* Validate the password does not contain invalid characters: `" ' \` and spaces
  * If the password contains any invalid characters, then throw an InvalidAttributeValueException 
* Validate the password confirms with the confirmation password
  * If the password does not match the confirmation password, then throw an 
  InvalidAttributeValueException

## 6.2.2 Login Account Endpoint
* Accepts the `POST` request to `/account/::username/status `
* Accepts a username and password to update the account status to logged in
* Returns the username and updated log in status
  * If the username is not found, then throw an AccountNotFoundAcception 

For security reasons: 
* Validate the username
  * If the username contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the username contains any invalid characters (`" ' \` and spaces), then throw an InvalidAttributeValueException 
* Validates the password 
  * If the password contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the password contains any invalid characters (`" ' \` and spaces), then throw an InvalidAttributeValueException 
  * If the password does not match the saved password, then throw an 
  InvalidAttributeValueException

## 6.2.3 Log Out Account Endpoint
* Accepts the `PUT` request to `/account/::username/status`
* Accepts a username to update the account status to logged out
* Returns the username and updated log out status
  * If the username is not found, then throw an AccountNotFoundAcception 

For security reasons: 
* Validate the username
  * If the username contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the username contains any invalid characters (`" ' \` and spaces), then throw an InvalidAttributeValueException 

## 6.2.4 Delete Account Endpoint
* Accepts the `DELETE` request to `/Accounts/::username`
* Accepts a username and password, verifies both entires and delete the corresponding Account
  * Test templates and custom terms made by the user will also be deleted 
* Returns username a list of the deleted resources to signal a successful delete attempt
  * If the username or the password do not match, then throw an InvalidAttributeValueException 

For security reasons: 
* Validates the username
  * If the username contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the username contains any invalid characters (`" ' \` and spaces), then throw an InvalidAttributeValueException 
* Validates the password 
  * If the password contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the password contains any invalid characters (`" ' \` and spaces), then throw an 

<!-- ## 6.2.5 Is Logged In Account Endpoint ***
* Accepts the `GET` request to `/Accounts/::username/status`
* Accepts a username and verifies the log in status of the user
* Returns the username and boolean
  * If the username is not found, then throw an AccountNotFoundAcception 
  * If the user is not logged in, throw an UnauthorizedAccessException 

For security reasons: 
* Validate the username
  * If the username contains less then 5 characters, then throw an InvalidAttributeValueException
  * If the username contains any invalid characters, then throw an InvalidAttributeValueException  -->

## 6.3.1 Get TestTemplate Endpoint
* Accepts a `GET` request to `/TestTemplate/::templateId`
* Accepts a template ID
* Returns the corresponding template
  * If the given template ID is not found, then throw a TemplateNotFoundException 

## 6.3.2 Create TestTemplate Endpoint
* Accepts a `POST` request to `/TestTemplates`
* Accepts data to create a new Template with a provided title, username and list of term IDs
  * If there is not a list of terms, then save an emtpy list
* Return the new template with a unique template Id and date provided by the Test Generator  Service 

For security reasons: 
* Validates the title does not contain invalid characters: `" ' \`
  * If the password contains any invalid characters, then throw an InvalidAttributeValueException
  * Note: The Title can have spaces

## 6.3.3 Update TestTemplate Endpoint
* Accepts a `PUT` request to `/TestTemplates/::templateId`
* Accepts data to update a template including a template ID, title, username and term Id list 
* Returns the update template
For security reasons: 
* Validates the title does not contain invalid characters: `" ' \`
  * If the password contains any invalid characters, then throw an InvalidAttributeValueException
  * Note: The Title can have spaces

## 6.3.4 Delete TestTemplate Endpoint
* Accepts a `DELETE` request to `/TestTemplates/::templateId`
* Accepts a template ID to delete the corresponding template
  * Custom made terms associted with the test will also be deleted and returned
* Returns the deleted template (and deleted custom made terms if deleted).

## 6.3.5 Get Template By Username Title Endpoint
* Accepts a `GET` request to `/TestTemplate/::username`
* Accepts a username to retrieve all templates with the username
* Return the list of templates from a user sorted by date 

<!-- ## 6.3.6 Get Template By Username Date Endpoint
* Accepts a `GET` request to `/TestTemplate/::username`
* Accepts a username to retrieve all templates with the username
* Return the list of templates from a user sorted by date  -->

## 6.4.1 Create Custom Term Endpoint (Add Custom Term to Template)
* Accepts a `POST` request to `/Terms`
* Accepts data to create a new custom term with a provided romanization, symbol(s), template ID and username
  * Optional definition field
* Return the new term with a unique Term ID and date provided by the Test Generator  Service 

## 6.4.2 Delete Custom Term Endpoint (Removes Custom Term from Template)
* Accepts a `DELETE` request to `/Terms/::termId`
* Accepts a term ID to delete the corresponding custom term
  * Optional definition field
* Return the deleted  

## 6.4.3 Get Terms By Template Date Endpoint
* Accepts a `GET` request to `/Terms/::templateId`
* Accepts a template ID to retrieves all custom terms associated with the template
* Returns a list of term, sorted by date. 

<!-- ## 6.4.4 Get Terms By Username Date Endpoint
* Accepts a `GET` request to `/Terms/::templateId`
* Accepts a username to retrieves all custom terms associated with the username
* Returns a list of term, sorted by date.  -->

<!-- ## 6.5.1 Update Term Endpoint - not included for now -->

## 6.5.1 Generate Test Doc 
* Accepts a `POST` request to `/Terms/::termId`
* Accepts a list of termIds to retrieve
  * Optional parameter for the order for the termIds: In order or in random order.
* Return the test with a title and testModels for each section requested.

# 7. Tables
## 7.1. `Accounts`
```
username // partition key, string
password // string
status // string
```

## 7.2 `TestTemplate`
```
templateId // partition key, string
title // string
username // string
dateModified // string
hiraganaIdList // List
katakanaIdList // List
```

## 7.3 `Term`
```
termId // partition key, string
romanization // string
symbol // string

templateId // string
username // string
dateCreated // string
definition // string
```

# 8. Pages

![](../images/Project Images/LBC Front End Diagram-Front.png)