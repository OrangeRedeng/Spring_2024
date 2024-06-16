<?php
  setcookie('user',$user['name'], time() - 3600 * 2, "/");
  header('Location: authlogin.php');
 ?>
