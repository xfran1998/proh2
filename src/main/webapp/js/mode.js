let mode_btn = document.querySelector("#mode-btn");
let body = document.querySelector("body");
let nav = document.querySelector("#nav");

console.log(mode_btn);

mode_btn.addEventListener("click", () => {
  console.log("click");

  nav.classList.add("no-transition-btn");
  mode_btn.classList.toggle("rigth");
  body.classList.toggle("dark");

  setTimeout(() => {
    nav.classList.remove("no-transition-btn");
  }, 50);
});
