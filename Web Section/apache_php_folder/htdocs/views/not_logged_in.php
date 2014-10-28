<?php
// show potential errors / feedback (from login object)
if (isset($login)) {
    if ($login->errors) {
        foreach ($login->errors as $error) {
            echo $error;
        }
    }
    if ($login->messages) {
        foreach ($login->messages as $message) {
            echo $message;
        }
    }
}
?>
<!-- login form box -->
<form class="navbar-form pull-right" method="post" action="index.php" name="loginform">
  <input id="login_input_username" class="span2" type="text" placeholder="Username" name="user_name" required />
  <input id="login_input_password" class="span2" type="password" placeholder="Password" name="user_password" autocomplete="off" required >
  <button type="submit" name="login" value="Log in" class="btn">Sign in</button>
</form>
