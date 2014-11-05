<style>
  .travelbackground {
    filter: alpha(opacity=30);
    background:url("http://dialog.scarborough.com/wp-content/uploads/2012/04/tech-savvy-travelers.jpg");
    filter: progid:DXImageTransform.Microsoft.Alpha(opacity=30);
    opacity:0.3;
   -moz-opacity: 0.30; 
  }

</style>

<?php include('header.php') ?>

      <!-- Main hero unit for a primary marketing message or call to action -->
      <div class="hero-unit travelbackground">
        <h1 class="iGoHead">iGo! </h1>
        <p class="iGoHead">Where will you go?</p>
        <!-- <p><a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a></p> -->
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="span4">
          <h2>Introduction</h2>
          <p>iGo allows you to plan and register your travels and coordinate with your friends! </p>
          <p><a class="btn" href="register.php">Register &raquo;</a></p>
        </div>
        <div class="span4">
          <h2>The Team</h2>
          <p>iGo is brought to you by: 
            <ul>
            <li>Shouvik Sayef &lt;shouvik.sayef@gmail.com&gt;</il>
            <li>Lokesh Gunda &lt;lgunda2@uic.edu&gt;</il>
            <li>Chetana Vedantam &lt;cvedan2@uic.edu&gt;</il>
            <li>Kanishka Garg &lt;kgarg3@uic.edu&gt;</il>
            <li>Sudhanshu Malhotra &lt;smalho4@uic.edu&gt;</il>
            <li>Harry Cordewener &lt;hcorde2@uic.edu&gt;</il>
          </p>
       </div>
        <div class="span4">
          <h2>Get Started</h2>
          <p>To start, please register, then log in. This will add available options from the Options tab at the top of the page.</p>
          <?php 
            if ($login->isUserLoggedIn() == true) {
                  // the user is logged in. you can do whatever you want here.
                  // for demonstration purposes, we simply show the "you are logged in" view.
                  include("views/logged_in.php");
              } else {
                  // the user is not logged in. you can do whatever you want here.
                  // for demonstration purposes, we simply show the "you are not logged in" view.
                  include("views/not_logged_in.php");
              }
          ?>
        </div>
      </div>

      <hr>

<?php include('footer.php') ?>