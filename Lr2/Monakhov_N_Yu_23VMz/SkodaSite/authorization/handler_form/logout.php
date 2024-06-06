<?php
session_start();
unset($_SESSION['user']);
header('Location: ../../index.php?page=logout');
header('Location: ../../index.php?page=index');
exit();

