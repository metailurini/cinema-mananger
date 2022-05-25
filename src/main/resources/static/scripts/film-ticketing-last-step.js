var child = document.getElementById("child");
var father = document.getElementById("father");

// for (let i = 0; i < 20; i++) {
//   father.appendChild(child.cloneNode(true));
// }

seats = JSON.parse(getCookie("_arr_seats"));
price = parseInt(getCookie("film-price")) * seats.length;
document.querySelector("#user-id").innerText = getCookie("current-user").slice(
  0,
  5
);
document.querySelector("#film-name").innerText = getCookie("film-name");
document.querySelector("#film-name-detail").innerText = getCookie("film-name");
document.querySelector("#film-name-ticket").innerText = getCookie("film-name");
document.querySelector("#date-ticket").innerText = new Date( parseInt(getCookie("ticket-time"))).toLocaleString();
document.querySelector("#date-ticket-booking").innerText = new Date( parseInt(getCookie("ticket-time"))).toLocaleString();
document.querySelector("#counted-ticket").innerText = seats.length;
document.querySelector("#seats").innerText = seats.join(", ");
document.querySelector("#film-price").innerText = price + " VND";
document.querySelector("#real-price").innerText = price * 0.9 + " VND";
document.querySelector("#transaction-id").innerText = getCookie("transaction-id").slice(0, 6);
document.querySelector("#film-image").src = getCookie("film-image")


for (let i = 0; i < seats.length; i++) {}

function transformData() {
  setCookie('seats', JSON.parse(getCookie('_arr_seats')).join('.'))
}