<!doctype html>
<html lang="ru" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <title>Business Shop - Main page</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="d-flex flex-column h-100">
<!--Модальные окна-->
<div class="offcanvas offcanvas-end w-auto" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
    <div class="offcanvas-header">
        <h5 id="offcanvasRightLabel">Корзина</h5>
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <?php CartController::actionIndex()?>
    </div>
</div>

<header>
    <!--Первый навбар-->
    <div class="navbar navbar-topbar navbar-expand-xl navbar-light bg-light">
        <div class="container">
            <!--Promo-->
            <div class="mr-xl-8">
                <i class="bi bi-truck"></i><span class="heading-xxxs"> БЕСПЛАТНАЯ ДОСТАВКА ПО ВСЕМУ МИРУ</span>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#topbarCollapse"
                    aria-controls="topbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Collapse -->
            <div class="collapse navbar-collapse" id="topbarCollapse">
                <!-- Nav -->
                <ul class="nav nav-divided navbar-nav ms-auto">
                    <li>
                    </li>
                </ul>
                <!-- Информация -->
                <ul class="nav navbar-nav mx-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/shipping">Доставка</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/faq">Вопросы-Ответы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/contacts">Контакты</a>
                    </li>
                </ul>

                <!-- Иконки -->
                <ul class="nav navbar-nav flex-row mr-8">
                    <li class="nav-item">
                        <a class="nav-link text-gray-350" href="#!">
                            <h4><i class="bi bi-facebook"></i></h4>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-gray-350" href="#!">
                            <h4><i class="bi bi-telegram"></i></h4>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-gray-350" href="#!">
                            <h4><i class="bi bi-instagram"></i></h4>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-gray-350" href="#!">
                            <h4><i class="bi bi-twitter"></i></h4>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--Второй навбар-->
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container">

            <!-- Brand -->
            <a class="navbar-brand fw-bold" href="/cover">
                Business Shop.
            </a>

            <!-- Toggler -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Collapse -->
            <div class="collapse navbar-collapse" id="navbarCollapse">

                <!-- Nav -->
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="/cover">Главная страница</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="/catalog">Каталог</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="/docs">Документы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="/blog">Блог</a>
                    </li>
                </ul>

                <!-- Nav -->
                <ul class="nav navbar-nav flex-row">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" href="search">
                            <h4><i class="bi bi-search"></i></h4>
                        </a>
                    </li>
                    <li class="nav-item ml-lg-n2">
                        <a class="nav-link" href="/user/login">
                            <h4><i class="bi bi-person-circle"></i></h4>
                        </a>
                    </li>
                    <li class="nav-item ml-lg-n4">
                        <a class="nav-link btn" href="#offcanvasRight" data-bs-toggle="offcanvas"
                           data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">
                            <h4><i class="bi bi-cart"></i></h4>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--Акции-->
    <div class="py-3 bg-dark bg-pattern mb-4">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Text -->
                    <div class="text-center text-white">
              <span class="heading-xxs letter-spacing-xl">
                ⚡️ Скидки в честь открытия интернет магазина ⚡️
              </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>