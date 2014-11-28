<?php include('header.php') ?>
    <style>
      #map-canvas {
        height: 500px;
        margin: 0px;
        padding: 0px
      }
    </style>
<?php
	if ($login->isUserLoggedIn() == true) {
    	// the user is logged in. you can do whatever you want here.
    	// for demonstration purposes, we simply show the "you are logged in" view.
    	include('views/map.php');
  	} else {
    	// the user is not logged in. you can do whatever you want here.
    	// for demonstration purposes, we simply show the "you are not logged in" view.
    	include("views/not_logged_in.php");
  	}
?>
<?php include('footer.php') ?>