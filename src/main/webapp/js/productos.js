/**
 * 
 */
 localStorage.clear();
 //localStorage.removeItem('carrito');
 var carrito_btns = document.querySelectorAll('.add-carrito');
 var user_carrito = JSON.parse(localStorage.getItem('carrito'));


console.log(user_carrito);
 if (!user_carrito) user_carrito = {};
 
 carrito_btns.forEach( btn => {
	btn.addEventListener('click', e => {
		 
		 
		let btn_clicked = e.currentTarget;
		let id = btn_clicked.getAttribute('data-id');
	
		
		if (user_carrito[id] == null) {
			user_carrito[id] = {};
			user_carrito[id].nombre = btn_clicked.getAttribute('data-nombre');	
			user_carrito[id].precio = btn_clicked.getAttribute('data-precio');	
			user_carrito[id].existencias = btn_clicked.getAttribute('data-existencias');	
			user_carrito[id].img = btn_clicked.getAttribute('data-img');	
			user_carrito[id].cant = 0;
			console.log('new');
		}
		
		// sumar 1 al carrito cada vez que pulsemos al add btn, siempre y cuando no sobrepase el maximo
		if (user_carrito[id].cant < user_carrito[id].existencias)
			user_carrito[id].cant++;
			
		localStorage.setItem('carrito', JSON.stringify(user_carrito));
		
		console.log('addedItem');
		console.log(JSON.parse(localStorage.getItem('carrito')));
	})
});

