<div class="zad d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
  <h5 class="raz my-0 mr-md-auto font-weight-normal">
    <img class="mr-2" src="img/logo.jpg" alt="" width="30" height="30">
    Sky-Family
  </h5>
  <nav class="my-2 my-md-0 mr-md-3">
    <a class="p-2 text-dark" href="index.php">Главная</a>
    <a class="p-2 text-dark" href="feedback.php">Обратная связь</a>
    <a class="p-2 text-dark" href="aboutus.html">О нас</a>
  </nav>
  <?php
    if($_COOKIE['user'] == ''):
   ?>
  <a class="btn btn-outline-primary" href="authlogin.php">Авторизация</a>
  <?php
     else:
   ?>
  <a class="btn btn-outline-primary" href="authlogin.php">Кабинет</a>
  <?php
     endif;
   ?>
</div>
