function incEltNbr(id, isCustom) {
  elt = document.getElementById(id);
  endNbr = Number(document.getElementById(id).innerHTML);
  incNbrRec(0, endNbr, elt, isCustom);
}

function incNbrRec(i, endNbr, elt, isCustom) {
  var speed = 10;
  if (i <= endNbr) {
    if (isCustom == false) {
      elt.innerHTML = i;
    } else {
      elt.innerHTML = numberWithCommas(i);
    }
    setTimeout(function () {
      if (isCustom == false) {
        incNbrRec(i + 1, endNbr, elt, false);
      } else {
        incNbrRec(i + endNbr * 0.01, endNbr, elt, true);
      }
    }, speed);
  }
}

function numberWithCommas(x) {
  var parts = x.toString().split(".");
  parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ".");
  return parts
}

var child = document.getElementById("child");
var father = document.getElementById("father");

for (let i = 0; i < 10; i++) {
  father.appendChild(child.cloneNode(true));
}

incEltNbr("nbr", false);
incEltNbr("nbrRevenueToday", true);
incEltNbr("nbrRevenueThisMonth", true);
incEltNbr("nbrRevenueThisYear", true);
