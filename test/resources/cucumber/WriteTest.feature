Feature: WriteTest
  Test if a given rdfs graph has all the necessary  readable. 

  Scenario: Write Primtives
    Given An object with primitive values.
    And  I make a result graph by wirte the object to it.
    When I ask if the expected is equal to the result graph.
    Then I should get "true"
    | string    | int | uri     | float | double | char |
    | str value | 8   | urn:any | 4.5f  | 20.22  | o    |

    Scenario: Write Mixed
    Given An object instance of ClassWithPrimitives and an ClassWithMixed
    And  I set the instance of Primitive class the child of the instance of ClassWithMixed;
    And  I make a result graph by wirte the the instance of ClassWithMixed to it.
    When I ask if the expected is equal to the result read graph from '/data/mixed.nt'.
    Then I should get "true"

    | string           | child/string | child/int | child/uri | child/float | child/double | child/char |
    | class with mixed | str value    | 8         | urn:any   | 4.5f        | 20.22        | o          |

