<?php
  $email = $_POST['email'];
  $message = $_POST['message'];
  $error = '';
  if(trim($email) == '')
    $error = 'Введите ваш email';
  else if (trim($message) == '')
    $error = 'Сообщение не содержит текста';
  else if (strlen($message) < 5)
    $error = 'Это сообщение расценено как спам';

  if($error != ''){
    echo $error;
    exit;
  }

  $subject = "=?utf-8?B?".base64_encode("Сообщение с сайта")."?=";
  $headers = "From: $email\r\nReply-to: $email\r\nContent-type: text/html;charset=utf-8\r\n";

  mail('vaadjm@yandex.ru', $subject, $message, $headers);

  header('Location: feedback.php');
?>
