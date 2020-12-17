$(document).ready(function() {
    $("#acceptButton").click(function(e) {
        var aID = $(this).closest("tr").find(".athleteID").text().trim()
        console.log(aID);
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
    })
    $("#rejectButton").click(function(e) {
        var aID = $(this).closest("tr").find(".athleteID").text().trim()
        console.log(aID);
        console.log("got here")
        $.ajax({
            type : "GET",
            url : "/rejectApplicant",
            dataType : "json",
            data : {
                "id" : aID
            },
            success: function(data){
                //response from controller

            }
        });
    })

});
