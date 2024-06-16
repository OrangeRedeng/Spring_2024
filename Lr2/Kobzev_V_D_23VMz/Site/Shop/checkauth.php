<?php
  $login = filter_var(trim($_POST['login']),FILTER_SANITIZE_STRING);
  $name = filter_var(trim($_POST['name']),FILTER_SANITIZE_STRING);
  $password = filter_var(trim($_POST['password']),FILTER_SANITIZE_STRING);

  if (mb_strlen ($name) < 2) {
    echo "Введите имя";
    exit();
  } else if (mb_strlen ($login) < 5 || mb_strlen($login) > 32) {
    echo "Логин не соответствует требованиям";
    exit();
  } else if (mb_strlen ($password) < 8 || mb_strlen($password) > 32) {
    echo "Пароль не соответствует требованиям";
    exit();
  }
  $password = md5 ($password."2o2l");

  require "blocks\connect.php";
  $mysql->query("INSERT INTO `users` (login, password, name) VALUES('$login', '$password', '$name')");
  $mysql->close();
  header('Location: authlogin.php');
?>
