<!DOCTYPE html>
<html lang="ru">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <link rel="stylesheet" href="css/style.css">
   <link rel="shortcut icon" href="img/logo.jpg" type="image/x-icon">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <title>authlogin</title>
</head>
<body>
<?php require "blocks/header.php" ?>
  <div class="container mt-4">
    <?php
       if($_COOKIE['user'] == ''):
     ?>
    <div class="row">
    <div class="col">
    <h1>Регистрация</h1><br>
    <form action="checkauth.php" method="post">
      <input class="form-control" type="text" name="name" id="name" placeholder="Введите имя"><br>
      <input class="form-control" type="text" name="login" id="login" placeholder="Введите логин (от 5 до 32 символов)"><br>
      <input class="form-control" type="password" name="password" id="password" placeholder="Введите пароль(от 8 до 32 символов)"><br>
      <button class="btn btn-success" type="submit">Зарегестрироваться</button>
    </form>
    </div>
    <div class="col">
    <h1>Авторизация</h1><br>
    <form action="checklogin.php" method="post">
      <input class="form-control" type="text" name="login" id="login" placeholder="Введите логин (от 5 до 32 символов)"><br>
      <input class="form-control" type="password" name="password" id="password" placeholder="Введите пароль(от 8 до 32 символов)"><br>
      <button class="btn btn-success" type="submit">Войти</button>
    </form>
    </div>
    <?php
       else:
     ?>
     <div class="container">
     <p>Добро пожаловать <?=$_COOKIE['user']?></p>
     <p><a href="exit.php"> Выход </a></p>
     </div>
    <?php
       endif;
     ?>
    </div>
  </div>
<?php require "blocks/footer.php" ?>
</body>
