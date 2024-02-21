# [Frying Pan] Project Rubric

## Background

*This captures the expectations that we have for your team during the unit.
This is how we will evaluate whether you have demonstrated these expectations.*

## Instructions

*As a team, complete this rubric (everything except for the Appendix) by
answering the questions below. Each question should usually only require one or
two sentences, so please be brief. The remainder of expectations will be
evaluated by instructors, so you don’t need to write anything in the Appendix.
We want you to see the full set of expectations for transparency’s sake.*

## Deliverables

*Provide links to the following project deliverables:*

|Deliverable                                                      |Due Date                  |Date Completed |URL                               |
|---                                                              |---                       |---            |---                               |
|Team name                                                        |Sprint 1 Module 1         |               |name:                             |
|[Design Document - problem statement](design_document_template.md)        |Sprint 1 Module 2         |               |                                  |
|[Team Charter](team_charter.md)                                  |Sprint 1 Module 3         |               |                                  |
|[Design Document](design_document_template.md)                            |Sprint 1 REQUIRED TO GO ON|               |                                  |
|Project Completion (Feature Complete)                            |Sprint 3                  |               |                                  |
|[Team Reflection](reflection.md)                                 |Sprint 3                  |               |                                  |
|[Accomplishment Tracking (person 1)](accomplishment_tracking.md) |Sprint 3                  |               |                                  |
|[Accomplishment Tracking (person 2)](accomplishment_tracking.md) |Sprint 3                  |               |                                  |
|[Accomplishment Tracking (person 3)](accomplishment_tracking.md) |Sprint 3                  |               |                                  |
|[Accomplishment Tracking (person 4)](accomplishment_tracking.md) |Sprint 3                  |               |                                  |
|Self Reflection                                                  |Sprint 3                  |               |n/a (will be submitted via Canvas - "Wrap-up" section) |

## Technical Learning Objectives

### API Design

**Design an API to meet the needs of your application.** Provide a link to the
definition for your endpoints (can be code/configuration, or can be
documentation). List one thing that your team learned about designing a good
API.

*Endpoint definition location:*   https://github.com/BloomTechBackend/bd-team-project-frying-pan/blob/LBC2/project_documents/design_document.md     
*What we learned:*    
* Get request should not modified data. In my project's backend, my generatetest method calls "get requests" to the terms table. However, after retrieving the data, it then modifies the data to produce the test models. This side effect did cause problems. As a result, I had to change the endpoint for the function to be a post request, even though it actually does not save an data.

**Develop a service endpoint definition that uses complex inputs and outputs.**
Select one of your endpoints and list the operation’s input and output objects.
Under each, list its attributes.

*Endpoint:* Update Test Template Endpoint   

*Input object(s):*       

* String templateId;
* String title;
* String username;
* String dateModified;
* List<String> hiraganaIdList;
* List<String> katakanaIdList;

*Output object(s):*      

* TemplateModel template 

**Given a user story that requires a user to provide values to a service
endpoint, design a service endpoint including inputs, outputs, and errors.**
Select one of your endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases.

|**Endpoint:**  | Create Account Endpoint |
|---            |---                  |
|**Error case** |**Service response** |
| Success       | The service accepted the request that contains a username, password and repeated password and passes the verification to create a new account. The service notifies the user and moves them to the login tab to continue.                     |
| Client Error | If the request contains invalid data, the user will receive a notification of the mistake. The user can fix their mistake and try to submit their request again                 |
| Server Error  | The service will notify the user that the server failed to process the request. The user can submit the same request and try again with the server ready                   |

**Develop a service endpoint definition that uses query parameters to determine
how results are sorted or filtered.** List at least one endpoint that allows
sorting or filtering of results. Which attribute(s) can be sorted/filtered on?

*Endpoint:* 
Get Template By Username Title Endpoint - this endpoint will retrieve templates based on the queried username and sorted by title. Context: Templates have a primary partition key of a string template Id. This end point is used to get all saved templates by a user based on the username and sorted based on the title. We use a GSI to help query this endpoint. 

*Attribute(s):*  
* String username // GSI partition key
* String title  // GSI sort key


**Determine whether a given error condition should result in a client or server
exception.** List one client exception and one server exception that your
service code throws. List one condition in which this exception is thrown.

|                       |**Exception** |**One case in which it is thrown** |
|---	                |---	       |---	                               |
|**Client exception:**  |	           |	                               |
|**Service exception:** |	           |	                               |

### DynamoDB Table Design

