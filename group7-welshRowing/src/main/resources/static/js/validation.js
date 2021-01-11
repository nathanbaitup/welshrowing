function checkNumbers(){
    var sessionDuration = document.getElementById("sessionDuration").value;
    var numberFormatter = /^[0-9]+$/;

    if (sessionDuration.match(numberFormatter)){
        return true;
    } else {
        alert('Please only enter digits.');
        return false;
    }
}

function validPhoneNumber(phoneField){
    var patternToFollow = /^\d{11}$/;

    if (phoneField.value.match(patternToFollow)) {
        return true;
    } else {
        alert("Please enter a valid phone number.");
        return false;
    }
}

function validPostcode(postcode){
    var pattern = /^[A-Z]{1,2}[0-9]{1,2} ?[0-9][A-Z]{2}$/i

    if (postcode.value.match(pattern)) {
        return true;
    } else {
        alert("Please enter a valid postcode following LL00 0LL.");
        return false;
    }
}