async function start_page() {
	// fetch GET page with the given page name as source/page.html
	let first_page = document.querySelector("#nav li").getAttribute("page");

	try {
		const response = await fetch(`./${first_page}.html`);
		const data = await response.text();
		// console.log(data);
		// console.log(tbody);

		// let tbody = document.querySelector('tbody');
		tbody.innerHTML = data;

		// call_prism();
	} catch (err) {
		console.log("error get_page_async");
		console.log(err);
	}
}
// // select the target node
// var target = document.querySelector('#tbody');

// // create an observer instance
// var observer = new MutationObserver(function(mutations) {
// 	mutations.forEach(function(mutation) {
// 		if (mutation.type === 'childList') {
// 			console.log('Updated!');
// 			// Maybe here call prism.js
// 		}
// 	});
// });

// // configuration of the observer:
// var config = { attributes: true, childList: true, characterData: true }

// // pass in the target node, as well as the observer options
// observer.observe(target, config);

start_page();
