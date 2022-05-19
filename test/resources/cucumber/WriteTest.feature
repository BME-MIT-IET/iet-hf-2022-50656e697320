Feature: WriteTest
  Test if a given rdfs graph has all the necessary  readable. 


  Scenario: Write Primtives
    Given An object with primitive values.
    | string    | int | uri     | float | double | char |
    | str value | 8   | urn:any | 4.5f  | 20.22  | o    |
    And  I make a result graph by wirte the object to it.
    When halo '/data/primitives.nt'
    Then I should get "true"


  Scenario: Write Mixed
    Given An object instance of ClassWithPrimitives and an ClassWithMixed.
    | string           | child/string | child/int | child/uri | child/float | child/double | child/char |
    | class with mixed | str value    | 8         | urn:any   | 4.5f        | 20.22        | o          |
    And  I make a result graph by wirte the instance of ClassWithMixed to it.
    When halo '/data/mixed.nt'
    Then I should get "true"
    
