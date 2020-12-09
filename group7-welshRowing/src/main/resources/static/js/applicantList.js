

$(document).ready(function() {
    $("#acceptButton").click(function(e) {
        print('Click works')
        var aID = athleteID.innerHTML;
        $.ajax({
            type : "GET",
            url : "/applicantToAthlete",
            dataType : "json",
            data : {
                "id" : aID
            },
            success: function(data){
                //response from controller

            }
        });
        location.reload()
    });
});




