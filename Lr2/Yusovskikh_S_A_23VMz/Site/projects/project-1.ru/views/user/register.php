<?php include ROOT . '/template/layouts/header.php'; ?>

<section>
    <div class="container">
        <div class="row">

            <div class="col-sm-4 col-sm-offset-4 padding-right">
                
                <?php if ($result): ?>
                    <h2 class="h3 mb-3 font-weight-normal">Вы зарегистрированы!</h2>
                <?php else: ?>
                    <?php if (isset($errors) && is_array($errors)): ?>
                        <ul>
                            <?php foreach ($errors as $error): ?>
                                <li> - <?php echo $error; ?></li>
                            <?php endforeach; ?>
                        </ul>
                    <?php endif; ?>

                    <div class="signup-form"><!--sign up form-->
                        <h2>Регистрация на сайте</h2>
                        <form action="#" method="post">
                            <input type="text" name="name" class="form-control mb-1" placeholder="Имя" value="<?php echo $name; ?>"/>
                            <input type="email" name="email" class="form-control mb-1" placeholder="E-mail" value="<?php echo $email; ?>"/>
                            <input type="password" name="password" class="form-control mb-3" placeholder="Пароль" value="<?php echo $password; ?>"/>
                            <input type="submit" name="submit" class="btn btn-lg btn-primary btn-block " value="Регистрация" />
                        </form>
                        <hr>
                        Есть аккаунт? <a href="/user/login/">Войти</a>
                    </div>
                
                <?php endif; ?>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</section>

<?php include ROOT . '/template/layouts/footer.php'; ?>