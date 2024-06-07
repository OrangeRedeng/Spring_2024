<?php

if ( ! isset($_SESSION)) {
    session_start();
}
$id_user = $_SESSION['user']['id'];

if (!isset($_GET['delid'])){

} else {
    $id = $_GET['delid'];
    $sql_basket_remove = $link->query("DELETE FROM basket WHERE id=$id"); 
    header('Location: '.'index.php?page=profile');
}
?>

<div>
	<p>Страница админа</p>
	<form>
        <?php 
        $img='<img src="authorization/'.$_SESSION['user']['avatar'].'" width="200" alt="">';
        echo $img;
    ?>
    <h2 style="margin: 10px 0;"><?= $_SESSION['user']['full_name'] ?></h2>
    <a href="#"><?= $_SESSION['user']['email'] ?></a>
    <table class="tableProfile">
        <tr>
            <td style="font-size: 20px;"><b>Email Заказчика</b></td>
            <td style="font-size: 20px;"><b>Наименование</b></td>
            <td style="font-size: 20px;"><b>Количество</b></td>
            <td style="font-size: 20px;"><b>Цена за шт.</b></td>
            <td style="font-size: 20px;"><b>Итого</b></td>
            <td></td>
        </tr>
        <?php
        $sql_m      = $link->query("SELECT * FROM `products`");
        $Sum        = 0;
        $sql_basket = $link->query("SELECT * FROM `basket`");
        if (isset($sql_basket)) {
            foreach ($sql_basket as $basket) {
                
                    $a      = $basket['id_product'];
                    $kol    = $basket['number_product'];
                    $good_m = [];
                    foreach ($sql_m as $product_m) {
                        if ($product_m['id'] == $a) {
                            $good_m = $product_m;
                            break;
                        }
                    } ?>
                    <tr>
                        <td>
                            <?php
                            	$idUs = $basket['id_user'];
                            	$sql_idUser = $link->query("SELECT * FROM `users` Where id =".$idUs);

                            	foreach ($sql_idUser as $id_m) {
                        			echo $id_m['email'];
                    			}
                    			
                            ?>
                        </td>
                        <td><?php 
                        
                        echo $good_m['name']; 
                        ?></td>
                        <td> <?php echo $kol; ?> </td>
                        <td>
                            <?php 
                                if (!isset($good_m['discount'])){
                                    echo $good_m['price'].'р'; 
                                    $realPrice = $good_m['price'];    
                                } else {
                                    echo '<a class="oldPrice">'.$good_m['price']."</a>";
                                    $realPrice = ($good_m['price'] - ($good_m['price']*$good_m['discount'])/100);
                                    echo $realPrice;
                                }
                                
                            ?>     
                        </td>
                        <td>
                            <?php 
                                echo $kol * $realPrice. 'р'; 
                            ?>    
                        </td>
                        <td><b><a href="index.php?page=profile&delid=<?php echo $basket['id'];?>">удалить</a></b></td>
                    </tr>
                    <?php
                    $Sum += $kol * $realPrice;
                }
            }
        
        ?>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td align="right" colspan="5"><b> <?php echo 'Всего: ' . $Sum ?></b>
            </td>
        </tr>
    </table>
 
</form>
<br>
<form>
    <table class="tableProfile">
        <tr>
            <td style="font-size: 20px;"><b>id</b></td>
            <td style="font-size: 20px;"><b>ФИО</b></td>
            <td style="font-size: 20px;"><b>Email</b></td>
            <td style="font-size: 20px;"><b>Роль</b></td>
        </tr>
        <?php
        $sql_users     = $link->query("SELECT * FROM `users`");
            foreach ($sql_users as $user) {
            	echo "<tr>";
            	echo "<td>";
                	echo $user['id'];
                echo "</td>";
                echo "<td>";
                	echo $user['full_name'];
                echo "</td>";
                echo "<td>";
                	echo $user['email'];
                echo "</td>";
                echo "<td>";
                	echo $user['role'];
                echo "</td>";
                }
            
        
        ?>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
     <a href="authorization/handler_form/logout.php" class="logout">Выход</a>
</form>



</div>
