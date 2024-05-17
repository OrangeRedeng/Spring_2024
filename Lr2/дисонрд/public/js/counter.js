const counter = document.getElementById('counter')
const button = document.getElementById('button')
let count = 0

button.addEventListener('click', () => {
  count++
  counter.textContent = `Счетчик: ${count}`
})
