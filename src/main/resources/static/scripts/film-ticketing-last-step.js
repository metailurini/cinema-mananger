seats = JSON.parse(getCookie("_arr_seats"));
price = parseInt(getCookie("film-price")) * seats.length;

if (getCookie("current-user") != "") {
  document.querySelector("#user-id").innerText = getCookie(
    "current-user"
  ).slice(0, 5);
} else {
  document.querySelector("#user-id").innerText = "Rỗng";
}

document.querySelector("#film-top-title").innerText = getCookie("film-name");
document.querySelector("#day-time-title").innerText = new Date(
  parseInt(getCookie("ticket-time"))
).toLocaleString();

document.querySelector("#film-name-detail").innerText = getCookie("film-name");

document.querySelector("#date-detail").innerText = new Date(
  parseInt(getCookie("ticket-time"))
).toLocaleString();
document.querySelector("#counted-detail").innerText = seats.length;
document.querySelector("#seats-detail").innerText = seats.join(", ");
document.querySelector("#film-price-detail").innerText = price + " VND";
document.querySelector("#real-price-detail").innerText = price * 0.9 + " VND";

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
document.querySelector("#price-ticket").innerText = price / 10 + " VND";
document.querySelector("#total-price-ticket").innerText =
  (price / 10) * 0.9 + " VND";

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
