<?php require_once ROOT.'/template/layouts/header.php'?>

<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        Категории
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                     data-bs-parent="#accordion">
                    <div class="accordion-body">
                        <ul class="nav flex-column">
                            <?php

                            include_once ROOT . '/models/Category.php';

                            $categories = array();
                            $categories = Category::getCategoriesList();

                            foreach ($categories as $categoryItem): ?>
                                <li class="nav-item ">
                                    <a class="nav-link p-1 fw-bold"
                                       href="/category/<?php echo $categoryItem['id']; ?>"><?php echo $categoryItem['name']; ?></a>
                                </li>
                            <?php endforeach; ?>
                        </ul>
                    </div>
            </div>
            </div>

            <div class="col-sm-9 padding-right">
                <div class="features_items">
                    <h2 class="title text-center">Последние товары</h2>

                    <?php foreach ($categoryProducts as $product): ?>
                        <div class="col-sm-3">
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <?php if ($product['is_new']): ?>
                                            <img src="/template/images/home/new.png" class="new" alt="" />
                                        <?php endif; ?>
                                        <?php echo Product::getImage($product['id']);?>
                                        <h2><?php echo $product['price']; ?> Руб.</h2>
                                        <p>
                                            <a href="/product/<?php echo $product['id']; ?>">
                                                <?php echo $product['name']; ?>
                                            </a>
                                        </p>
                                        <a href="/cart/add/<?php echo $product['id']; ?>" class="btn btn-lg btn-primary btn-block add-to-cart" data-id="<?php echo $product['id']; ?>"><i class="bi bi-shopping-cart"></i>В корзину</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <?php endforeach; ?>

                </div><!--features_items-->

                <!-- Постраничная навигация -->
                <?php echo $pagination->get(); ?>

            </div>
        </div>
    </div>
</section>

<?php require_once ROOT.'/template/layouts/footer.php'?>