<?php
  $login = filter_var(trim($_POST['login']),FILTER_SANITIZE_STRING);
  $password = filter_var(trim($_POST['password']),FILTER_SANITIZE_STRING);


  $password = md5 ($password."2o2l");

  require "blocks\connect.php";
  $result = $mysql->query("SELECT * FROM `users` WHERE `login` = '$login' AND `password` = '$password' " );
  $user = $result->fetch_assoc();
  if(count($user)==0) {
    echo "Пользователь не сущетвует";
    exit();
  }

  setcookie('user',$user['name'], time() + 3600 * 2, "/");

  $mysql->close();
  header('Location: authlogin.php');
?>
