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
  var speed = 10;
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

const toastTrigger = document.getElementById("liveToastBtn");
const toastLiveExample = document.getElementById("liveToast");
if (toastTrigger) {
  toastTrigger.addEventListener("click", () => {
    const toast = new bootstrap.Toast(toastLiveExample);

    toast.show();
  });
}

incEltNbr("nbr");

document.querySelector("#statusSelection").value = getCookie('status')
document.querySelector("#searchID").value = getCookie('phone_or_name')