$(document).ready(function() {
    $.ajax({
        url: "http://localhost:49000/api/accounts/all"
    }).then(function(data) {
        var listc= [];
       $.each(data.comments, function (index, value) {
               listc.push(value.comment+", "); 
       })
       $('.msg-author').append(data.accountID);
       $('.msg-content').append(data.message);
       $('.msg-date').append(data.created);
       $('.msg-comments').append(listc);    
    });
});
