const toggleBtn = document.getElementById('toggle-btn')
const text = document.getElementById('text')

toggleBtn.addEventListener('click', () => {
  text.hidden = !text.hidden
})
