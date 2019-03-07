$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/cucumber1/test01.feature");
formatter.feature({
  "name": "Search on google",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Get and Search on google",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on google page",
  "keyword": "Given "
});
formatter.step({
  "name": "I enter a search \"\u003cterm\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I click Search",
  "keyword": "When "
});
formatter.step({
  "name": "I should see searched page",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "term"
      ]
    },
    {
      "cells": [
        "YouTube"
      ]
    },
    {
      "cells": [
        "FaceBook"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Get and Search on google",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on google page",
  "keyword": "Given "
});
formatter.match({
  "location": "Test01.toGooglePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter a search \"YouTube\"",
  "keyword": "When "
});
formatter.match({
  "location": "Test01.enterSearchTerm(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click Search",
  "keyword": "When "
});
formatter.match({
  "location": "Test01.ClickTheSearchButton()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should see searched page",
  "keyword": "Then "
});
formatter.match({
  "location": "Test01.weShouldSeeSearchedPage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Get and Search on google",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am on google page",
  "keyword": "Given "
});
formatter.match({
  "location": "Test01.toGooglePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter a search \"FaceBook\"",
  "keyword": "When "
});
formatter.match({
  "location": "Test01.enterSearchTerm(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click Search",
  "keyword": "When "
});
formatter.match({
  "location": "Test01.ClickTheSearchButton()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should see searched page",
  "keyword": "Then "
});
formatter.match({
  "location": "Test01.weShouldSeeSearchedPage()"
});
formatter.result({
  "status": "passed"
});
});