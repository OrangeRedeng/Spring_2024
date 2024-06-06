<?php include ROOT . '/template/layouts/header.php'; ?>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4 padding-right">

                    <?php if (isset($errors) && is_array($errors)): ?>
                        <ul>
                            <?php foreach ($errors as $error): ?>
                                <li> - <?php echo $error; ?></li>
                            <?php endforeach; ?>
                        </ul>
                    <?php endif; ?>
                    <div class="signup-form">
                        <h2 class="h3 mb-3 font-weight-normal">Вход на сайт</h2>
                        <form action="#" method="post">
                            <input type="email" name="email" class="form-control mb-1" placeholder="E-mail" value="<?php echo $email; ?>"/>
                            <input type="password" name="password" class="form-control mb-3" placeholder="Пароль" value="<?php echo $password; ?>"/>
                            <button type="submit" name="submit" class="btn btn-lg btn-primary btn-block ">Войти</button>
                        </form>
                        <hr>
                        Нет аккаунта? <a href="/user/register/">Зарегестрироваться</a>
                    </div>
                    <br/>
                    <br/>
                </div>
            </div>
        </div>
    </section>


<?php include ROOT . '/template/layouts/footer.php'; ?>