// var stompClient = null;
//
// $(document).ready(function () {
//
//     if (stompClient != null)
//         stompClient.disconnect();
//
//     var socket = new SockJS('/livescore-websocket');
//     stompClient = Stomp.over(socket);
//
//     $("button").click(function () {
//
//         sendData2Socket();
//     });
// });
//
// function sendData2Socket() {
//     var vis = document.querySelector('input[name="genderS"]:checked').value;
//
//     stompClient.send("/app/scorecard", {}, JSON.stringify({vis: vis}));
//     console.log(vis);
//
// }

// var radios = document.getElementsByName('genderS');
//
// function myFunc(){
// 	var d = $("#vis").val()
// 	console.log(d);
// }


var stompClient = null;


$(document).ready(function(){

    if(stompClient!=null)
        stompClient.disconnect();

    var socket = new SockJS('/livescore-websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/livescore', function (scoredata) {

            var scoreJson = JSON.parse(scoredata.body);

            // $("#batting_td").html("Batting Team: "+scoreJson.battingTeamName+" and Total runs are: "+scoreJson.totalRuns);
            // $("#bowling_td").html("Bowling Team: "+scoreJson.bowlingTeamName+" and Total overs are: "+scoreJson.totalOvers);
            // document.querySelector('input[name="genderS"]:checked').value = scoreJson.vis;
            $("#genderS").html(scoreJson.vis);
            if(scoreJson.vis){
                // $("#tr").html(scoreJson.vis);
                $("#tr").attr("checked", true);


            }else{
                // $("#fl").html(scoreJson.vis);
                $("#fl").attr("checked", true);

            }


        });
    });


});
