Feature: WriteTest
  Test if a given rdfs graph has all the necessary  readable. 

  Scenario: Result is correct
    Given graph and a result
    And result made from graph
    When I ask if Expected should equal to result
    Then I should get "false"
  
  Scenario: Result is not correct
    Given graph and a result
    And result made from graph
    When I ask if Expected should equal to result
    Then III should get "false"
