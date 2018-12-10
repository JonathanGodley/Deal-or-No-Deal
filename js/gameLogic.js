/***************************************************************************************
 * Title: Javascript Post Function
 * Author: Rakesh Pai - https://stackoverflow.com/users/20089/rakesh-pai
 * Date: 25 Sep 2008
 * Code Version: 8
 * Availability: https://stackoverflow.com/questions/133925/javascript-post-request-like-a-form-submit
 *
 * Modified: Jonathan Godley - c3188072
 * Last modified:  2/05/2018
 *
 * I've modified this to take fewer parameters, renamed it for clarity
 *   and commented it for my own understanding.
 ***************************************************************************************/
function gameLogic(params) {
  // create a form for submission
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", "Game");

  // create hidden fields to hold our data on POST
  for (var key in params) {
    if (params.hasOwnProperty(key)) {
      var hiddenField = document.createElement("input");
      hiddenField.setAttribute("type", "hidden");
      hiddenField.setAttribute("name", key);
      hiddenField.setAttribute("value", params[key]);

      // add our hidden field to the form
      form.appendChild(hiddenField);
    }
  }

  // add our form to the document
  document.body.appendChild(form);

  //submit the form as a post
  form.submit();
}
