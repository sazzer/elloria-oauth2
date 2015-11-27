Feature: Dummy Controller
  @wip
  Scenario Outline: Request Status Code
    When I get the debug status for <Status>
    Then the debug status code is <Status>

    Examples:
    | Status |
    | 200    |
    | 400    |
    | 500    |