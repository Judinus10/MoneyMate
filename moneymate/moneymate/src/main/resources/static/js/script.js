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

// Hamburger menu toggle
const hamburger = document.getElementById('hamburger');
const navLinks = document.getElementById('navLinks');

hamburger.addEventListener('click', () => {
  navLinks.classList.toggle('open');
});

// Power button toggle
const powerBtn = document.getElementById('powerToggle');
let isOn = false;

powerBtn.addEventListener('click', () => {
  isOn = !isOn;
  powerBtn.classList.toggle('on', isOn);
  powerBtn.classList.toggle('off', !isOn);
});

// Set default state
powerBtn.classList.add('off');
