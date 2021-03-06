const arrSeatsIndex = ["A", "B", "C", "D", "E", "F", "G", "H"];
let arrTempSeats = JSON.parse(getCookie("_arr_seats"));
let unvailableSeats = getCookie("_arr_unvailable_seats").split(".");

console.log(arrTempSeats);
console.log(unvailableSeats);

if (getCookie("_arr_seats") == null) {
  setCookie("_arr_seats", JSON.stringify([]), 1);
}

function setCookie(name, value, days) {
  var expires = "";
  if (days) {
    var date = new Date();
    date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
    expires = "; expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
function getCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(";");
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == " ") c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
  }
  return null;
}

function initSeats() {
  // const arrSeatClass = ["seat-available", "seat-unvailable", "seat-choosing"];
  for (let i = 0; i < 7; i++) {
    const seatRowContainer = document.createElement("div");
    const rowTextIndex = arrSeatsIndex[i];
    seatRowContainer.className = "seat-row-container";
    seatRowContainer.id = "seatRowContainer";
    for (let j = 0; j < 7; j++) {
      const seat = document.createElement("div");
      const seatText = document.createElement("p");
      seat.id = rowTextIndex + (j + 1);
      seat.addEventListener("click", function () {
        if (seat.className.includes("seat-available")) {
          addSeatChoosed(seat, false);
        } else if (seat.className.includes("seat-choosing")) {
          deleteSeatChoosed(seat);
        }
      });
      seatText.className = "seat-text";
      seatText.innerText = rowTextIndex + (j + 1);
      checkSeatClass(seat, j);
      seat.appendChild(seatText);
      seatRowContainer.appendChild(seat);
    }
    document.getElementById("seatContainer").appendChild(seatRowContainer);
  }
}

function checkSeatClass(seat, j) {
  if (j == 1 || j == 4) {
    if (arrTempSeats.includes(seat.id) == true) {
      seat.className = "seat-choosing seat-margin-right-big";
      addSeatChoosed(seat, true);
    } else if (unvailableSeats.includes(seat.id) == true) {
      seat.className = "seat-unvailable seat-margin-right-big";
    } else {
      seat.setAttribute("class", "seat-available seat-margin-right-big");
    }
  } else {
    if (arrTempSeats.includes(seat.id) == true) {
      seat.className = "seat-choosing seat-margin-right-small";
      addSeatChoosed(seat, true);
    } else if (unvailableSeats.includes(seat.id) == true) {
      seat.className = "seat-unvailable seat-margin-right-small";
    } else {
      seat.setAttribute("class", "seat-available seat-margin-right-small");
    }
  }
}

function addSeatChoosed(seatChoosed, isGenerateFromCookie) {
  var showSeatFather = document.getElementById("father");
  var showSeatChild = document.createElement("div");
  var showSeatGrandChild = document.createElement("div");
  var showSeatGrandChildTop = document.createElement("div");
  var showSeatGrandChildBottom = document.createElement("div");
  var seatName = document.createElement("h5");
  var filmName = document.createElement("h6");
  var seatPrice = document.createElement("h5");
  var textNodeSeatName = document.createTextNode(seatChoosed.id);
  var textNodeFilmName = document.createTextNode(
    "GODZILLA: KING OF THE MONSTER"
  );
  var textNodePrice = document.createTextNode("50.000");

  if (isGenerateFromCookie == false) {
    arrTempSeats.push(seatChoosed.id);
    setCookie("_arr_seats", JSON.stringify(arrTempSeats), 1);

    seatChoosed.className = seatChoosed.className.replace(
      "seat-available",
      "seat-choosing"
    );
  }

  showSeatChild.style = "display: flex; justify-content: space-between";
  showSeatChild.setAttribute("data-aos", "fade-right");
  showSeatGrandChild.style = "display: flex";
  showSeatGrandChildTop.className = "seat-choosing";
  showSeatGrandChildTop.style = "margin-right: 10px; width: 50px; height: 50px";
  seatName.style = "margin-top: 12px";
  seatPrice.style = "color: #fbca10";
  showSeatChild.id = "temp" + seatChoosed.id;

  seatName.appendChild(textNodeSeatName);
  filmName.appendChild(textNodeFilmName);
  seatPrice.appendChild(textNodePrice);
  showSeatGrandChildTop.appendChild(seatName);
  showSeatGrandChildBottom.appendChild(filmName);
  showSeatGrandChildBottom.appendChild(seatPrice);
  showSeatGrandChild.appendChild(showSeatGrandChildTop);
  showSeatGrandChild.appendChild(showSeatGrandChildBottom);
  showSeatChild.appendChild(showSeatGrandChild);
  showSeatFather.appendChild(showSeatChild);
}

function deleteSeatChoosed(seatChoosed) {
  const showSeatFather = document.getElementById("temp" + seatChoosed.id);
  var index = arrTempSeats.indexOf(seatChoosed.id);
  if (index > -1) {
    arrTempSeats.splice(index, 1);
    setCookie("_arr_seats", JSON.stringify(arrTempSeats), 1);
  }
  seatChoosed.className = seatChoosed.className.replace(
    "seat-choosing",
    "seat-available"
  );
  showSeatFather.remove();
}

initSeats();

function searchUser() {
  search = document.querySelector("#search-id").value;
  fetch(`http://localhost:8080/api/search-customer?phone_or_name=${search}`, {
    headers: {
      accept:
        "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
      "accept-language":
        "en-US,en;q=0.9,vi-VN;q=0.8,vi;q=0.7,fr-FR;q=0.6,fr;q=0.5",
      "cache-control": "max-age=0",
      "sec-ch-ua":
        '" Not A;Brand";v="99", "Chromium";v="101", "Google Chrome";v="101"',
      "sec-ch-ua-mobile": "?0",
      "sec-ch-ua-platform": '"Windows"',
      "sec-fetch-dest": "document",
      "sec-fetch-mode": "navigate",
      "sec-fetch-site": "none",
      "sec-fetch-user": "?1",
      "upgrade-insecure-requests": "1",
    },
    referrerPolicy: "strict-origin-when-cross-origin",
    body: null,
    method: "GET",
    mode: "cors",
    credentials: "include",
  }).then((data) => {
    data.text().then(text => {
      dat = JSON.parse(text)
      document.querySelector("#customer-phone").innerText = dat.phone_number || "...";
      document.querySelector("#customer-name").innerText = dat.full_name || "...";
      setCookie("current-user", dat.user_id || "", 12);
    })
  });
}

// stop enter to reload page
$('#search-id').keypress(function (e) {                                       
       if (e.which == 13) {
            e.preventDefault();
            searchUser();
       }
});

document.querySelector("#film-image").src = getCookie('film-image')
document.querySelector("#film-name").innerText = decodeURI(getCookie('film-name'))
document.querySelector("#ticket-time").innerText = new Date(parseInt(getCookie('ticket-time')))