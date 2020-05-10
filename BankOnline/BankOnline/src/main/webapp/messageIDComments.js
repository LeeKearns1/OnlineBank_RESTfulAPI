$(document).ready(function() {
    $.ajax({
        url: "http://localhost:49000/api/messages/1/comments"
    }).then(function(data) {
        var listc= [];
       $.each(data, function (index, value) {
               listc.push(value.comment+", "); 
       })
       $('.msgID-comments').append(listc);    
    });
});
