Feature: WriteTest
  Test if a given rdfs graph has all the necessary  readable. 

  Scenario: Wite Primtives
    Given An object with primitive values.
    And  I make a result graph by wirte the object to it.
    When I ask if the expected is equal to the result graph.
    Then I should get "true"

