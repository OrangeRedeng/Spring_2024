<?php

class NotFoundController{
    public function actionIndex(){

        require_once(ROOT . '/views/notFound/index.php');
        return true;
    }
}