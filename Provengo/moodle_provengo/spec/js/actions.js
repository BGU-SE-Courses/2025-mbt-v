function composeQuery(session, data) {
  session.writeText(xpaths.searchWindow.searchInput, data.text)
}

function startSearch(session) {
  with(session) {
    click(xpaths.searchWindow.searchButton)
  }
}

function feelLucky(session) {
  with(session) {
    click(xpaths.searchWindow.feelingLuckyButton)
  }
}