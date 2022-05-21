let arrTimes = [
  {
    id: 1,
    time: "00:00 - 01:30",
  },
  {
    id: 2,
    time: "01:45 - 03:15",
  },
  {
    id: 3,
    time: "03:30 - 05:00",
  },
  {
    id: 4,
    time: "05:15 - 06:45",
  },
  {
    id: 5,
    time: "07:00 - 08:30",
  },
];

let arrDays = ["21-05-2022", "22-05-2022", "23-05-2022"];

// var child = document.getElementById("child");
// var father = document.getElementById("father");

// for (let i = 0; i < 20; i++) {
//   father.appendChild(child.cloneNode(true));
// }

function initDayTimeChoose() {
  for (let arrDay of arrDays) {
    const bottomContainer = document.getElementById("bottomDayTime");
    const day = arrDay;
    const chooseTimeCover = document.createElement("div");
    const dayTitle = document.createElement("h2");
    const fatherDayTime = document.createElement("div");

    chooseTimeCover.className = "choose-time-cover";
    dayTitle.innerText = day;
    fatherDayTime.style.marginTop = "20px";
    fatherDayTime.className = "row";

    for (let arrTime of arrTimes) {
      const childDayTime = document.createElement("h5");
      childDayTime.id =
        arrDay + "-" + arrTime.time.replace(/ /g, "").replaceAll(":", "");
      childDayTime.className = "choose-time-unactive";
      childDayTime.innerText = arrTime.time;

      childDayTime.addEventListener("click", function () {
        if (childDayTime.className.includes("choose-time-unactive")) {
          addTimeChoosed(childDayTime);
        } else if (childDayTime.className.includes("choose-time-active")) {
          deleteTimeChoosed(childDayTime);
        }
      });

      fatherDayTime.appendChild(childDayTime);
    }
    chooseTimeCover.appendChild(dayTitle);
    chooseTimeCover.appendChild(fatherDayTime);
    bottomContainer.appendChild(chooseTimeCover);
  }
}

function addTimeChoosed(childDayTime) {
  childDayTime.className = "choose-time-active";
}

function deleteTimeChoosed(childDayTime) {
  childDayTime.className = "choose-time-unactive";
}

initDayTimeChoose();
