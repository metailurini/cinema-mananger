let arrSeatsIndex = [
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

function initSeats() {
  for (let i = 0; i < 7; i++) {
    const seatRowContainer = document.createElement("div");
    const rowTextIndex = arrSeatsIndex[i].word;
    seatRowContainer.className = "seat-row-container";
    seatRowContainer.id = "seatRowContainer";
    for (let j = 0; j < 7; j++) {
      const seat = document.createElement("div");
      const seatText = document.createElement("p");
      seatText.className = "seat-text";
      seatText.innerText = rowTextIndex + (j + 1);
      seat.appendChild(seatText);
      if (j == 1 || j == 4) {
        seat.className = "seat-available seat-margin-right-big";
      } else {
        seat.className = "seat-available seat-margin-right-small";
      }
      seatRowContainer.appendChild(seat);
    }
    document.getElementById("seatContainer").appendChild(seatRowContainer);
  }
}

var child = document.getElementById("child");
var father = document.getElementById("father");

for (let i = 0; i < 20; i++) {
  father.appendChild(child.cloneNode(true));
}

initSeats();
