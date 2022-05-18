Feature: ReadTest
  Test if a given rdfs graph has all the necessary  readable.

  Scenario: Result is correct
    Given I have a graph
    And I have a mapper
    When I read from the graph
    Then I should get
      | string    | int | uri     | float | double | char |
      | str value | 8   | urn:any | 4.5f  | 20.22  | o    |
