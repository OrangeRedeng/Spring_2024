<?php
$host = 'localhost';
$user = 'root';
$password = '';
$db_name = 'site_skoda';

$link = mysqli_connect($host,$user,$password,$db_name);

if ($link->maxdb_connect_errno) exit('Ошибка соединения с БД');

$link->set_charset('utf8');
?>