Feature: Debug controller
    Scenario: ping
        When I ping the debug controller
        Then the ping response matches:
            | Text         | Pong       |
            | Status Code  | 200        |
            | Content Type | text/plain |
