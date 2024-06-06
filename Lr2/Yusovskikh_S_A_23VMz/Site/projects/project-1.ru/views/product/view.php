<?php require_once ROOT . '/template/layouts/header.php' ?>

<section>
    <div class="container md-2">
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
                <div class="product-details"><!--product-details-->
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="view-product">
                                <?php echo Product::getImage($product['id']); ?>
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="product-information"><!--/product-information-->

                                <?php if ($product['is_new']): ?>
                                    <img src="/template/images/product-details/new.jpg" class="newarrival" alt="" />
                                <?php endif; ?>

                                <h2><?php echo $product['name']; ?></h2>
                                <span>
                                    <span><?php echo $product['price']; ?> Руб.</span>
                                    <a href="/cart/add/<?php echo $product['id']; ?>" class="btn btn-lg btn-primary btn-block add-to-cart" data-id="<?php echo $product['id']; ?>"><i class="bi bi-shopping-cart"></i>В корзину</a>

                                </span>
                                <p><b>Наличие:</b> <?php echo Product::getAvailabilityText($product['availability']); ?></p>
                                <p><b>Производитель:</b> <?php echo $product['brand']; ?></p>
                            </div><!--/product-information-->
                        </div>
                    </div>
                    <div class="row">                                
                        <div class="col-sm-12">
                            <br/>
                            <h5>Описание товара</h5>
                            <?php echo $product['description']; ?>
                        </div>
                    </div>
                </div><!--/product-details-->
                <br>

            </div>
        </div>
    </div>
</section>

<?php require_once ROOT . '/template/layouts/footer.php' ?>