Feature: Testing the API's

Scenario: Admin Authentication
Given I create a new request with https://localhost:8095/api/ service
And I add the admin/authentication endpoint to the service
And I pass application/json as content type
And I send the values of src/test/resources/cucumberResources/adminAuthInput.json in the request body
And I send the POST request to the service
Then I get the 201 response code

Scenario: Customer Authentication
Given I create a new request with https://localhost:8095/api/ service
And I add the customer/authentication endpoint to the service
And I pass application/json as content type
And I send the values of src/test/resources/cucumberResources/customerAuthInput.json in the request body
And I send the POST request to the service
Then I get the 201 response code




