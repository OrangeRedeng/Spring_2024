<?php

session_start();
require_once '../../connect.php';
$full_name        = $_POST['full_name'];
$login            = $_POST['login'];
$email            = $_POST['email'];
$password         = $_POST['password'];
$password_confirm = $_POST['password_confirm'];
$check_user       = mysqli_query(
    $link,
    "SELECT * FROM `users` WHERE `login` = '$login' "
);
if (mysqli_num_rows($check_user) > 0) {
    $_SESSION['message'] = 'Такой логин уже используется';
    $redirect            = $_SERVER['HTTP_REFERER'] ?? 'redirect-form.html';
    header("Location: $redirect");
    exit();
} else {
    if ($password === $password_confirm) {
        $path = 'uploads/' . time() . $_FILES['avatar']['name'];
        if ( ! move_uploaded_file(
            $_FILES['avatar']['tmp_name'],
            '../' . $path
        )
        ) {
            $_SESSION['message'] = 'Ошибка при загрузке сообщения';
            $redirect            = $_SERVER['HTTP_REFERER'] ??
                'redirect-form.html';
            header("Location: $redirect");
            exit();
        }
        $password = md5($password);
        mysqli_query(
            $link,
            "INSERT INTO `users` (`id`, `full_name`, `login`, `email`, `password`, `avatar`) VALUES (NULL, '$full_name', '$login', '$email', '$password', '$path')"
        );
        $_SESSION['message'] = 'Регистрация прошла успешно!';
    } else {
        $_SESSION['message'] = 'Пароли не совпадают';
    }
    $redirect = $_SERVER['HTTP_REFERER'] ?? 'redirect-form.html';
    header("Location: $redirect");
    exit();
}