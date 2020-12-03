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

    function checkDOB(){
        // REFERENCE accessed 23/11/2020
        //https://developer.tizen.org/community/code-snippet/web-code-snippet/how-get-daymonthyear-input-date-type
        //used to retrieve date from user input
        var input = document.getElementById("dob").value;
        var dob = new Date(input);
        var yearDob = dob.getFullYear();
        var currentDate = new Date();
        var currentYear = currentDate.getFullYear();
        //END REFERENCE

        if (currentYear-yearDob<18){
            document.getElementById("parentGuardianEntry").style.display = "block";
        } else {
            document.getElementById("parentGuardianEntry").style.display = "none";
        }

}