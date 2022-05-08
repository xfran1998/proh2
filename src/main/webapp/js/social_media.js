let media_btn = document.querySelectorAll(".media-btn");
let tbody = document.querySelector("#tbody");

let transition_time = 0.2;

// change media_btn style
media_btn.forEach((btn) => {
	btn.style.transition = `all ${transition_time}s ease-in-out`;
});

media_btn.forEach((btn) => {
	btn.addEventListener("click", () => {
		// remove_checked(btn);
		// btn.classList.toggle("btn-checked");
		let page = btn.getAttribute("page");
		add_animation(btn);

		// check if tbody have the same page
		let tbody_page = tbody.getAttribute("page");
		if (tbody_page !== page) {
			get_page_async(page);
		}
	});
});

const remove_checked = (clicked_btn) => {
	media_btn.forEach((btn) => {
		if (btn !== clicked_btn) {
			btn.classList.remove("btn-checked");
		}
	});
};

const add_animation = (clicked_btn) => {
	// add class .animated-click to clicked button and 1 seg later remove it
	clicked_btn.classList.remove("neuromorphic");
	clicked_btn.classList.add("anime1");
	setTimeout(() => {
		clicked_btn.classList.add("anime2");
	}, transition_time * 1000);

	setTimeout(() => {
		clicked_btn.classList.remove("anime1");
		clicked_btn.classList.remove("anime2");
		clicked_btn.classList.add("neuromorphic");
	}, transition_time * 1000);
};

const replace_animation = (body) => {
	body.classList.remove("replace-in");
	body.classList.add("replace-out");
	setTimeout(() => {
		body.classList.remove("replace-out");
		body.classList.add("replace-in");
	}, 500);
};

async function get_page_async(page, method='GET', form_body=null, body=tbody, API=false) {
	// fetch GET page with the given page name as source/page.html
	
	try {
		if (body) replace_animation(body);
		// let page_url = (page=='')
		let response_body = {};
		console.log(JSON.stringify(form_body));
		 
		if (method == 'GET')
			response_body = {
				method: method,
			}
		if (method == 'POST' || method == 'PUT'){
			response_body = {
				headers: {
			    	'Content-Type': 'application/json'
			    },
				method: method,
				body: JSON.stringify(form_body)
			}
		}
		
		
		response = await fetch(`${page}.html`, response_body);
		let data = await response.text();
		
		if (API) return data; // return data if API is true, not update the page

		setTimeout(() => {
			body.innerHTML = data;
			body.setAttribute("page", page);
			console.log("body changed");
			call_scripts(body); // llama a los scripts que no se llaman desde fecth
		}, 500);
	} catch (err) {
		console.log("error get_page_async");
		console.log(err);
	}
}

function call_scripts(body) {
	console.log("call_scripts");
	body.querySelectorAll("script").forEach((script) => {
		let script_src = script.getAttribute("src");
		// check if script_src is already loaded
		console.log("adding scripts");
		if (script_src) {
			let node_script = document.createElement("script");
			node_script.src = script_src;
			// script.parentNode.removeChild(script);

			tbody.appendChild(node_script);
			console.log("script added");
		}
		else{
			console.log("script added");
			eval(script.innerHTML);
		}
	});
}

