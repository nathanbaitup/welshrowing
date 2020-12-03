//REFERENCE accessed 30/11/2020
//https://stackoverflow.com/a/6982840
// Used Jquery to set the 'today's date' fields default value in session-rpe-form to the current date.

$(document).ready( function() {
    var now = new Date();
    var month = (now.getMonth() + 1);
    var day = now.getDate();
    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;
    var today = now.getFullYear() + '-' + month + '-' + day;
    $('#date').val(today);
});

// END REFERENCE