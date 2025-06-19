// //restrict future time
// window.onload = function () {
//     const today = new Date().toISOString().split("T")[0];
//     document.getElementsByName("date")[0].setAttribute("max", today);
// };

// // time
const dateInput = document.querySelector('input[name="date"]');
const timeInput = document.querySelector('input[name="time"]');

const now = new Date();

// Set max date to today
dateInput.max = now.toISOString().split('T')[0];

// Set initial max time to current time if date is today
function updateMaxTime() {
  if (dateInput.value === now.toISOString().split('T')[0]) {
    timeInput.max = now.toTimeString().slice(0, 5); // 'HH:mm'
  } else {
    timeInput.max = '23:59';
  }
}

// Initial call
updateMaxTime();

// Update max time when date changes
dateInput.addEventListener('change', updateMaxTime);

// Functions for navbar added
document.addEventListener("DOMContentLoaded", () => {
    const searchBtn = document.getElementById("searchBtn");
    const searchInput = document.getElementById("searchInput");
    const notifBtn = document.getElementById("notifBtn");
    const notifBox = document.getElementById("notifBox");
    const settingsBtn = document.getElementById("settingsBtn");
    const settingsBox = document.getElementById("settingsBox");
    const userBtn = document.getElementById("userBtn");
    const userPanel = document.getElementById("userPanel");

    searchBtn.addEventListener("click", () => {
        searchInput.classList.toggle("show");
    });

    notifBtn.addEventListener("click", () => {
        notifBox.classList.toggle("hidden");
        settingsBox.classList.add("hidden");
    });

    settingsBtn.addEventListener("click", () => {
        settingsBox.classList.toggle("hidden");
        notifBox.classList.add("hidden");
    });

    userBtn.addEventListener("click", () => {
        userPanel.classList.toggle("show");
    });

    // Close user panel when clicking outside
    window.addEventListener("click", (e) => {
        if (!userBtn.contains(e.target) && !userPanel.contains(e.target)) {
            userPanel.classList.remove("show");
        }
    });
});
