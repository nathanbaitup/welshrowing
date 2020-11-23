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

function showHiddenParentFields(){
    var parentNeeded = document.getElementById("parentNeeded");

    if (parentNeeded.checked == true) {
        document.getElementById("parentName").style.display = "block";
        document.getElementById("parentEmail").style.display = "block";
        document.getElementById("relationshipToAthlete").style.display = "block";
        document.getElementById("parentNumber").style.display = "block";
    } else {
        document.getElementById("parentName").style.display = "none";
        document.getElementById("parentEmail").style.display = "none";
        document.getElementById("relationshipToAthlete").style.display = "none";
        document.getElementById("parentNumber").style.display = "none";
    }


}