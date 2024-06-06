<div class="container-fluid my-carousel">
  <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="3000">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4" aria-label="Slide 5"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="5" aria-label="Slide 6"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="6" aria-label="Slide 7"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="7" aria-label="Slide 8"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="8" aria-label="Slide 9"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="9" aria-label="Slide 10"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">

        <img src="images/carousel/new-banner-octavia2021.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item ">
        <img src="images/carousel/simply-clever-main-kv-new.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/skoda-superb-offer-kv-new-desk.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/nov-rapid-2.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/new_superb_main_desktop_re.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/kodiaq-landing-spec-new.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/new-spec-kv-karoq-banner.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/heo-new-index.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/heo-new-KV21-desk.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="images/carousel/trade-in_desktop_re.jpeg" class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</div>
<div class="model_head">
  <h3>Cписок автомобилей Skoda</h3>
</div>
<div class="container content">
  <div class="sort-block mb-4 mt-4">
    <form action="">
      <select onchange="location=value">
        <option value="" selected="selected">Сортировка по имени</option>
        <option value="index.php?page=sort&id_sort=1">A-Z</option>
        <option value="index.php?page=sort&id_sort=2">Z-A</option>
      </select>

      <select onchange="location=value">
        <option value="" selected="selected">Сортировка по цене</option>
        <option value="index.php?page=sort&id_sort=3">по возрастанию</option>
        <option value="index.php?page=sort&id_sort=4">по убыванию</option>
      </select>

    </form>
  </div>

  <div class="row">
    <div class="col-lg-2">
      <ul class="list-group">
        <?php
        $sql_cat = $link->query("select * from `category`");
        foreach ($sql_cat as $cat) :
          ?>
          <a href="index.php?page=model_cat&id_cat=<?php echo $cat['id_category']; ?>">
            <li class="list-group-item"><?php echo $cat['name']; ?></li>
          </a>
        <?php endforeach; ?>
        <a href="index.php?page=model_cat&id_cat=0">
          <li class="list-group-item">Всё</li>
        </a>
      </ul>
    </div>

    <div class="col-md-9">
      <section class="main-content">
        <div class="container">
          <div class="row">
              <?php
            foreach ($sql as $good):

            ?>
            <div class="col-lg-4 .col-sm-6 mb-4">
              <div class="product-card">
                <div class="product-thumb">
                  <img src="<?php echo $good['imgs']; ?>" alt="">
                </div>
                <div class="product-details">
                  <h4><a href="index.php?page=modelCart&id=<?php echo $good['id']?>"><?php echo $good['name']; ?></a></h4>
                  <div class="product-bottom-details d-flex justify-content-between">
                    <div class="product-price">
                     <?php echo $good['price']." ₽"; ?>
                   </div>
                   <div class="product-links">
                    <a href="#"><i class="fas fa-shopping-cart"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <?php endforeach;?>
        </div>
      </div>
    </section>
  </div>
</div>
</div>