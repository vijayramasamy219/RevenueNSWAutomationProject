Feature: OpenLibrary author API

  Scenario: Validate author details for OL1A
    Given I call the authors endpoint for OL1A
    Then Check the response status code is 200
    And Verify the person name "personal_name" is "Sachi Rautroy"
    And Verify the alternate name "alternate_names" is "Yugashrashta Sachi Routray"
