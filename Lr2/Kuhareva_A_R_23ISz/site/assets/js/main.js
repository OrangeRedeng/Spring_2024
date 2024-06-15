document.addEventListener("DOMContentLoaded", function(event) {
    setInterval(function() {
        createTimer();
    }, 1000);
});

function createTimer() {
    let timerElement = document.getElementById('timer');
    let date = new Date();
    let timeNow = ('0' + date.getDate()).slice(-2) + '.' + 
        ('0' + (date.getMonth() + 1)).slice(-2) + '.' + 
        date.getFullYear() + ' ' + 
        ('0' + date.getHours()).slice(-2) + ':' +  
        ('0' + date.getMinutes()).slice(-2) + ':' + 
        ('0' + date.getSeconds()).slice(-2);
    timerElement.textContent = timeNow;        
}