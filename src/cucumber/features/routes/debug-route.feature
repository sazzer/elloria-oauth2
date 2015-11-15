Feature: Debug controller
    Scenario: ping
        When I ping the debug controller
        Then the debug response matches:
            | Text         | Pong       |
            | Status Code  | 200        |
            | Content Type | text/plain |

    Scenario: now
        Given the current time is 2015-11-15T10:46:15Z
        When I request the current server time
        Then the debug response matches:
            | Text         | 2015-11-15T10:46:15+00:00 |
            | Status Code  | 200                       |
            | Content Type | text/plain                |
