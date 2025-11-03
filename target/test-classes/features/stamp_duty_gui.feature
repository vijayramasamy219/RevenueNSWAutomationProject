Feature: Stamp duty calculator (GUI)

  Scenario Outline: Calculate stamp duty for a new vehicle
    Given I am on the stamp duty calculator page
    When I click on the "Check online" button
    And I select "Yes" for a new vehicle
    And I enter <vehicleAmount> as vehicle amount
    And I click on the "Calculate" button
    Then I verify the calculation popup displays the expected vehicle amount <vehicleAmount> and registration "<registration>"
    Examples:
      | vehicleAmount | registration |
      | 44000         | Yes          |
      | 47000         | Yes          |
      | 85000         | Yes          |