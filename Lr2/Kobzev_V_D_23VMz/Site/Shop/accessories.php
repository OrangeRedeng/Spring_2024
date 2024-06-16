<!DOCTYPE html>
<html lang="ru">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <link rel="stylesheet" href="css/style.css">
   <link rel="shortcut icon" href="img/logo.jpg" type="image/x-icon">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <title>Аксессуары</title>
</head>
<body>
<?php require "blocks/header.php" ?>
<?php
 $accessories = array(
   'Освещение XB5' => array('1','1499 ₽'),
   'Защита лопастей' => array('2','999 ₽'),
   'Рюкзак Lowepro QuadGuard' => array('3','2999 ₽'),
) ?>
<div class="container mt-5">
  <h3 class="mb-5">Аксессуары</h3>
  <div class="d-flex flex-wrap">
  <?php foreach ($accessories as $nameaccessories => $numberaccessories) {?>
      <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal"><?php echo $nameaccessories; ?></h4>
      </div>
      <div class="card-body">
        <img class="img-thumbnail" src="accessoriesimg/<?php echo $numberaccessories[0]; ?>.jpg" alt="">
        <h5 class="my-2 font-weight-bold"><?php echo $numberaccessories[1]; ?></h5>
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
