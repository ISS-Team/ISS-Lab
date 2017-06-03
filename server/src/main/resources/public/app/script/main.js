/**
 * Created by Afterwind on 5/31/2017.
 */
$(document).ready(function () {
    $(function () {
        $(".datepicker").datetimepicker({timepicker: false, format: 'Y-m-d'});
        $(".datetimepicker").datetimepicker({timepicker: true, format: "c"});
    });
    //se incarca automat la deschiderea paginii

    $("#About").click(function () {
        show("#generalInformations");

        //apel populare
        $.ajax({
            url: "/conferences/getall", //schimbat
            success: function (data) {
                $("#tableGeneralInformations > tbody").empty();
                var tr;
                for (var i = 0; i < data.length; i++) {
                    tr = $('<tr/>');
                    tr.append("<td class='id'>" + data[i].id + "</td>");
                    tr.append("<td>" + data[i].title + "</td>");
                    tr.append("<td>" + data[i].theme + "</td>");
                    tr.append("<td>" + data[i].deadlineAbstractInfo + "</td>");
                    tr.append("<td>" + data[i].deadlineFullPaper + "</td>");
                    tr.append("<td>" + data[i].deadlineReview + "</td>");
                    tr.append("<td>" + data[i].startTime + "</td>");
                    tr.append("<td>" + data[i].endTime + "</td>");
                    tr.click(function(ev) {
                        showConferenceInformation(getParent($(ev.target), "tr"));
                    });
                    $("#tableGeneralInformations > tbody").append(tr);
                }
            },
            error: function (res) {
                alert("Eroare " + res);
            }
        });

    });

    $("#Login").click(function () {
        show("#formLogin");
    });

    $("#Register").click(function () {
        show("#formRegister");
    });

    $("#UploadConference").click(function() {
        show("#formConference");
    });

    $("#btnSubmitRegister").click(function () {
        var firstName = $("#firstNameR").val();
        var lastName = $("#lastNameR").val();
        var email = $("#emailR").val();
        var username = $("#usernameR").val();
//    var password=SHA256($("#passwordR").val());
        var password = $("#passwordR").val();
        var position = $("#selectPosition").val();

        var forRegister = {
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "username": username,
            "password": password,
            "position": position
        };

        $.ajax({
            url: "/users/register",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(forRegister),
            success: function (res) {
                show("#formLogin");
            },

            error: function (res) {
                alert("Eroare la inregistrare");
            }
        });
//    var xhr = new XMLHttpRequest();

//       xhr.open("post", "myurl", true);
//	    xhr.send(JSON.stringify(forRegister));
//		alert("Datele au fost trimise");

    });
    $("#btnSubmitLogin").click(function () {

        var username = $("#username").val();
//    var password=SHA256($("#password").val());
        var password = $("#password").val();
        var forLogin = {
            "username": username,
            "password": password
        };

        $.ajax({
            url: "/users/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(forLogin),
            dataType: "json",

            success: function (res) {
                //trebuie modificat in functie de permisie
                if (res.permissionLevel === 0 || res.permissionLevel === 1) {
                    $(".hidden-button").removeClass("hidden-button");
                    show();
                }
                //console.log(res);
            },

            error: function (res) {
                alert("Eroare");
            }
        });
//    var xhr = new XMLHttpRequest();
//       xhr.open("post", "/users/login", true);

//	    xhr.send(JSON.stringify(forLogin));
//		alert("Datele au fost trimise");
    });
    $("#btnSubmitUploadConference").click(function () {
        var title = $("#Title").val();
        var theme = $("#Theme").val();
        var revPerPaper = $("#RevPerPaper").val();
        var deadlineAbstractInfo = $("#DeadlineAbstractInfo").val();
        var deadlineFullPaper = $("#DeadlineFullPaper").val();
        var deadlineReview = $("#DeadlineReview").val();
        var firstDay = $("#FirstDay").val();
        var lastDay = $("#LastDay").val();

        var forUploadConference = {
            "reviewersPerPaper": revPerPaper,
            "title": title,
            "theme": theme,
            "deadlineAbstractInfo": deadlineAbstractInfo,
            "deadlineFullPaper": deadlineFullPaper,
            "deadlineReview": deadlineReview,
            "startTime": firstDay,
            "endTime": lastDay
        };

        $.ajax({
            url: "/conferences/create",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(forUploadConference),
            success: function (res) {
                alert("Success!")
            },

            error: function (res) {
                alert("Eroare la incarcare");
            }
        });
    });
	
	$("#btnUploadPaper").click(function() {
		var title=$("#titleUploadPaper").val();
		var abstractPaper=$("#abstractPaper").val();
		var topics=$("#topics").val().split(',');	
		var keywords=$("#keywords").val().split(',');
		var path=$("#pathUploadPaper").val();					
		var forUploadPaper = {
			"title":title,
			"abstractPaper":abstractPaper,
			"topics":topics,
			"keywords":keywords,
			"path":path
			};
				
		$.ajax({
			url: "/conferences/{conferenceId}/papers/save",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(forUploadPaper),
			dataType: "json",
			success: function (res) {
				alert("Added with success");
				console.log(res);
				},
			error: function(res) {
				alert("Error at adding");
				}
		});
	});
});

function show(id) {
    $(".hidden-box").removeClass("shown");
    if (id !== undefined) {
        $(id).addClass("shown");
    }
}

function showConferenceInformation(row) {
    var info = $("#conferenceInformation").find("#conferenceInfo");
    var conference_id = row.find(".id").html();
    $.ajax({
        url: "/conferences/" + conference_id,
        type: "GET",
        success: function(res) {
            info.empty();
            info.append($("<h2>" + res.title + "</h2>"));
            info.append($("<h4>Theme: " + res.theme + "</h4>"));
            info.append($("<span>" + res.startTime + " - " + res.endTime + "</span>"));
        }
    });
    $.ajax({
        url: "/conferences/" + conference_id + "/sessions/getall",
        type: "GET",

        success: function (data) {
            var tr;
            $("#sessionInfo").find("tbody").empty();
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr/>');
                tr.append("<td>" + data[i].id + "</td>");
                tr.append("<td>" + data[i].title + "</td>");
                tr.append("<td>" + data[i].startTime + "</td>");
                tr.append("<td>" + data[i].duration + "</td>");
                tr.append("<td>" + data[i].paper.title + "</td>");
                tr.append("<td>" + data[i].paper.topics.join(',') + "</td>");
                tr.append("<td>" + data[i].paper.keywords.join(',') + "</td>");
                $("#sessionInfo").find("tbody").append(tr);
            }
        }
    });
    show("#conferenceInformation")
}

function getParent(node, selector) {
    while (!node.is("body")) {
        if (node.is(selector)) {
            return node;
        } else {
            node = node.parent();
        }
    }
    return null;
}