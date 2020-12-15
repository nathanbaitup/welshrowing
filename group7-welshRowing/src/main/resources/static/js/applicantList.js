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
        location.reload()
    })
    $("#rejectButton").click(function(e) {
        var aID = $(this).closest("tr").find(".athleteID").text().trim()
        console.log(aID);
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
        location.reload()
    })

});
