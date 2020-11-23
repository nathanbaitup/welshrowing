// REFERENCE accessed 23/11/2020
//https://stackoverflow.com/a/18893556
//used to ensure password and confirm password values match
function checkPasswordsMatch(input) {
    if (input.value != document.getElementById('password').value) {
        input.setCustomValidity('Passwords Must be Matching.');
    } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
    }
}
//END REFERENCE