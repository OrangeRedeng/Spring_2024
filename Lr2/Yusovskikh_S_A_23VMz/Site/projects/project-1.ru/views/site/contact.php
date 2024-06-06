<?php include ROOT . '/template/layouts/header.php'; ?>

    <div class="container mb-3">

<?php if ($result): ?>

    <div class="row">
    <h1 class="mt-5">Обратная связь</h1>
    <p>Сообщение отправлено! Мы ответим Вам на указанный email.</p>
    </div>
    </div>
<?php else: ?>
    <?php if (isset($errors) && is_array($errors)): ?>
        <ul>
            <?php foreach ($errors as $error): ?>
                <li> - <?php echo $error; ?></li>
            <?php endforeach; ?>
        </ul>
    <?php endif; ?>


    <div class="row">
        <h1 class="mt-5">Обратная связь</h1>
        <p class="lead">Есть вопрос? Напишите нам</p>

        <form action="#" method="post">
            <div class="col-sm-3">
                <label for="email"  class="form-label">Email <span class="text-muted">(Optional)</span></label>
                <input type="email"  name="userEmail" class="form-control" id="email" placeholder="you@example.com"
                       value="<?php echo $userEmail; ?>">
                <div class="invalid-feedback">
                    Введите корректный адрес почты
                </div>
            </div>
            <div class="col-sm-12">
                <label for="firstName" class="form-label">Сообщение</label>
                <input type="text" name="userText" class="form-control" id="firstName" placeholder="" required=""
                       value="<?php echo $userText; ?>">
                <div class="invalid-feedback">
                    Сообщение некоректно
                </div>
            </div>
            <hr>
            <button class="btn btn-primary" name="submit" type="submit" value="Отправить">Отправить</button>
        </form>
    </div>
    </div>

<?php endif; ?>


<?php include ROOT . '/template/layouts/footer.php'; ?>