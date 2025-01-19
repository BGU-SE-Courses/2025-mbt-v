/* @provengo summon selenium */

const SESSIONS = {
  teacher: {name: 'teacher', username: 'teacher', password: 'sandbox24'},
  student: {name: 'student', username: 'student', password: 'sandbox24'}
}


/**
 * Story: Student marks 2 choices
 * The method:
 * 1. Opens a new browser session
 * 2. Goes to: https://sandbox.moodledemo.net/login/index.php
 * 3. Logs in with student credentials
 * 4. Navigates to the course page
 * 5. Navigates to the quiz page
 * 6. Starts the quiz and fills 2 answers
 */
bthread('student mark 2 choices', function () {
  let s = new SeleniumSession(SESSIONS.student.name)
  s.start(URL)
  login(s, {'username': SESSIONS.student.username, 'password': SESSIONS.student.password})
  get_course(s)
  get_quiz_attempt(s)
  fill_answers(s)
})

/**
 * Story: Teacher changes quiz to single choice
 * The method:
 * 1. Opens a new browser session
 * 2. Goes to: https://sandbox.moodledemo.net/login/index.php
 * 3. Logs in with teacher credentials
 * 4. Navigates to the course page
 * 5. Navigates to the quiz page
 * 6. Changes the quiz to single choice
 * 7. Saves the changes
 */
bthread('teacher change quiz to single choice', function () {
  let s = new SeleniumSession(SESSIONS.teacher.name)
  s.start(URL)
  login(s, {'username': SESSIONS.teacher.username, 'password': SESSIONS.teacher.password})
  get_course(s)
  goto_edit_quiz(s)
  edit_quiz(s)
})
 
/**
 * Constraint: Teacher edits quiz before student manages to fill 2 answers
 */
bthread("teacher edits quiz before student fills 2 answers", function () {
  sync({
    waitFor: Event("end_edit_quiz", {session: {name: SESSIONS.teacher.name}}),
    block: Event("start_go_to_quiz_attempt", {session: {name: SESSIONS.student.name}})
  })
})