//restrict future time
window.onload = function () {
    const today = new Date().toISOString().split("T")[0];
    document.getElementsByName("date")[0].setAttribute("max", today);
};

// time
const dateInput = document.getElementById('dateInput');
const timeInput = document.getElementById('timeInput');

dateInput.addEventListener('change', () => {
    if (dateInput.value) {
        timeInput.disabled = false;
    } else {
        timeInput.disabled = true;
        timeInput.value = "";
    }
});