**Decompose a given set of use cases into a set of DynamoDB tables that provides
efficient data access.** List the DynamoDB tables in your project:

1. LBC_Accounts 
2. LBC_Terms 
3. LBC_Test_Templates


**Design a DynamoDB table key schema that allows items to be uniquely
identified.** Describe the primary key structure for your DynamoDB table with
the most interesting primary key. In a sentence or two, explain your choice of
partition/sort key(s).

All 3 table have a simple primary key using only a partition primary key. 
* Accounts have a string username partition key
* Templates have a string template Id partition key
* Terms have a string term Id partition key

The most complex primary key are from the GSIs:
* Get Template By Username Title Endpoint
  * Composite Primary Key with a partition key with string username and a sort key with string tile
* Get Term By Template Date Endpoint
  * Composite Primary Key with a partition key with string template Id and a sort key with string date
* Get Term By Username Date Endpoint
  * Composite Primary Key with a partition key with string username and a sort key with string date
  * The other 2 have actual path endpoints and called form the front end. The back end code used code to query and delete custom terms associated to account to be deleted (in the delete account endpoint)

**Design the attributes of a DynamoDB table given a set of use cases.** List a
DynamoDB table with at least 3 attributes. List one relevant use case that uses
the attribute. In one sentence, describe why the attribute is included.

**Table name:**  LBC_Test_Template

**Attributes:** 

|Attribute name |(One) relevant use case |attribute purpose |
|---            |---                     |---               |
| templateId    | Users want to get, edit and save their templates | Uniquely identify the test template stored on the table |
| title | Users want to see their saved templates in alphabetical order | Saves the title given by the user |
| username | Users want to see a list of their saved templates | Saves the username that made the template|
| hiraganaIdList | Loading the data for the hiragana ids | Saves the hiragana Ids to the template |
| katakanaIdList | Loading the data for the katakana ids | Saves the katakana Ids to the template |

### DynamoDB Indexes

**Design a GSI key schema and attribute projection that optimizes queries not
supported by a provided DynamoDB table.** In one or two sentences, explain why
you created one of the GSIs that your project uses, including one use case that
uses that index.

**Table name:** LBC_Test_Template

**Table keys:** primary partition key: templateId

**GSI keys:** partition key: username, sort key: title

**Use case for GSI:** To retrieve a list of templates created by the user and sorted by title. The retrieved list will be displayed the user (then they can select which one they wish to open and edit).


**Implement functionality that uses query() to retrieve items from a provided
DynamoDB's GSI.** List one of your use cases that uses `query()` on a GSI.

**Table name:** UsernameTitleIndex

**Use case for `query()` on GSI:**
To retrieve a list of templates created by the user and sorted by title. The retrieved list will be displayed the user (then they can select which one they wish to open and edit).

## Soft(er) Outcomes

**Learn a new technology.** For each team member, list something new that that
team member learned:

|Team Member |Something new the team member learned |   
|---   |---                                   |
| Andy Pan | Yaml and json for aws database creation. Javascript functions, logging, debugging with chrome's console and creating event listeners|   
|      |                                      |     
|      |                                      |     
|      |                                      |     

**Identify key words to research to accomplish a technical goal | Use sources
like sage and stack overflow to solve issues encountered while programming.**
Give an example of a search term that your team might have used to find an
answer to a technical question/obstacle that your team ran into. List the
resource that you found that was helpful.

**Search terms:**   
* Cloud Formation 
* Japanese and Chinese Terms in Java
* Json file format verification 
* java dependencies
* axios method parameters and delete parameters

**Helpful resource:** 
* aws documentation  
* stack overflow   
* Perplexity / OpenAI
* https://mvnrepository.com

**Find material online to learn new technical topics.** List one resource that
your team found on your own that helped you on your project.

**Topic:**
Creating tabs in html, css and javascript 

**Resource:**
https://www.w3schools.com/howto/howto_js_full_page_tabs.asp

**Share what was worked on yesterday, the plan for today, and any blockers as a
part of a scrum standup.** In one or two sentences, describe what your team
found to be the most useful outcome from holding daily standups.

I finished the last create and delete custom term end point on the front end. 

I need to to test the complete project from start (creating an account) to finish (generate a test with hiragana, katakana and custom terms). 

Previous blockers
* CloudFormation stacks and yaml file failing to compile
* javascript axios request and response - data handling and sending requests failing

Scrum helped me tracked with I have and have not completed. When ever I had blockers I would immediately schedule time with LA's or reach out on slack to request help. 