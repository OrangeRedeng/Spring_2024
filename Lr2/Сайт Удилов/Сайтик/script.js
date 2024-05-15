window.addEventListener('load', function() {
    const texts = [
        ' "Судьба. Власть. Коварство." ',
        ' "Все души обречены." ',
        ' "Суета живого мира и покой посмертного." ',
        ' "И ты будешь служить мне, дух." ',
        ' "Железо вечно!" ',
        ' "Я снова воплощён в металле." ',
        ' "Войди и оставь надежду." '
    ];
    
    const changingText = document.getElementById('changingText');

    function changeText() {
        const randomIndex = Math.floor(Math.random() * texts.length);
        changingText.textContent = texts[randomIndex];
    }

    // Change text on page load
    changeText();

    // Change text when switching tabs
    document.addEventListener('visibilitychange', function() {
        if (!document.hidden) {
            changeText();
        }
    });
});