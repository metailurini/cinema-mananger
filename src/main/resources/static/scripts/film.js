var child = document.getElementById("child");
var father = document.getElementById("father");

// for (let i = 0; i < 20; i++) {
//   father.appendChild(child.cloneNode(true));
// }

function more() {
  for (let i = 0; i < 20; i++) {
    father.appendChild(child.cloneNode(true));
  }
}

function incEltNbr(id) {
  elt = document.getElementById(id);
  endNbr = Number(document.getElementById(id).innerHTML);
  incNbrRec(0, endNbr, elt);
}

function incNbrRec(i, endNbr, elt) {
  var speed = 30;
  if (i <= endNbr) {
    elt.innerHTML = i;
    setTimeout(function () {
      incNbrRec(i + 1, endNbr, elt);
    }, speed);
  }
}

function openCloseDropdownUser() {
  var x = document.getElementById("openCloseDropdownUser");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function deleteAuthTokenCookie() {
  document.cookie = "_auth_token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;'";
  location.reload();
}

incEltNbr("nbr");
