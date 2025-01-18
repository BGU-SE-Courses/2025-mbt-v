/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URL = 'https://sandbox.moodledemo.net/login/index.php';

const xpaths = {
  login_page: {
    username: '/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[1]/input',
    password: '/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[2]/div/input',

    login_button: '/html/body/div[2]/div[2]/div/div/section/div/div/div/div/form[1]/div[3]/button',
  },
  home_page: {
    my_courses: '/html/body/div[2]/nav/div/div[1]/nav/ul/li[3]/a',
  },
  my_courses_page: {
    course: '/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/a/span[3]/span[2]',
  },
  course_page: { // assume the course exist:
    quiz: '/html/body/div[2]/div[5]/div/div[3]/div/section/div/div/div/ul/li[2]/div/div[2]/ul/li[1]/div/div[2]/div[2]/div/div/a'
  },
  quiz_start_page: {
    attempt_quiz: '/html/body/div[2]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button',
    questions: '/html/body/div[2]/div[4]/div/div[2]/nav/ul/li[3]/a'
  },
  quiz_page: {
    option1: '/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[1]/input[2]',
    option2: '/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[2]/input[2]'
  },
  quiz_edit_page: {
    start_edit_quiz: '/html/body/div[4]/div[4]/div/div[3]/div/section/div[2]/div[2]/ul/li/div/ul/li[2]/div/div/div[2]/a/span/i',
    question_type_dropdown: '/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/form/fieldset[1]/div[2]/div[9]/div[2]/select',
    mark_single_answer: '/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/form/fieldset[1]/div[2]/div[9]/div[2]/select/option[2]',
    save_changes: '/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/form/div[4]/div[2]/div[1]/div/div[1]/span/input'
  }
}