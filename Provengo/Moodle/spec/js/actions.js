function login(session, data) {
  sync({ request: Event('start_login', {'session': {'name': session.name}, 'data': data}) });
  with(session) {
    writeText(xpaths.login_page.username, data.username);
    writeText(xpaths.login_page.password, data.password);
    click(xpaths.login_page.login_button);
  }
  sync({ request: Event('end_login', {'session': {'name': session.name}, 'data': data}) });
}

function get_course(session) {
  sync({ request: Event('start_get_courses', {'session': {'name': session.name}}) });
  with(session) {
    click(xpaths.home_page.my_courses);
    click(xpaths.my_courses_page.course);
  }
  sync({ request: Event('end_get_courses', {'session': {'name': session.name}}) });
}

function get_quiz_attempt(session) {
  sync({ request: Event('start_go_to_quiz_attempt', {'session': {'name': session.name}}) });
  with(session) {
    click(xpaths.course_page.quiz);
    click(xpaths.quiz_start_page.attempt_quiz);
  }
  sync({ request: Event('end_go_to_quiz_attempt', {'session': {'name': session.name}}) });
}

function fill_answers(session) {
  sync({ request: Event('start_fill_answers', {'session': {'name': session.name}}) });
  with(session) {
    click(xpaths.quiz_page.option1);
    click(xpaths.quiz_page.option2);
  }
  sync({ request: Event('end_fill_answers', {'session': {'name': session.name}}) });
}

function goto_edit_quiz(session) {
  sync({ request: Event('start_goto_edit_quiz', {'session': {'name': session.name}}) });
  with(session) {
    click(xpaths.course_page.quiz);
    click(xpaths.quiz_start_page.questions);
    click(xpaths.quiz_edit_page.start_edit_quiz);
  }
  sync({ request: Event('end_goto_edit_quiz', {'session': {'name': session.name}}) });
}

function edit_quiz(session) {
  sync({ request: Event('start_edit_quiz', {'session': {'name': session.name}}) });
  with(session) {
    moveToElement(xpaths.quiz_edit_page.question_type_dropdown);
    click(xpaths.quiz_edit_page.question_type_dropdown);
    click(xpaths.quiz_edit_page.mark_single_answer);
    moveToElement(xpaths.quiz_edit_page.save_changes);
    click(xpaths.quiz_edit_page.save_changes);
  }
  sync({ request: Event('end_edit_quiz', {'session': {'name': session.name}}) });
}