<?php

include 'Post.php';

/**
 * Class login
 * handles the user's login and logout process
 */
class Login
{
	private $loginurl = '/Login_Auth' ;
    private $serviceurl = 'http://54.69.0.233:8080/iGoWeb/HappyPathClientService?Login';
    private $serviceWSDLurl = 'http://54.69.0.233:8080/iGoWeb/HappyPathClientService?WSDL';
    /**
     * @var array Collection of error messages
     */
    public $errors = array();
    /**
     * @var array Collection of success / neutral messages
     */
    public $messages = array();

    /**
     * the function "__construct()" automatically starts whenever an object of this class is created,
     * you know, when you do "$login = new Login();"
     */
    public function __construct()
    {
        // create/read session, absolutely necessary
        session_start();

        // check the possible login actions:
        // if user tried to log out (happen when user clicks logout button)
        if (isset($_GET["logout"])) {
            $this->doLogout();
        }
        // login via post data (if user just submitted a login form)
        elseif (isset($_POST["login"])) {
            $this->dologinWithPostData();
        }
    }

    /**
     * log in with post data
     */
    private function dologinWithPostData()
    {
        // check login form contents
        if (empty($_POST['user_name'])) {
            $this->errors[] = "Username field was empty.";
        } elseif (empty($_POST['user_password'])) {
            $this->errors[] = "Password field was empty.";
        } elseif (!empty($_POST['user_name']) && !empty($_POST['user_password'])) {

            $requestParams = array( 'login', $_POST['user_name'], $_POST['user_password'], "0");

            $client = new SoapClient($serviceWSDLurl);
            $response = $client->getYelpData($requestParams);

            $truefalse = print_r($response);

            if($truefalse == "1") {
            	// write user data into PHP SESSION (a file on your server)
                $_SESSION['user_name'] = $_POST['user_name'];
                $_SESSION['user_login_status'] = 1;
                echo "FALSE";
            } else {
                // $this->errors[] = '<code> Returned: >'.$truefalse.'< with error: '.$err.'</code>';
                $this->errors[] = '<font color="white">Wrong username or password. Try again.</font>';
            }
        }
    }

    /**
     * perform the logout
     */
    public function doLogout()
    {
        // delete the session of the user
        $_SESSION = array();
        session_destroy();
        // return a little feeedback message
        $this->messages[] = "You have been logged out.";

    }

    /**
     * simply return the current state of the user's login
     * @return boolean user's login status
     */
    public function isUserLoggedIn()
    {
        if (isset($_SESSION['user_login_status']) AND $_SESSION['user_login_status'] == 1) {
            return true;
        }
        // default return
        return false;
    }
}

