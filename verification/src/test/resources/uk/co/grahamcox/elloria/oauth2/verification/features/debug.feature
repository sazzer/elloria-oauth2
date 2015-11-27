Feature: Dummy Controller

  Scenario Outline: Request Status Code
    When I get the debug status for <Status>
    Then the debug status code is <Status>

    Examples:
    | Status |
    | 200    |
    | 400    |
    | 500    |

  @wip
  Scenario: Request Ping
    When I ping the debug controller
    Then I the debug response is a pong

  @wip
  Scenario: Request current time
    When I request the current time from the debug controller
    Then I get roughly the current time from the debug controller
