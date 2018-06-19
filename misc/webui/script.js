
var field = document.getElementById("tfield");
var listener = function () {
    outputTextFieldValue(this);
};
field.addEventListener('input', listener);
field.removeEventListener('input', listener);
// console.log(par.textContent);

// input.textContent;
// input.getAttribute("id");

//
// var ping = setInterval(function () {
//     console.log(input.value);
// }, 2000);
//
// setTimeout(function () {
//     clearInterval(ping);
// }, 10000);


function outputTextFieldValue(textField) {
    console.log(textField.value);
}

// var person = {
//     name: 'Walter White',
//     sayMyName: function () {
//         return 'Heizenberg';
//     }
// };
//
// var hisName = person.sayMyName();
// console.log(person['name']);
// console.log(hisName);