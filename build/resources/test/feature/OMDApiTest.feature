Feature:OMDbApi Search and imdbID

  @Test01
  Scenario: TC01 Harry Potter ve imdbID Get Request
    Given Kullanici "Harry Potter" icin request gonderir
    When Kullanici  "Harry Potter and the Sorcerer's Stone" filminin id sini alir
    Then Kullanici alinan imdbID ile request gonderir
    And Kullanici gelen response ile dogrulama yapar