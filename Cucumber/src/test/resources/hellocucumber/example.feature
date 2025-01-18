Feature: Student fills questionnaire in Moodle
  Scenario Outline: Student fills out a question with multiple choices and chooses two answers
    Given a logged in user is in the home page
    When the user logged in with username "<Email>" and password "<Password>"
    And the user navigates to the course page
    And the user navigates to quiz page
    And the user fills out a question with multiple choices and chooses two answers
    Then the student's answer is recorded
    Examples:

      | Email  | Password |
      | student| sandbox24  |

