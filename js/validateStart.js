// validateStart.js
// Simple validation script for username field
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018

function validateStart() {
  var userID = document.getElementById("userID").value;
  var resultStatus = true;
  var messageStr = "The following errors were detected:\n";

  var uidCheck = /^[a-z0-9]+$/i; // regex for alpha-numberic

  if (!userID || userID == "") {
    resultStatus = false;
    messageStr += "UserID is blank\n";
  }

  if (!uidCheck.test(userID)) {
    resultStatus = false;
    messageStr += "UserID must be alpha-numberic\n";
  }

  if (!resultStatus) {
    alert(messageStr);
  }
  return resultStatus;
}
