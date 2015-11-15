Feature: Debug controller
    Scenario: ping
        When I ping the debug controller
        Then the ping response is "Pong"
        And the ping status code is 200
        And the ping content type is "text/plain"
