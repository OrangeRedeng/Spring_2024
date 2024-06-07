<?php
session_start();
?>

<div class="modal fade" id="modal-cart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header text-white" style="background-color: #282626;">
        <h5 class="modal-title" id="exampleModalLabel">Корзина</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div class="modal-body">
        <table class="table">
          <thead>
            <td>Фото модели</td>
            <td>Наименование</td>
            <td>Колличество</td>
            <td>Стоимость</td>
            <td>Итого</td>
        </thead>
        <tbody>
            <?php
            $sql_m = $link->query("SELECT * FROM `products`");
            $Sum = 0;
            $add_product = $_SESSION['add_product'];

            if (isset($add_product)) {
              foreach ($add_product as $key => $value) {
                $a = $key;
                $kol =  $_SESSION['add_product'][$a];
                $good_m = [];
                foreach ($sql_m as $product_m) {
                  if ($product_m['id'] == $a) {
                    $good_m = $product_m;
                    break;
                  }
              }
              ?>
              <tr>
                  <td><img width="50px" src="<?php echo $good_m['imgs']; ?>" /></td>
                  <td><?php echo $good_m['name']; ?></td>
                  <td><?php echo $kol; ?>
              </td>


              <td><?php echo $good_m['price'] . 'р'; ?></td>
              <td><?php echo $kol * $good_m['price'] . 'р'; ?></td>
              <td align="right" colspan="5"><b> <a href="rem_basket.php?remId=<?php echo $a;?>">Удалить</a></b></td>

          </tr>
          <?php
                $Sum += $kol * $good_m['price'];
              }
          }
            // Выводим итоговую сумму заказа
          ?>
          <tr>
              <td align="right" colspan="5"><b> <?php echo 'Всего: ' . $Sum ?></b></td>
          </tr>
      </tbody>
  </table>
</div>
<div class="modal-footer">
  <b> <a href="add_basket.php">Оформить заказ</a></b>
  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
</div>
</div>
</div>
</div>

<!--Футер-->
<footer>
    <section class="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-6">
                    <h4>Информация</h4>
                    <ul class="list-unstyled">
                        <li><a href="index.php?page=index">Главная</a></li>
                        <li><a href="">Модельный ряд</a></li>
                        <li><a href="index.php?page=historie">История Skoda</a></li>
                    </ul>
                </div>

                <div class="col-md-3 col-6">
                    <h4>Время работы</h4>
                    <ul class="list-unstyled">
                        <li>ул. Студенческая д</li>
                        <li>пн:10:00 - 18:00</li>
                        <li>Без перерыва</li>
                    </ul>
                </div>

                <div class="col-md-3 col-6">
                    <h4>Контакты</h4>
                    <ul class="list-unstyled">
                        <li>8(999)-072-44-55</li>
                        <li>8(999)-073-45-57</li>
                        <li>8(999)-074-45-55</li>
                    </ul>
                </div>

                <div class="col-md-3 col-6">
                    <h4>Социальные сети</h4>
                    <div class="footer-icons">
                        <a href="https://www.facebook.com/skodarussia/"><i class="fab fa-facebook"></i></a>
                        <a href="info@skoda-avtoruss.ru"><i class="fas fa-at"></i></a>
                        <a href="https://vk.com/skodarussia"><i class="fab fa-vk"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</footer>
<script src="script/bootstrap.js"></script>
</body>
</html>