/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var customerID;
var customerEmail;
var customerPass;

$("#formsubmit").click(function () {
    registerAccount();
});

//Register Customer
function registerAccount() {
    var fname = $("#full-name").val();
    var address = $("#add1").val() + ", " + $("#add2").val() + ", " + $("#add3").val();
    var email = $("#email").val();
    var pass = $("#pass").val();
    var cpass = $("#c_password").val();

    var url = "/api/customer/create?name=" + fname + "&address=" + address + "&email=" + email + "&password=" + pass;
    $.post(url);
    window.location.href = "http://localhost:49000/index.html";
}
function tryLogin() {
    customerEmail = $("#loginEmail").val();
    customerPass = $("#loginPass").val();
    getAccount(customerEmail, customerPass);

}
function tryLogout() {
    $("#loginForm").removeClass("d-none");
    $("#main-page").addClass("d-none");
    $("#accountHTML").empty();
}

function getAccount(customerEmail, customerPass) {
    $("#accountHTML").empty();
    var tempEmail, tempPass;
    $.get("/api/customer/all", function (data) {
        var customer = $(data).find("customer").filter(function () {
            tempEmail = $('email', this).text();
            tempPass = $('password', this).text();
            customerID = $('id', this).text();
            console.log(tempEmail + "-- " + customerEmail);
            console.log(tempPass + "-- " + customerPass);
            if (tempEmail == customerEmail) {
                if (customerPass == tempPass) {
                    $("#loginForm").addClass("d-none");
                    $("#main-page").removeClass("d-none");
                    $("#validation").empty();
                    $("#validation").append("Success!");
                    $("#validation").addClass("text-success");
                    $("#validation").removeClass("text-danger");

                    $.get("/api/customer/accounts?id=" + customerID, function (data) {
                        var account = $(data).find("account").filter(function () {
                            var id = $('id', this).text();
                            var type = $('type', this).text();
                            var sortCode = $('number', this).text();
                            var balance = $('balance', this).text();
                            var cust = $('customerId', this).text();
                            $("#accountHTML").append(
                                    '<tr id=' + id + '>' +
                                    '<th scope="row" id="showAccID">' + id + '</th>' +
                                    '<td id="showAccType">' + type + '</td>' +
                                    '<td id="showAccSortCode">' + sortCode + '</td>' +
                                    '<td id="showAccBalance">' + balance + '</td>' +
                                    '<td><button type="button" " id="' + id + '" onclick="getTransactions(this.id)" class="w-100 btn btn-primary">Show</button></td>' +
                                    '<td><button type="button" id="' + id + '" onclick="deleteAccount(this.id)" class="w-100 btn btn-danger">Delete</button></td>' +
                                    '</tr>'
                                    );
                            console.log("id: " + id + "\ntype:" + type + "\nnumber:" + sortCode + "\nbalance:" + balance + "\nCustomerID" + cust);
                        });
                    });
                }
            } else {
                $("#validation").empty();
                $("#validation").append("Wrong ID or Password !");
                $("#validation").removeClass("text-success");
                $("#validation").addClass("text-danger");
            }
            if (customerEmail == "admin@email.com") {
                $("#main-page").addClass("d-none");
                $("#loginForm").addClass("d-none");
                $("#admin").removeClass("d-none");
            }
        });
    });
}

function getTransactions(accountId) {
    $("#transactionHTML").empty();
    $.get("/api/account/transactions?id=" + accountId, function (data) {
        var transaction = $(data).find("transaction").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var description = $('description', this).text();
            var date = $('date', this).text();
            var amount = $('amount', this).text();
            var toAccount = $('toAccount', this).text();
            var fromAccount = $('fromAccount', this).text();
            $(".transaction-table #transactionHTML").append(
                    '<tr>' +
                    '<th scope="row">' + id + '</th>' +
                    '<td>' + type + '</td>' +
                    '<td>' + description + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td>' + amount + '</td>' +
                    '<td>' + fromAccount + '</td>' +
                    '<td>' + toAccount + '</td>' +
                    '</tr>'
                    );
            console.log("id: " + id + "\ntype:" + type + "\ndesc:" + description + "\namount:" + amount);
        });
    });
}
;
function createAccount() {
            var email = $("#create-email").val();
            var pass = $("#create-pass").val();
            var type = $("#create-type").val();
            $.get("/api/customer/all",function(data){
                var customer = $(data).find("customer").filter(function () {
                    var tempEmail = $('email', this).text();
                    var customerid=$('id',this).text();
                    if(tempEmail == customerEmail){

                        $.post("/api/customer/account?id="+customerid+"&type="+type);
                        getAccount(customerEmail, customerPass);
                    };
                });
            });
}
;

