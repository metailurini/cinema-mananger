const toastTrigger = document.getElementById("liveToastBtn");
const toastLiveExample = document.getElementById("liveToast");
if (toastTrigger) {
  toastTrigger.addEventListener("click", () => {
    const toast = new bootstrap.Toast(toastLiveExample);

    toast.show();
  });
}

if (decodeURI(getCookie('error')) != "") {
  $("#toastContext")[0].innerHTML = decodeURI(getCookie('error')).replaceAll('+', ' ');
  toastTrigger.click();
  setCookie('error', '', 0.1);
}