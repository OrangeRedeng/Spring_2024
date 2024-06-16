<!DOCTYPE html>
<html lang="ru">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <link rel="stylesheet" href="css/style.css">
   <link rel="shortcut icon" href="img/logo.jpg" type="image/x-icon">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <title>Заводские квадрокоптеры</title>
</head>
<body>
<?php require "blocks/header.php" ?>
<?php
 $quadcopters = array(
   'Mavic air pro 2' => array('1','25000 ₽'),
   '3DR Solo Drone' => array('2','32500 ₽'),
) ?>
<div class="container mt-5">
  <h3 class="mb-5">Заводские квадрокоптеры</h3>
  <div class="d-flex flex-wrap">
  <?php foreach ($quadcopters as $namequadcopters => $numberquadcopters) {?>
      <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal"><?php echo $namequadcopters; ?></h4>
      </div>
      <div class="card-body">
        <img class="img-thumbnail" src="quadcoptersimg/<?php echo $numberquadcopters[0]; ?>.jpg" alt="">
        <h5 class="my-2 font-weight-bold text-center"><?php echo $numberquadcopters[1]; ?></h5>
          <button type="button" class="btn btn-lg btn-block btn-outline-primary mt-3" onclick="window.location.href = 'basket.php';">В корзину</button>
      </div>
    </div>
<?php } ?>
 </div>
</div>
<?php require "blocks/footer.php" ?>
</div>
</body>
</html>
