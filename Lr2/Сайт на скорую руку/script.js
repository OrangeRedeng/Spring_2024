function togglePopup(popupId) {
    let popup = document.getElementById(popupId);
    popup.classList.toggle('active');
event.preventDefault()
}

function closePopup(popupId) {
    let popup = document.getElementById(popupId);
    popup.classList.remove('active');
}

document.addEventListener('click', (e) => {
    
    let popups = document.querySelectorAll('.popup');
    popups.forEach((popup) => {
        if (e.target === popup) {
            popup.classList.remove('active');
        }
    });
});


function sendEmail() {
window.alert("Форма отправлена")
}

$(".heart").click(function() { 
    $(this).toggleClass("heart_active"); 
  });
