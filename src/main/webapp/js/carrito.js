/**
 * 
 */
 
 console.log('carrito');
 console.log(localStorage.getItem('carrito'));
 var productos = JSON.parse(localStorage.getItem('carrito'));
 var tbody_carrito = document.querySelector('tbody');
 
 console.log(productos);
 
 var listenBtn = (btns) => {
	btns.forEach(btn => {
			btn.addEventListener('click', e => {		
				let id = e.currentTarget.getAttribute('data-id');
				let type = e.currentTarget.getAttribute('data-type');
				let row = tbody_carrito.querySelector(`#prod-${id}`);
				let input = row.querySelector('input');
				
				if (type == 'add' && productos[id].cant < productos[id].existencias){ // Add
					productos[id].cant++;
					input.value = productos[id].cant;
					row.querySelector('.precio-producto').innerHTML = `${ productos[id].cant * productos[id].precio }€`;
				}
				if (type == 'sub'){ // subs
					productos[id].cant--;
					input.value = productos[id].cant;
					row.querySelector('.precio-producto').innerHTML = `${ productos[id].cant * productos[id].precio }€`;
					
					if (productos[id].cant == 0){
						delete productos[id];
						row.remove();
					}
				}
				if (type == 'remove'){ // Remove
					delete productos[id];
					row.remove();
				}
					
					
				localStorage.setItem('carrito', JSON.stringify(productos));				
			});
		});
	};

var listenInput = (input) => {
	input.addEventListener('change', e => {
		let id = e.currentTarget.parentNode.parentNode.id.split('-')[1];
		let input_value = parseInt(e.currentTarget.value);
		let max_cant = parseInt(productos[id].existencias);
		
		console.log('value:');
		console.log(input_value);
		if (!input.value || input.value < 0){
			e.currentTarget.value = productos[id].cant;
			console.log('entra');
			return;
		}
		
		if (e.currentTarget.value > 0 ){			
			productos[id].cant = input_value;
			e.currentTarget.parentNode.parentNode.querySelector('.precio-producto').innerHTML = `${ productos[id].cant * productos[id].precio }€`;
			
			if (e.currentTarget.value > max_cant){
			 	productos[id].cant = max_cant;
				e.currentTarget.value = max_cant;
				e.currentTarget.parentNode.parentNode.querySelector('.precio-producto').innerHTML = `${ productos[id].cant * productos[id].precio }€`;
			}
		}
		else{
			delete productos[id];
			tbody_carrito.querySelector(`#prod-${id}`).remove();
		}
		
		console.log('id input:', productos[id]);
		localStorage.setItem('carrito', JSON.stringify(productos));
	});
}
 
 var mostrarProd = (prods) => {
	console.log('mostrar');
	for (let id in prods ){
		console.log(id);
		let row_prod = templateProd(prods[id], id);
		listenBtn(row_prod.querySelectorAll('.carrito-option-btn'));
		listenInput(row_prod.querySelector('input'));
		tbody_carrito.appendChild(row_prod);
	}
};

var templateProd = (prod, id) => {
	let row_prod = document.createElement('tr');
	innerText = `
                <td>
                    <div class="table-img-wrapper">
                        <img src="img/${prod.img}" alt="producto">        
                    </div>
                </td>
                <td>${prod.nombre}</td>
                <td><input type="number" name="cantidad" id="cantidad" class="form__input" value="${prod.cant}"></td>
                <td>${prod.precio}€</td>
                <td class='precio-producto'>${prod.precio * prod.cant}€</td>
                <td>
                    <div class="carrito-btn-container">
                        <div data-id="${id}" data-type="add" class="add-1 carrito-option-btn"></div>
                        <div data-id="${id}" data-type="sub" class="sub-1 carrito-option-btn"></div>
                        <div data-id="${id}" data-type="remove" class="remove carrito-option-btn"></div>
                    </div>
                </td>
                `;
   	row_prod.innerHTML = innerText;
   	row_prod.id = `prod-${id}`
    return row_prod;
};

 mostrarProd(productos);
 