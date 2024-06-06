<?php

class CoverController{
    public function actionIndex(){

        require_once(ROOT . '/views/cover/index.php');
        return true;
    }
}