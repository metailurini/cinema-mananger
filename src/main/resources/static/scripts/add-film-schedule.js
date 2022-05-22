let arrTimes = [
  "07:00 - 08:45",
  "09:00 - 10:45",
  "11:00 - 12:45",
  "13:00 - 14:45",
  "15:00 - 16:45",
  "17:00 - 18:45",
  "19:00 - 20:45",
  "21:00 - 22:45",
];

let arrDays = getDatesInRange("22-05-2022", "31-05-2022");

let arrDayTimeChoosed = [];
setCookie("_arr_day_time", JSON.stringify(arrDayTimeChoosed), 1);

function getDatesInRange(startDate, endDate) {
  startDate =
    startDate.substring(6) +
    "-" +
    startDate.substring(3, 5) +
    "-" +
    startDate.substring(0, 2);

  endDate =
    endDate.substring(6) +
    "-" +
    endDate.substring(3, 5) +
    "-" +
    endDate.substring(0, 2);

  const d1 = new Date(startDate);
  const d2 = new Date(endDate);

  const date = new Date(d1.getTime());
  const dates = [];
  date.setDate(date.getDate() + 1);
  while (date <= d2) {
    const tmpDate = new Date(date).toISOString().slice(0, 10);
    dates.push(
      tmpDate.substring(8) +
        "-" +
        tmpDate.substring(5, 7) +
        "-" +
        tmpDate.substring(0, 4)
    );
    date.setDate(date.getDate() + 1);
  }
  return dates;
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
      childDayTime.id = arrDay + arrTime;
      childDayTime.className = "choose-time-unactive";
      childDayTime.innerText = arrTime;

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
  arrDayTimeChoosed.push({
    day: childDayTime.id.substring(0, 10).split(" ").join(""),
    time: childDayTime.id.substring(10).split(" ").join(""),
  });
  setCookie("_arr_day_time", JSON.stringify(arrDayTimeChoosed), 1);
}

function deleteTimeChoosed(childDayTime) {
  childDayTime.className = "choose-time-unactive";
  var index = arrDayTimeChoosed.findIndex((object) => {
    return object.day + object.time == childDayTime.id;
  });
  if (index > -1) {
    arrDayTimeChoosed.splice(index, 1);
    setCookie("_arr_day_time", JSON.stringify(arrDayTimeChoosed), 1);
  }
}

initDayTimeChoose();
