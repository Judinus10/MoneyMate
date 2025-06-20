// // //restrict future time
// // window.onload = function () {
// //     const today = new Date().toISOString().split("T")[0];
// //     document.getElementsByName("date")[0].setAttribute("max", today);
// // };
// console.log("✅ script.js is running");

// // // time
// const dateInput = document.querySelector('input[name="date"]');
// const timeInput = document.querySelector('input[name="time"]');

// const now = new Date();

// // Set max date to today
// if (dateInput) {
//     dateInput.max = now.toISOString().split('T')[0];

//     function updateMaxTime() {
//         if (dateInput.value === now.toISOString().split('T')[0]) {
//             timeInput.max = now.toTimeString().slice(0, 5);
//         } else {
//             timeInput.max = '23:59';
//         }
//     }

//     updateMaxTime();
//     dateInput.addEventListener('change', updateMaxTime);
// }


// // Set initial max time to current time if date is today
// function updateMaxTime() {
//   if (dateInput.value === now.toISOString().split('T')[0]) {
//     timeInput.max = now.toTimeString().slice(0, 5); // 'HH:mm'
//   } else {
//     timeInput.max = '23:59';
//   }
// }

// // Initial call
// updateMaxTime();

// // Update max time when date changes
// dateInput.addEventListener('change', updateMaxTime);

// Functions for navbar added
document.addEventListener("DOMContentLoaded", () => {
    console.log("✅ DOM loaded");

    const searchBtn = document.getElementById("searchBtn");
    const searchInput = document.getElementById("searchInput");
    const notifBtn = document.getElementById("notifBtn");
    const notifBox = document.getElementById("notifBox");
    const settingsBtn = document.getElementById("settingsBtn");
    const settingsBox = document.getElementById("settingsBox");
    const userBtn = document.getElementById("userBtn");
    const userPanel = document.getElementById("userPanel");

    if (searchBtn && searchInput) {
        searchBtn.addEventListener("click", () => {
            searchInput.classList.toggle("show");
            searchInput.classList.toggle("hidden");
        });
    }

    if (notifBtn && notifBox) {
        notifBtn.addEventListener("click", () => {
            notifBox.classList.toggle("hidden");
            settingsBox?.classList.add("hidden");
        });
    }

    if (settingsBtn && settingsBox) {
        settingsBtn.addEventListener("click", () => {
            settingsBox.classList.toggle("hidden");
            notifBox?.classList.add("hidden");
        });
    }

    if (userBtn && userPanel) {
        userBtn.addEventListener("click", () => {
            userPanel.classList.toggle("show");
        });

        window.addEventListener("click", (e) => {
            if (!userBtn.contains(e.target) && !userPanel.contains(e.target)) {
                userPanel.classList.remove("show");
            }
            if (!settingsBtn.contains(e.target) && !settingsBox.contains(e.target)) {
                settingsBox?.classList.add("hidden");
            }
            if (!notifBtn.contains(e.target) && !notifBox.contains(e.target)) {
                notifBox?.classList.add("hidden");
            }
        });
    }
});

document.addEventListener("keydown", function (e) {
    if (e.key === "Escape") {
        notifBox.classList.remove("show");
        settingsBox.classList.remove("show");
        userPanel.classList.remove("show");
    }
});
