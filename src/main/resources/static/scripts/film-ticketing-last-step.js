var child = document.getElementById("child");
var father = document.getElementById("father");

// for (let i = 0; i < 20; i++) {
//   father.appendChild(child.cloneNode(true));
// }

seats = JSON.parse(getCookie("_arr_seats"));
price = parseInt(getCookie("film-price")) * seats.length;
if (getCookie("current-user") != "") {
  document.querySelector("#user-id").innerText = getCookie(
    "current-user"
  ).slice(0, 5);
} else {
  document.querySelector("#user-id").innerText = "Rỗng";
}
document.querySelector("#film-name").innerText = getCookie("film-name");
document.querySelector("#film-name-detail").innerText = getCookie("film-name");
document.querySelector("#film-name-ticket").innerText = getCookie("film-name");
document.querySelector("#date-ticket").innerText = new Date(
  parseInt(getCookie("ticket-time"))
).toLocaleString();
document.querySelector("#date-ticket-booking").innerText = new Date(
  parseInt(getCookie("ticket-time"))
).toLocaleString();
document.querySelector("#counted-ticket").innerText = seats.length;
document.querySelector("#seats").innerText = seats.join(", ");
document.querySelector("#film-price").innerText =
  numberWithCommas(price) + " VND";
document.querySelector("#real-price").innerText =
  numberWithCommas(price * 0.9) + " VND";
document.querySelector("#transaction-id").innerText = getCookie(
  "transaction-id"
).slice(0, 6);
document.querySelector("#film-image").src = getCookie("film-image");

document.querySelector("#film-name-ticket").innerText = getCookie("film-name");
document.querySelector("#day-ticket").innerText = new Date(
  parseInt(getCookie("ticket-time"))
)
  .toLocaleString()
  .substring(0, 9);

document.querySelector("#time-ticket").innerText = new Date(
  parseInt(getCookie("ticket-time"))
)
  .toLocaleString()
  .substring(10);
document.querySelector("#price-ticket").innerText =
  numberWithCommas(price / 10) + " VND";
document.querySelector("#total-price-ticket").innerText =
  numberWithCommas((price / 10) * 0.9) + " VND";

for (let i = 0; i < seats.length; i++) {
  var father = document.getElementById("father");
  var child = document.getElementById("child");
  var seatCover = document.getElementById("seat-ticket-cover");
  const seat = document.createElement("p");

  seat.className = "seat-ticket";
  seat.innerHTML = seats[i];
  console.log(seat);
  seatCover.appendChild(seat);
  father.appendChild(child);
}

function transformData() {
  setCookie("seats", JSON.parse(getCookie("_arr_seats")).join("."));
}

function numberWithCommas(x) {
  var parts = x.toString().split(".");
  parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ".");
  return parts;
}
