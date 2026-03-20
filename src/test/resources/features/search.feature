Feature: Walmart Search

  Scenario: Search for iPhone
    Given user opens Walmart
    When user searches for "iPhone"
    Then results should be displayed