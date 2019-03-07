Feature: Search on google
Scenario Outline: Get and Search on google
Given I am on google page
When I enter a search "<term>"
When I click Search
Then I should see searched page

Examples:
|term|
|YouTube|
|FaceBook|
