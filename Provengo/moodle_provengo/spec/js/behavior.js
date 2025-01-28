/* @provengo summon selenium */
/* @provengo summon ctrl */

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


// this function creates a bthread that marks the index of the action in the session (its order of occurrence)
function actionMarker(action, session) {
  bthread(`mark ${action} action`, function () {
    const endEvents = [
        Event(`end_login`, {'session': {'name': SESSIONS.student.name}, 'data': {'username': SESSIONS.student.username, 'password': SESSIONS.student.password}}),
        Event(`end_get_courses`, {'session': {'name': SESSIONS.student.name}}),
        Event(`end_go_to_quiz_attempt`, {'session': {'name': SESSIONS.student.name}}),
        Event(`end_fill_answers`, {'session': {'name': SESSIONS.student.name}}),
        Event('end_login', {'session': {'name': SESSIONS.teacher.name}, 'data': {'username': SESSIONS.teacher.username, 'password': SESSIONS.teacher.password}}),
        Event('end_get_courses', {'session': {'name': SESSIONS.teacher.name}}),
        Event('end_goto_edit_quiz', {'session': {'name': SESSIONS.teacher.name}}),
        Event('end_edit_quiz', {'session': {'name': SESSIONS.teacher.name}})
    ]
    let signaledEvent = sync({waitFor: endEvents})
    let i = 0;
    while (signaledEvent.name !== `end_${action}` || signaledEvent.data.session.name !== session.name) {
      signaledEvent = sync({waitFor: endEvents})
      i++
    }

    sync({request: Ctrl.markEvent(`Action:${action}Session:${session.name}Index:${i}`)})
  })
}

let studentActions = ['login', 'get_courses', 'go_to_quiz_attempt', 'fill_answers']
let teacherActions = ['login', 'get_courses', 'goto_edit_quiz', 'edit_quiz']

let studentActionsWithSession = studentActions.map(action => [action, SESSIONS.student])
let teacherActionsWithSession = teacherActions.map(action => [action, SESSIONS.teacher])


studentActionsWithSession.forEach(action => actionMarker(action[0], action[1]))
teacherActionsWithSession.forEach(action => actionMarker(action[0], action[1]))


function isValidSequence(sequence) {
  let editQuizIndex = sequence.findIndex(
      ([action, session]) => (action === 'edit_quiz' && session === SESSIONS.teacher)
  );
  let getToQuizAttemptIndex = sequence.findIndex(
      ([action, session]) => (action === 'go_to_quiz_attempt' && session === SESSIONS.student)
  );

  // If either action is missing or constraint is violated, the sequence is invalid
  if (
      editQuizIndex === -1 ||
      getToQuizAttemptIndex === -1 ||
      editQuizIndex >= getToQuizAttemptIndex
  ) {
    return false;
  }
  return true;
}

// generates all possible sequences of actions
function generateAllValidSequences() {
  const allActions = studentActionsWithSession.concat(teacherActionsWithSession);

  function permute(arr) {
    if (arr.length === 0) return [[]];
    return arr.flatMap((item, i) =>
        permute([...arr.slice(0, i), ...arr.slice(i + 1)]).map((p) => [item, ...p])
    );
  }

  return permute(allActions).filter(isValidSequence);
}

// creates a bthread that marks a specific sequence of actions
function pairwise(sequence, sequenceIndex) {
  bthread(`pairwise sequence ${sequenceIndex}`, function () {
    sequence.forEach((action, index) => {
        let actionName = action[0];
        let session = action[1];
        sync({waitFor: Event(`Action:${actionName}Session:${session.name}Index:${index}`)})
        sync({request: Ctrl.markEvent(`sequence:${sequenceIndex}`)})
    })
  })
}

let sequences = generateAllValidSequences();

sequences.forEach((sequence, index) => pairwise(sequence, index))