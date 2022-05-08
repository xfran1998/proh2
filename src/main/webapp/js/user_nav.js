var nav_user = document.querySelectorAll("#user-nav li");
var ubody = document.querySelector('#ubody');
console.log('nav_user');
console.log(ubody);
console.log(document.body);

nav_user.forEach( (n) => {
  n.addEventListener('click', (e) => {
    var nav_clicked = e.currentTarget;
    var page = nav_clicked.getAttribute('data-page');
    console.log('nav: ', page);
    
    nav_user.forEach( (n) => {
      n.classList.remove('active');
    });
    nav_clicked.classList.add('active');
    
    ( async () => {
      get_page_async(`${page}`, 'GET', null, ubody);
    })();
  });
});

// first page load
( async () => {
  get_page_async('./perfil', 'GET', null, ubody);
})();
