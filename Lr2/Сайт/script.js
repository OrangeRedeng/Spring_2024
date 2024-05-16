function goToLink(url) {
    window.location.href = url;
}

function goToSrc(src) {
    if (!src.endsWith('.html')) {
    src += '.html';
    }
    window.location.href = src;
}

/*Далее скрипт для модульного окна*/

const openModalButton = document.getElementById('openModal');
const modal = document.getElementById('modal');
const closeButton = document.getElementById('closeButton');
const yesButton = document.getElementById('yesButton');
const noButton = document.getElementById('noButton');

openModalButton.addEventListener('click', () => {
    modal.style.display = 'block';
    noButton.style.position = 'absolute'; // Устанавливаем позиционирование для кнопки "Нет"
    
    const modalRect = modal.getBoundingClientRect(); // Получаем позицию модального окна
    
    // Устанавливаем координаты кнопки "Нет"
    noButton.style.top = `${modalRect.top + 77}px`; // например, сдвиг на 50px от верхней границы модального окна
    noButton.style.left = `${modalRect.left + 225}px`; // например, сдвиг на 50px от левой границы модального окна
    
    // Запоминаем текущие координаты кнопки "Нет"
    noButtonPosition.top = noButton.offsetTop;
    noButtonPosition.left = noButton.offsetLeft;
});

yesButton.addEventListener('click', () => {
    alert('Спасибо, что выбрали нас ♡');
    modal.style.display = 'none';
});

noButton.addEventListener('mouseenter', function() {
    this.style.top = `${Math.random() * 90}%`; // Указываем случайное положение по оси Y
    this.style.left = `${Math.random() * 90}%`; // Указываем случайное положение по оси X
});

closeButton.addEventListener('click', () => {
    modal.style.display = 'none';
    if (noButtonPosition.top !== noButton.offsetTop && noButtonPosition.left !== noButton.offsetLeft) { // Возвращаем кнопку "Нет" на предыдущее местоположение
        noButton.offsetTop = noButtonPosition.top + 'px';
        noButton.offsetLeft = noButtonPosition.left + 'px';
    }
});