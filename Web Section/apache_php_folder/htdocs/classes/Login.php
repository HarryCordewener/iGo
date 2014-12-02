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
        $loginxml = 
        '<?xml version="1.0" encoding="UTF-8"?>
         <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
            <SOAP-ENV:Header/>
            <S:Body>
                <ns2:getYelpData xmlns:ns2="http://local/">
                    <arg0>login</arg0>
                    <arg1>%s</arg1>
                    <arg2>%s</arg2>
                    <arg3>0</arg3>
                </ns2:getYelpData>
            </S:Body>
        </S:Envelope>';

        // check login form contents
        if (empty($_POST['user_name'])) {
            $this->errors[] = "Username field was empty.";
        } elseif (empty($_POST['user_password'])) {
            $this->errors[] = "Password field was empty.";
        } elseif (!empty($_POST['user_name']) && !empty($_POST['user_password'])) {
			$loginxml = sprintf($loginxml, $_POST['user_name'], $_POST['user_password']);

            /*
			$truefalse = GetPost($loginurl, array( "Username" => $_POST['user_name'], 
													 "Password" => $_POST['user_password'] )); 
            */

            $soap_do = curl_init(); 

            curl_setopt($soap_do, CURLOPT_URL,            $serviceurl );   
            curl_setopt($soap_do, CURLOPT_CONNECTTIMEOUT, 10); 
            curl_setopt($soap_do, CURLOPT_TIMEOUT,        10); 
            curl_setopt($soap_do, CURLOPT_RETURNTRANSFER, true );
            curl_setopt($soap_do, CURLOPT_SSL_VERIFYPEER, false);  
            curl_setopt($soap_do, CURLOPT_SSL_VERIFYHOST, false); 
            curl_setopt($soap_do, CURLOPT_POST,           true ); 
            curl_setopt($soap_do, CURLOPT_POSTFIELDS,    $loginxml); 
            curl_setopt($soap_do, CURLOPT_HTTPHEADER,     array('Content-Type: text/xml; charset=utf-8', 'Content-Length: '.strlen($loginxml) )); 
            // curl_setopt($soap_do, CURLOPT_USERPWD, $user . ":" . $password);
            

            $truefalse = curl_exec($soap_do);
            $err = curl_error($soap_do);  

            if($truefalse == "1") {
            	// write user data into PHP SESSION (a file on your server)
                $_SESSION['user_name'] = $_POST['user_name'];
                $_SESSION['user_login_status'] = 1;
                echo "FALSE";
            } else {
                $this->errors[] = '<code> Returned: >'.$truefalse.'< with error: '.$err.'</code>';

                //$this->errors[] = '<font color="white">Wrong username or password. Try again.</font>';
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

