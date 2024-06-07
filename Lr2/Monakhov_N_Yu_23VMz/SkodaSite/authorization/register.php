<?php

if ( ! isset($_SESSION)) {
    session_start();
}
if (array_key_exists('user', $_SESSION)) {
    require 'profile.php';
    exit();
}
?>

<div class="avtor d-flex">
     <div class="title">
            <h3>Регистрация</h3>
    <form action="authorization/handler_form/signup.php" method="post"
          enctype="multipart/form-data">
        <label>ФИО</label>
        <input type="text" name="full_name" 
               placeholder="Введите свое ФИО">
        <label>Логин</label>
        <input type="text" name="login" placeholder="Введите свой логин" required pattern="^\S{3,10}$">
        <label>Почта</label>
        <input type="email" name="email" required
               placeholder="Введите адрес своей почты">
        <label>Изображение профиля</label>
        <input type="file" name="avatar">
        <label>Пароль</label>
        <input id="password" name="password" type="password" pattern="^\S{6,16}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Должно быть не менее 6 и не более 16 символов' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;" placeholder="Введите пароль" required> 
        <label>Подтверждение пароля</label>
        <input id="password_confirm" name="password_confirm" type="password" pattern="^\S{6,16}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Пожалуйста, введите тот же пароль, что и выше' : '');" placeholder="Подтвердите пароль" required>
        <button type="submit">Зарегистрироваться</button>
        <p>
            У вас уже есть аккаунт? - <a href="index.php?page=login">Авторизируйтесь</a>!
        </p>
        <?php
        if (array_key_exists('message', $_SESSION)) {
            echo '<p class="msg"> ' . $_SESSION['message'] . ' </p>';
        }
        unset($_SESSION['message']);
        ?>
    </form>
</div>
</div>

