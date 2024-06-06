<?php

return array(

    // Пользовательские пути:
        // * Кабинет пользователя
        'cabinet' => 'cabinet/index',

        // * Редактирование информации
        'cabinet/edit' => 'cabinet/edit',

    // Корзина:
    'cart/checkout' => 'cart/checkout',
    'cart/delete/([0-9]+)' => 'cart/delete/$1',
    'cart/add/([0-9]+)' => 'cart/add/$1',

    // Авторизацция
        // * Регистрация
        'user/register' => 'user/register',

        // * Авторизация
        'user/login' => 'user/login',

        // * Деавторизация
        'user/logout' => 'user/logout',

    // Администраторские пути:
        // * Управление товарами:
        'admin/product/create' => 'adminProduct/create',
        'admin/product/update/([0-9]+)' => 'adminProduct/update/$1',
        'admin/product/delete/([0-9]+)' => 'adminProduct/delete/$1',
        'admin/product' => 'adminProduct/index',
        // * Управление категориями:
        'admin/category/create' => 'adminCategory/create',
        'admin/category/update/([0-9]+)' => 'adminCategory/update/$1',
        'admin/category/delete/([0-9]+)' => 'adminCategory/delete/$1',
        'admin/category' => 'adminCategory/index',
        // * Управление заказами:
        'admin/order/update/([0-9]+)' => 'adminOrder/update/$1',
        'admin/order/delete/([0-9]+)' => 'adminOrder/delete/$1',
        'admin/order/view/([0-9]+)' => 'adminOrder/view/$1',
        'admin/order' => 'adminOrder/index',
    // Админпанель:
    'admin' => 'admin/index',

    // Основные маршруты
        // * Доставка
        'shipping' => 'site/shipping',

        // * FAQ
        'faq' => 'site/faq',

        // * Контакты
        'contacts' => 'site/contact',

        // * Главная страница
        'cover' => 'cover/index',

        // * Каталог:
        'catalog' => 'catalog/index',

        // * Категория товаров:
        'category/([0-9]+)/page-([0-9]+)' => 'catalog/category/$1/$2',
        'category/([0-9]+)' => 'catalog/category/$1',

        // * Товар
        'product/([0-9]+)' => 'product/view/$1',

    // Заглушка
    '404' => 'notFound/index',

);