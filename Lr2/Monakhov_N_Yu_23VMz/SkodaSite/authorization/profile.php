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


<form class="profile">
    <?php 
        $img='<img src="authorization/'.$_SESSION['user']['avatar'].'" width="200" alt="">';
        echo $img;
    ?>
    <h2 style="margin: 10px 0;"><?= $_SESSION['user']['full_name'] ?></h2>
    <a href="#"><?= $_SESSION['user']['email'] ?></a>
    <table class="tableProfile">
        <tr>
            <td></td>
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
                if ($basket['id_user'] == $id_user) {
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
                        <td><img width="300px" src="<?php echo $good_m['imgs']; ?>"/></td>
                        <td><?php echo $good_m['name']; 
                        ?>   
                        </td>
                        <td> <?php echo $kol; ?> </td>
                        <td>
                            <?php 
                                if (!isset($good_m['discount'])){
                                    echo $good_m['price'].'р'; 
                                    $realPrice = $good_m['price'];    
                                } 
                                
                            ?>     
                        </td>
                        <td>
                            <?php 
                                echo $kol * $realPrice. 'р'; 
                            ?>    
                        </td>
                        <td><b><a href="index.php?page=profile&delid=<?php echo $basket['id'];?>">Удалить</a></b></td>
                    </tr>
                    <?php
                    $Sum += $kol * $realPrice;
                }
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
    <a href="authorization/handler_form/logout.php" class="logout">Выход</a>
</form>