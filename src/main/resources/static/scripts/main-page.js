function openCloseDropdownUser() {
  var x = document.getElementById("openCloseDropdownUser");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function deleteCookie() {
  document.cookie = "_auth_token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;'";
  location.reload();
}
