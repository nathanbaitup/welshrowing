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