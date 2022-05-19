const arrSeatsIndex = [
  {
    index: 1,
    word: "A",
  },
  {
    index: 2,
    word: "B",
  },
  {
    index: 3,
    word: "C",
  },
  {
    index: 4,
    word: "D",
  },
  {
    index: 5,
    word: "E",
  },
  {
    index: 6,
    word: "F",
  },
  {
    index: 7,
    word: "G",
  },
  {
    index: 8,
    word: "H",
  },
];
const arrSeatClass = ["seat-available", "seat-unvailable"];

function initSeats() {
  for (let i = 0; i < 7; i++) {
    const seatRowContainer = document.createElement("div");
    const rowTextIndex = arrSeatsIndex[i].word;
    seatRowContainer.className = "seat-row-container";
    seatRowContainer.id = "seatRowContainer";
    for (let j = 0; j < 7; j++) {
      const seat = document.createElement("div");
      const seatText = document.createElement("p");
      seat.id = rowTextIndex + (j + 1);
      seat.addEventListener("click", function () {
        if (seat.className.includes("seat-available")) {
          addSeatChoosed(seat);
        } else if (seat.className.includes("seat-choosing")) {
          deleteSeatChoosed(seat);
        }
      });
      seatText.className = "seat-text";
      seatText.innerText = rowTextIndex + (j + 1);
      seat.appendChild(seatText);
      if (j == 1 || j == 4) {
        seat.className =
          arrSeatClass[Math.floor(Math.random() * arrSeatClass.length)] +
          " seat-margin-right-big";
      } else {
        seat.className =
          arrSeatClass[Math.floor(Math.random() * arrSeatClass.length)] +
          " seat-margin-right-small";
      }
      seatRowContainer.appendChild(seat);
    }
    document.getElementById("seatContainer").appendChild(seatRowContainer);
  }
}

function addSeatChoosed(seatChoosed) {
  var showSeatFather = document.getElementById("father");
  var showSeatChild = document.createElement("div");
  var showSeatGrandChild = document.createElement("div");
  var showSeatGrandChildTop = document.createElement("div");
  var showSeatGrandChildBottom = document.createElement("div");
  var a = document.createElement("a");
  var i = document.createElement("i");
  var seatName = document.createElement("h5");
  var filmName = document.createElement("h6");
  var seatPrice = document.createElement("h5");
  var textNodeSeatName = document.createTextNode(seatChoosed.id);
  var textNodeFilmName = document.createTextNode(
    "GODZILLA: KING OF THE MONSTER"
  );
  var textNodePrice = document.createTextNode("50.000");

  seatChoosed.className = seatChoosed.className.replace(
    "seat-available",
    "seat-choosing"
  );

  showSeatChild.style = "display: flex; justify-content: space-between";
  showSeatGrandChild.style = "display: flex";
  showSeatGrandChildTop.className = "seat-choosing";
  showSeatGrandChildTop.style = "margin-right: 10px; width: 50px; height: 50px";
  a.style = "cursor: pointer";
  i.className = "fa-solid fa-square-xmark";
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
  a.appendChild(i);
  showSeatChild.appendChild(showSeatGrandChild);
  showSeatChild.appendChild(a);
  showSeatFather.appendChild(showSeatChild);
}

function deleteSeatChoosed(seatChoosed) {
  const seatDelete = document.getElementById("temp" + seatChoosed.id);
  seatChoosed.className = seatChoosed.className.replace(
    "seat-choosing",
    "seat-available"
  );
  seatDelete.remove();
}

initSeats();

function logra(varr) {
  console.log(varr);
}
