Feature: ReadTest
  Test if a given rdfs graph has all the necessary  readable.

  Scenario: Read primitives
    Given I have a graph from '/data/primitives.nt'
    And I have a mapper
    When I read an instance of ClassWithPrimitives from the graph
    Then I should get an instance of ClassWithPrimitives
      | string    | int | uri     | float | double | char |
      | str value | 8   | urn:any | 4.5f  | 20.22  | o    |

  Scenario: Read mixed
    Given I have a graph from '/data/mixed.nt'
    And I have a mapper
    When I read an instance of ClassWithMixed from the graph
    Then I should get an instance of ClassWithMixed
      | string           | child/string | child/int | child/uri | child/float | child/double | child/char |
      | class with mixed | str value    | 8         | urn:any   | 4.5f        | 20.22        | o          |
