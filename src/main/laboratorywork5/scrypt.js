var operator, num1, num2, result;
document.getElementById('calculate').onclick = checkOperation;

function checkOperation() {
    var arrayOfRadioValues = document.getElementsByName('operation');
    for (var i = 0; i < arrayOfRadioValues.length; i++) {
        if (arrayOfRadioValues[i].checked) {
            operator = arrayOfRadioValues[i].value
            break;
        }
    }

    num1 = document.getElementById('number1').value;
    num1 = parseInt(num1);
    num2 = document.getElementById('number2').value;
    num2 = parseInt(num2);

    if (operator === '+') {
        result = num1 + num2;
    } else if (operator === '-') {
        result = num1 - num2;
    } else if (operator === '*') {
        result = num1 * num2;
    } else if (operator === '/') {
        result = num1 / num2;
    }

    new_page();
}

function new_page() {
    localStorage.setItem("number_one", num1);
    localStorage.setItem("number_two", num2);
    localStorage.setItem("number_result", result);

    localStorage.setItem("flag", true);
    window.location = "http://localhost:63342/PartyninWeb/src/main/laboratorywork5/result.html";
}


function f() {
    if (localStorage.getItem("flag")) {
        document.getElementById("number_one").innerHTML = localStorage.getItem("number_one");
        document.getElementById("number_two").innerHTML = localStorage.getItem("number_two");
        document.getElementById("number_result").innerHTML = localStorage.getItem("number_result");
    } else {
        document.getElementsByClassName("result_page").item(0).innerHTML += "Error!";

    }
}


