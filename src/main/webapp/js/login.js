/*
		Designed by: SELECTO
		Original image: https://dribbble.com/shots/5311359-Diprella-Login
*/

var switchCtn = document.querySelector("#switch-cnt");
var switchC1 = document.querySelector("#switch-c1");
var switchC2 = document.querySelector("#switch-c2");
var switchCircle = document.querySelectorAll(".switch__circle");
var switchBtn = document.querySelectorAll(".switch-btn");
var aContainer = document.querySelector("#a-container");
var bContainer = document.querySelector("#b-container");
var allButtons = document.querySelectorAll(".submit");

var getButtons = (e) => e.preventDefault();

var changeForm = (e) => {
	switchCtn.classList.add("is-gx");
	setTimeout(function () {
		switchCtn.classList.remove("is-gx");
	}, 1500);

	switchCtn.classList.toggle("is-txr");
	switchCircle[0].classList.toggle("is-txr");
	switchCircle[1].classList.toggle("is-txr");

	switchC1.classList.toggle("is-hidden");
	switchC2.classList.toggle("is-hidden");
	aContainer.classList.toggle("is-txl");
	bContainer.classList.toggle("is-txl");
	bContainer.classList.toggle("is-z200");
};

for (var i = 0; i < allButtons.length; i++) {
	allButtons[i].addEventListener("click", getButtons);
	// console.log(allButtons[i]);
}

for (var i = 0; i < switchBtn.length; i++) {
	switchBtn[i].addEventListener("click", changeForm);
	// console.log("click: ", switchBtn[i]);
}