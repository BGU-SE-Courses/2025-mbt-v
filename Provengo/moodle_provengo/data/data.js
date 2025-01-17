/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URL = 'https://sandbox.moodledemo.net/login/index.php';

const xpaths = {
  login_page: {
    username: '//*[@id="username"]',
    password: '//*[@id="password"]',

    login_button: '//*[@id="loginbtn"]',
  },
  home_page: {
    my_courses: '//*[@id="moremenu-678a778e6871f-navbar-nav"]/li[3]/a',
  },
  my_courses_page: {
    course: '//*[@id="inst4"]/div[2]/div[1]/div[1]/div[1]/h3/a',
  },
}