function deleteAccount(accountId) {
    $.post("/api/account/delete?id=" + accountId, function () {
    });
    $("#accountHTML").empty();
    console.log(customerEmail + "- " + customerPass);
    getAccount(customerEmail, customerPass);
}
;


function loadAllCustomers() {
    $("#adminCustomers").empty();
    $.get("/api/customer/all", function (data) {
        var customer = $(data).find("customer").filter(function () {
            var id = $('id', this).text();
            var name = $('name', this).text();
            var address = $('address', this).text();
            var email = $('email', this).text();
            var password = $('password', this).text();

            $("#adminCustomers").append(
                    '<tr id=' + id + '>' +
                    '<th scope="row">' + id + '</th>' +
                    '<th>' + name + '</th>' +
                    '<td>' + address + '</td>' +
                    '<td>' + email + '</td>' +
                    '<td>' + password + '</td>' +
                    '<td><button type="button" " id="' + id + '" onclick="getCustomerAccount(this.id)" class="w-100 btn btn-primary">Show</button></td>' +
                    '<td><button type="button" id="' + id + '" onclick="deleteCustomer(this.id)" class="w-100 btn btn-danger">Delete</button></td>' +
                    '</tr>'
                    );
        });
    });
}
function deleteCustomer(custId) {
    $.post("/api/customer/delete?id=" + custId, function () {
        alert("Customer Deleted");
    });
    $("#adminCustomers").empty();
    loadAllCustomers();
}
function getCustomerAccount(custId) {
    $("#adminAccounts").empty();
    $.get("/api/customer/accounts?id=" + custId, function (data) {
        var account = $(data).find("account").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var sortCode = $('number', this).text();
            var balance = $('balance', this).text();
            var cust = $('customerId', this).text();
            $("#adminAccounts").append(
                    '<tr id=' + id + '>' +
                    '<th scope="row" id="showAccID">' + id + '</th>' +
                    '<td id="showAccType">' + type + '</td>' +
                    '<td id="showAccSortCode">' + sortCode + '</td>' +
                    '<td id="showAccBalance">' + balance + '</td>' +
                    '<td><button type="button" " id="' + id + '" onclick="getAdminTransactions(this.id)" class="w-100 btn btn-primary">Show</button></td>' +
                    '<td><button type="button" id="' + id + '" onclick="deleteAdminAccount(this.id,' + cust + ')" class="w-100 btn btn-danger">Delete</button></td>' +
                    '</tr>'
                    );
        });
    });
}
function deleteAdminAccount(accountId, customerId) {
    $.post("/api/account/delete?id=" + accountId, function () {
    });
    $("#adminAccounts").empty();
    $.get("/api/customer/accounts?id=" + customerId, function (data) {
        var account = $(data).find("account").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var sortCode = $('number', this).text();
            var balance = $('balance', this).text();
            var cust = $('customerId', this).text();
            $("#adminAccounts").append(
                    '<tr id=' + id + '>' +
                    '<th scope="row" id="showAccID">' + id + '</th>' +
                    '<td id="showAccType">' + type + '</td>' +
                    '<td id="showAccSortCode">' + sortCode + '</td>' +
                    '<td id="showAccBalance">' + balance + '</td>' +
                    '<td><button type="button" " id="' + id + '" onclick="getTransactions(this.id)" class="w-100 btn btn-primary">Show</button></td>' +
                    '<td><button type="button" id="' + id + '" onclick="deleteAdminAccount(this.id,' + cust + ')" class="w-100 btn btn-danger">Delete</button></td>' +
                    '</tr>'
                    );
        });
    });
}
function getAdminTransactions(accountId) {
    $("#adminTransactions").empty();
    $.get("/api/account/transactions?id=" + accountId, function (data) {
        var transaction = $(data).find("transaction").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var description = $('description', this).text();
            var date = $('date', this).text();
            var amount = $('amount', this).text();
            var toAccount = $('toAccount', this).text();
            var fromAccount = $('fromAccount', this).text();
            $("#adminTransactions").append(
                    '<tr>' +
                    '<th scope="row">' + id + '</th>' +
                    '<td>' + type + '</td>' +
                    '<td>' + description + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td>' + amount + '</td>' +
                    '<td>' + fromAccount + '</td>' +
                    '<td>' + toAccount + '</td>' +
                    '</tr>'
                    );
            console.log("id: " + id + "\ntype:" + type + "\ndesc:" + description + "\namount:" + amount);
        });
    });
}
;
function loadAllTransactions() {
    $("#adminTransactions").empty();
    $.get("/api/transaction/all", function (data) {
        var transaction = $(data).find("transaction").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var description = $('description', this).text();
            var date = $('date', this).text();
            var amount = $('amount', this).text();
            var toAccount = $('toAccount', this).text();
            var fromAccount = $('fromAccount', this).text();
            $("#adminTransactions").append(
                    '<tr>' +
                    '<th scope="row">' + id + '</th>' +
                    '<td>' + type + '</td>' +
                    '<td>' + description + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td>' + amount + '</td>' +
                    '<td>' + fromAccount + '</td>' +
                    '<td>' + toAccount + '</td>' +
                    '</tr>'
                    );
            console.log("id: " + id + "\ntype:" + type + "\ndesc:" + description + "\namount:" + amount);
        });
    });
}
function loadAllAccounts() {
    $("#adminAccounts").empty();
    $.get("/api/account/all", function (data) {
        var account = $(data).find("account").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var sortCode = $('number', this).text();
            var balance = $('balance', this).text();
            var cust = $('customerId', this).text();
            $("#adminAccounts").append(
                    '<tr id=' + id + '>' +
                    '<th scope="row" id="showAccID">' + id + '</th>' +
                    '<td id="showAccType">' + type + '</td>' +
                    '<td id="showAccSortCode">' + sortCode + '</td>' +
                    '<td id="showAccBalance">' + balance + '</td>' +
                    '<td><button type="button" " id="' + id + '" onclick="loadAccountTransaction(this.id)" class="w-100 btn btn-primary">Show</button></td>' +
                    '<td><button type="button" id="' + id + '" onclick="deleteAdminAccount(this.id,' + cust + ')" class="w-100 btn btn-danger">Delete</button></td>' +
                    '</tr>'
                    );
        });
    });
}
function loadAccountTransaction(accountid) {
    $("#adminTransactions").empty();
    $.get("/api/account/transactions?id=" + accountid, function (data) {
        var transaction = $(data).find("transaction").filter(function () {
            var id = $('id', this).text();
            var type = $('type', this).text();
            var description = $('description', this).text();
            var date = $('date', this).text();
            var amount = $('amount', this).text();
            var toAccount = $('toAccount', this).text();
            var fromAccount = $('fromAccount', this).text();
            $("#adminTransactions").append(
                    '<tr>' +
                    '<th scope="row">' + id + '</th>' +
                    '<td>' + type + '</td>' +
                    '<td>' + description + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td>' + amount + '</td>' +
                    '<td>' + fromAccount + '</td>' +
                    '<td>' + toAccount + '</td>' +
                    '</tr>'
                    );
        });
    });
}


function makeLodgement() {
    var accountId = $("#recipient-account").val();
    var amount = $("#lodgement-amount").val();
    var description = $("#lodgement-description").val();
    $.post("/api/transaction/lodgement?account=" + accountId + "&amount=" + amount + "&description=" + description);
    getAccount(customerEmail, customerPass);
}
function makeWithdrawal() {
    var accountId = $("#withdrawal-account").val();
    var amount = $("#withdrawal-amount").val();
    $.post("/api/transaction/withdrawal?account=" + accountId + "&amount=" + amount);
    getAccount(customerEmail, customerPass);
}
function makeTransfer() {
    var fromAccount = $("#from-account").val();
    var toAccount = $("#to-account").val();
    var amount = $("#transfer-amount").val();
    var description = $("#transfer-description").val();
    $.post("/api/transaction/transfer?from=" + fromAccount + "&to=" + toAccount + "&amount=" + amount + "&desc=" + description);
    getAccount(customerEmail, customerPass);
}