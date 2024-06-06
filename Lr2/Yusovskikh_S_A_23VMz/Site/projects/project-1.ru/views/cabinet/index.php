<?php include ROOT . '/template/layouts/header.php'; ?>

<section>
    <div class="container">
        <div class="row">

            <h3>Кабинет пользователя</h3>
            
            <h4>Привет, <?php echo $user['name'];?>!</h4>
            <ul>
                <li><a href="/cabinet/edit">Редактировать данные</a></li>
            </ul>
            
        </div>
    </div>
</section>

<?php include ROOT . '/template/layouts/footer.php'; ?>