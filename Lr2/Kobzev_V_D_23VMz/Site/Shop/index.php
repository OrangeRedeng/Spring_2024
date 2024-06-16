<!DOCTYPE html>
<html lang="ru">
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <link rel="stylesheet" href="css/style.css">
   <link rel="shortcut icon" href="img/logo.jpg" type="image/x-icon">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <title>Sky-Family</title>
</head>
<body>
<?php require "blocks/header.php" ?>
<?php
 $kategory = array(
   'Аксессуары' => array('1', 'accessories.php'),
   'Заводские квадрокоптеры' => array('2', 'factory.php'),
   'Собери сам' => array('3', '#'),
   'Управление' => array('4', '#'),
   'Рамы' => array('5', '#'),
   'Контроллеры полётов' => array('6', '#'),
   'Контроллеры оборотов' => array('7', '#'),
   'GPS трекеры' => array('8', '#'),
   'Моторы' => array('9', '#'),
   'Винты' => array('10', '#'),
   'Аккумуляторы' => array('11', '#'),
   'Зарядные устройства' => array('12', '#'),
   'Подвески для камер' => array('13', '#'),
   'Камеры' => array('14', '#'), ); ?>
<div class="container mt-5">
  <h3 class="mb-5">Товары</h3>
  <div class="d-flex flex-wrap">
  <?php foreach ($kategory as $namekat => $number) {?>
      <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal"><?php echo $namekat; ?></h4>
      </div>
      <div class="card-body">
        <img class="img-thumbnail" src="img/<?php echo $number[0]; ?>.jpg" alt="Категория картинки">
          <button type="button" class="btn btn-lg btn-block btn-outline-primary mt-3" onclick="window.location.href = '<?php echo $number[1]; ?>';">Подробнее</button>
      </div>
    </div>
<?php } ?>
 </div>
</div>
<?php require "blocks/footer.php" ?>
</body>
</html>
