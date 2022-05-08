var login_btn = document.querySelector('#b-form .submit');
var register_btn = document.querySelector('#a-form .submit')
console.log(login_btn);

login_btn.addEventListener('click', (e) => {
	e.preventDefault();
	
	var body_form = getDataForm(e.currentTarget.parentNode);
	
	(async => {
		get_page_async('login', 'POST', body_form);
	})();
	
});


console.log(register_btn);
register_btn.addEventListener('click', e => {
	e.preventDefault();
	console.log("submit: ", e);

	var name = document.querySelector("#a-user").value;
	var email = document.querySelector("#a-email").value;
	var password = document.querySelector("#a-password").value;
	var confirmPassword = document.querySelector("#a-confirm-password").value;

	// Regex check: https://regex101.com/
	// Valid: [a-z], [A-Z], [0-9] from 3 to 32 characters
	var validName = /^[a-zA-Z1-9_]{3,32}$/;
	
	if (!validName.test(name)) {
		alert("Invalid name, [a-z], [A-Z], [0-9] from 3 to 32 characters");
		return;
	}

	if (password != confirmPassword) {
		alert("Passwords do not match");
		return;
	}

	var body_form = getDataForm(e.currentTarget.parentNode);
	
	(async => {
		get_page_async('register', 'POST', body_form);
	})();
});

function getDataForm(form) {
	//register_btn.submit();
	var body_form = {};
	
	form.querySelectorAll('input').forEach( el => {
		body_form[el.name] = el.value;
	});

	return body_form;
}