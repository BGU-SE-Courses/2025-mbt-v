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


Feature: Teacher changes question type to single answer

  Scenario: Teacher modifies a file submission question to allow only one answer
    Given a logged-in teacher is on the Moodle home page
    When the teacher logs in with username "<Email>" and password "<Password>"
    And the teacher navigates to the course page
    And the teacher creates a quiz
    And the teacher creates a multiple-choice question
    And the teacher adds question to quiz
    And the teacher edits the question to 'One answer only'
    Then the question should only allow one answer selection


  Examples:
    | Email   | Password  |
    | teacher | sandbox24 |
