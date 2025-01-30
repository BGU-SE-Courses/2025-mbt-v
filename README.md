# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called Moodle (https://sandbox.moodledemo.net/).

Moodle (Modular Object-Oriented Dynamic Learning Environment) is a widely used open-source learning management system (LMS) designed to facilitate online education and training. It provides educators, institutions, and businesses with a flexible platform for creating, managing, and delivering courses in a virtual learning environment.

## Installation
We did not installed Moodle on our local machine, instead we tested our designated usecases on the Moodle sandbox (link provided above).

## What we tested
We tested the quiz module that allows for checking multiple answers. We chose to test the following user stories: 

*User story:* A teacher changes a multiple choice question to a single choice question.

*Preconditions:* There is a course with a teacher, there is a quiz in the course with a multiple choice question, which has only a single correct answer.

*Expected outcome:* The question is changed to be a single choice question.

*User story:* A students attempts a quiz & marks two choices. 

*Preconditions:* There is a course with a quiz, the student is enrolled to the course, the quiz was changed by the teacher.

*Expected outcome:* The student marks two options in a single answer question successfully.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.