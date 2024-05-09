
const swiper = new Swiper('.swiper', {
  slidesPerView: 1,
  loop: true,
  // пагинация
  pagination: {
    el: '.swiper-pagination',
    clickable: true
  },
  // навигация
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

  effect: 'coverflow',
  coverflowEffect: {
    rotate: 30,
    slideShadows: false,
  },
});
