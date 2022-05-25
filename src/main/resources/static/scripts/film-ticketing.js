const dates = (startDate, num) =>
  Array.from({ length: num }, (_, i) =>
    new Date(startDate.getTime() + i * 60000 * 60 * 24)
      .toISOString()
      .slice(0, 10)
  );

const chooseDay = () => {
  let date = new Date();
  date.setDate(date.getDate());
  let weekdays = dates(date, 7);
  let select = document.getElementById("select-day-father");

  for (let weekday of weekdays) {
    var option = document.createElement("option");
    if (
      weekday.substring(8) ==
      parseInt(date.toDateString().substring(8, 10)) - 1
    ) {
      option.selected = true;
    }
    option.value = weekday;
    option.innerHTML =
      "Ngày " +
      weekday.substring(8) +
      " tháng " +
      weekday.substring(5, 7) +
      " năm " +
      weekday.substring(0, 4);
    select.appendChild(option);
  }
};

const chooseTime = () => {
  let select = document.getElementById("select-time-father");
  let arrayTime = [
    "08:30 AM",
    "10:45 AM",
    "13:00 PM",
    "15:00 PM",
    "17:00 PM",
    "20:00 PM",
  ];
  for (let time of arrayTime) {
    var option = document.createElement("option");
    if (arrayTime.indexOf(time) == "08:30 am") {
      option.selected = true;
    }
    option.value = time;
    option.innerHTML = time;
    select.appendChild(option);
  }
};

chooseDay();
chooseTime();

cals = getCookie('calendar-dates').split('-').map(i => new Date(parseInt(i)));
days = {}
for(let i = 0; i < cals.length; i ++) {
    key = `${cals[i].getDate()}-${cals[i].getMonth()}-${cals[i].getYear()}`
    if(days[key] == undefined){days[key] = []}
    days[key] = days[key].concat(cals[i])
}