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
                    tr.append("<td>" + moment(data[i].deadlineAbstractInfo).format("LL") + "</td>");
                    tr.append("<td>" + moment(data[i].deadlineFullPaper).format("LL") + "</td>");
                    tr.append("<td>" + moment(data[i].deadlineReview).format("LL") + "</td>");
                    tr.append("<td>" + moment(data[i].startTime).format("LLL") + "</td>");
                    tr.append("<td>" + moment(data[i].endTime).format("LLL") + "</td>");
                    tr.click(function (ev) {
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

    $("#UploadConference").click(function () {
        show("#formConference");
    });

    $("#PaperInformation").click(function () {
        showPaperInformation();
    });

    $("#UploadPaper").click(function () {
        show("#formUploadPaper");
    });

    $("#BiddingPhase").click(function () {
        show("#biddingPapers");
        $("#biddingPapers").find("tbody").empty();
        $.ajax({
            url: "/conferences/" + window.selectedConferenceId + "/papers/getall",
            type: "GET",
            success: function (res) {
                for (var i = 0; i < res.length; i++) {
                    var paper = res[i];
                    var tr = $("<tr></tr>");
                    tr.append($("<td>" + paper.id + "</td>"));
                    tr.append($("<td>" + paper.title + "</td>"));
                    var td = $("<td></td>");
                    var radioReject = $("<label class='radio-inline'><input type='radio' value='REJECTED' name='optradio" + paper.id + "'>Reject</label>");
                    var radioNeutral = $("<label class='radio-inline'><input type='radio' checked='checked' value='NEUTRAL' name='optradio" + paper.id + "'>Neutral</label>");
                    var radioAccept = $("<label class='radio-inline'><input type='radio' value='ACCEPTED' name='optradio" + paper.id + "'>Accept</label>");
                    td.append(radioReject);
                    td.append(radioNeutral);
                    td.append(radioAccept);
                    tr.append(td);
                    $("#biddingPapers").find("tbody").append(tr);
                }
            }
        });
        $("#biddingPapers").find("input[type='button']").click(function () {
            $.each($("#biddingPapers").find("tbody tr"), function (i, val) {
                var paperId = $(this).find("td:first-child").html();
                var status = $(this).find("input:checked").val();
                $.ajax({
                    url: "/conferences/" + window.selectedConferenceId + "/papers/bid/" + paperId,
                    type: "POST",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify({status: status})
                });
            });
        });
    });

    $("#ReviewingPhase").click(function () {
        show("#reviewingPapers");
        $("#tableOfPapersR").find("tbody").empty();
        $.ajax({
            url: "/conferences/" + window.selectedConferenceId + "/papers/getincomplete",
            type: "GET",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var tr = $('<tr/>');
                    tr.append("<td class='id'>" + data[i].reviewedPaper.id + "</td>");
                    tr.append("<td>" + data[i].reviewedPaper.title + "</td>");
                    tr.append("<td> <div class='col-xs-6 selectContainer'> " +
                        "<select class='form-control' name='review" + data[i].id + "'> " +
                        "<option value='STRONG_ACCEPT'>Strong Accept</option> " +
                        "<option value='ACCEPT'>Accept</option> " +
                        "<option value='WEAK_ACCEPT'>Weak Accept</option> " +
                        "<option value='BORDERLINE'>Borderline</option> " +
                        "<option value='WEAK_REJECT'>Weak Reject</option> " +
                        "<option value='REJECT'>Reject</option> " +
                        "<option value='STRONG_REJECT'>Strong Reject</option> " +
                        "</select> " +
                        "</div> </td>");
                    $("#tableOfPapersR").find("tbody").append(tr);
                }
            },
            error: function (res) {
                alert("Eroare");
            }
        });
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
            "permissionLevel": position
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
                $(".hidden-by-login-listener").removeClass("hidden-by-login-listener");
                $(".hidden-by-logged-in").removeClass("shown");
                if (res.permissionLevel >= 1) {
                    $(".hidden-by-login-author").removeClass("hidden-by-login-author");
                }
                if (res.permissionLevel >= 2) {
                    $(".hidden-by-login-member").removeClass("hidden-by-login-member");
                }
                if (res.permissionLevel >= 3) {
                    $(".hidden-by-login-chair").removeClass("hidden-by-login-chair");
                }
                show();
            },

            error: function (res) {
                alert("Eroare");
            }
        });
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
    $("#btnUploadPaper").click(function () {
        var title = $("#titleUploadPaper").val();
        var abstractPaper = $("#abstractPaper").val();
        var topics = $("#topics").val().split(',');
        var keywords = $("#keywords").val().split(',');
        var path = $("#pathUploadPaper").val();
        var forUploadPaper = {
            "title": title,
            "abstractPaper": abstractPaper,
            "topics": topics,
            "keywords": keywords,
            "pathFile": path
        };

        $.ajax({
            url: "/conferences/" + window.selectedConferenceId + "/papers/save",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(forUploadPaper),
            dataType: "json",
            success: function (res) {
                alert("Added with success");
                console.log(res);
            },
            error: function (res) {
                alert("Error at adding");
            }
        });
    });

    $("#reviewSubmit").click(function () {
        var date = Math.round((new Date()).getTime() / 1000);
        $.each($("#reviewingPapers").find("tbody tr"), function() {
            var paperId = $(this).find(".id").html();
            var qualifier = $(this).find("select").val();
            var reviewInfo = { "date": date, "qualifier": qualifier };
            $.ajax({
                url: "/conferences/" + window.selectedConferenceId + "/papers/" + paperId + "/reviews/review",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(reviewInfo),
                success: function () {
                    alert("Success");
                },
                error: function () {
                    alert("Eroare la recenzie hartie");
                }
            })
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
    var conferenceId = row.find(".id").html();
    window.selectedConferenceId = conferenceId;
    $(".hidden-by-conference").removeClass("hidden-by-conference");
    $.ajax({
        url: "/conferences/" + conferenceId,
        type: "GET",
        success: function (res) {
            $(".conference-name").html("Conf: " + res.title + " (" + res.id + ")");
            info.empty();
            info.append($("<h2>" + res.title + "</h2>"));
            info.append($("<h4>Theme: " + res.theme + "</h4>"));
            info.append($("<span> From <strong>" + moment(res.startTime).format("LLL") + "</strong> until <strong>" + moment(res.endTime).format("LLL") + "</strong></span>"));
        }
    });
    $.ajax({
        url: "/conferences/" + conferenceId + "/sessions/getall",
        type: "GET",

        success: function (data) {
            var tr;
            $("#sessionInfo").find("tbody").empty();
            for (var i = 0; i < data.length; i++) {
                tr = $('<tr/>');
                tr.append("<td class='id'>" + data[i].id + "</td>");
                tr.append("<td>" + data[i].title + "</td>");
                tr.append("<td>" + data[i].startTime + "</td>");
                tr.append("<td>" + data[i].duration + "</td>");
                tr.append("<td>" + data[i].paper.title + "</td>");
                tr.append("<td>" + data[i].paper.topics.join(',') + "</td>");
                tr.append("<td>" + data[i].paper.keywords.join(',') + "</td>");
                var participateButton = $("<input type='button' value='Participate'>");
                participateButton.click(function () {
                    var btn = $(this);
                    var sessionId = getParent(btn, "tr").find(".id").html();
                     $.ajax({
                         url: "/conferences/" + conferenceId + "/sessions/participate/" + sessionId,
                         type: "GET",
                         success: function() {
                             btn.prop("disabled", "disabled");
                         },
                         error: function () {
                             alert("Failed to process request...");
                         }
                     });
                });
                var participateTd = $("<td></td>");
                participateTd.append(participateButton);
                tr.append(participateTd);
                $("#sessionInfo").find("tbody").append(tr);
            }
        }
    });
    show("#conferenceInformation");
}

function showPaperInformation() {
    $.ajax({
        url: "/conferences/" + window.selectedConferenceId + "/papers/getall",
        type: "GET",

        success: function (data) {
            $("#papersInfoTable").find("tbody").empty();
            for (var i = 0; i < data.length; i++) {
                var tr = $('<tr/>');
                tr.append("<td id='paper" + data[i].id + "'>" + data[i].id + "</td>");
                tr.append("<td>" + data[i].title + "</td>");
                tr.append("<td>" + data[i].abstractPaper + "</td>");
                tr.append("<td>" + data[i].pathFile + "</td>");
                tr.append("<td>" + data[i].author.username + "</td>");
                if (data[i].reviews.length === 0) {
                    tr.append("<td>" + "uploaded" + "</td>");
                }
                else {
                    var incompleteReviews = 0;
                    for (var j = 0; j < data[i].reviews.length; j++) {
                        if (data[i].reviews[j].date === -1) {
                            incompleteReviews++;
                        }
                    }
                    if (incompleteReviews > 0) {
                        tr.append("<td>" + "reviewing" + "</td>");
                    }
                    else {
                        tr.append("<td>" + "reviewed" + "</td>");
                    }
                }
                $("#papersInfoTable").find("tbody").append(tr);
            }
        }
    });
    show("#papersInformation");
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