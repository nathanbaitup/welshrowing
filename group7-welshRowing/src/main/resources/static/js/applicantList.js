
$(document).ready(function() {
    $("#acceptButton").click(function(e) {
        e.preventDefault();
        $.ajax({
            type : "GET",
            url : "/applicants",
            data : {
                "id" : "$(\"#athleteID\").val()"
            },
            success: function(data){
                //response from controller
            }
        });
    });
});





