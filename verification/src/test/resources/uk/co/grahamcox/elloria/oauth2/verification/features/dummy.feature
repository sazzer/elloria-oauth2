Feature: Dummy Controller
  @wip
  Scenario Outline: Request Status Code
    When I GET /api/debug/status/<Status>
    Then I get a status code of <Status>

    Examples:
    | Status |
    | 200    |
    | 400    |
    | 500    |