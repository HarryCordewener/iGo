<?php

include 'Post.php';

/**
 * Class registration
 * handles the user registration
 */
class Registration
{
    private $registerurl = '/Register_Me' ;
    private $serviceurl = 'http://54.69.0.233:8080/iGoWeb/HappyPathClientService?Tester';
    private $serviceWSDLurl = 'http://54.69.0.233:8080/iGoWeb/HappyPathClientService?WSDL';

    /**
     * @var array $errors Collection of error messages
     */
    public $errors = array();
    /**
     * @var array $messages Collection of success / neutral messages
     */
    public $messages = array();

    /**
     * the function "__construct()" automatically starts whenever an object of this class is created,
     * you know, when you do "$registration = new Registration();"
     */
    public function __construct()
    {
        if (isset($_POST["register"])) {
            $this->registerNewUser();
        }
    }

    /**
     * handles the entire registration process. checks all error possibilities
     * and creates a new user in the database if everything is fine
     */
    private function registerNewUser()
    {
        $telephone = "000-0000-000"; // Not implemented.
        if (empty($_POST['user_name'])) {
            $this->errors[] = "Empty Username";
        } elseif (empty($_POST['user_password_new']) || empty($_POST['user_password_repeat'])) {
            $this->errors[] = "Empty Password";
        } elseif ($_POST['user_password_new'] !== $_POST['user_password_repeat']) {
            $this->errors[] = "Password and password repeat are not the same";
        } elseif (strlen($_POST['user_password_new']) < 6) {
            $this->errors[] = "Password has a minimum length of 6 characters";
        } elseif (strlen($_POST['user_name']) > 64 || strlen($_POST['user_name']) < 2) {
            $this->errors[] = "Username cannot be shorter than 2 or longer than 64 characters";
        } elseif (!preg_match('/^[a-z\d]{2,64}$/i', $_POST['user_name'])) {
            $this->errors[] = "Username does not fit the name scheme: only a-Z and numbers are allowed, 2 to 64 characters";
        } elseif (empty($_POST['user_email'])) {
            $this->errors[] = "Email cannot be empty";
        } elseif (strlen($_POST['user_email']) > 64) {
            $this->errors[] = "Email cannot be longer than 64 characters";
        } elseif (!filter_var($_POST['user_email'], FILTER_VALIDATE_EMAIL)) {
            $this->errors[] = "Your email address is not in a valid email format";
        } elseif (!empty($_POST['user_name'])
            && strlen($_POST['user_name']) <= 64
            && strlen($_POST['user_name']) >= 2
            && preg_match('/^[a-z\d]{2,64}$/i', $_POST['user_name'])
            && !empty($_POST['user_email'])
            && strlen($_POST['user_email']) <= 64
            && filter_var($_POST['user_email'], FILTER_VALIDATE_EMAIL)
            && !empty($_POST['user_password_new'])
            && !empty($_POST['user_password_repeat'])
            && ($_POST['user_password_new'] === $_POST['user_password_repeat'])
        ) {

            /* $truefalse = GetPost($loginurl, array( "Username" => $_POST['user_name'], 
                                                    "Email" => $_POST['user_email'], 
                                                    "Telephone" => $_POST['user_phone'], 
                                                    "Password" => $_POST['user_password_new'] )); */

            $requestParams = array( 'login', $_POST['user_name'], $_POST['user_email'], $_POST['user_phone'], $_POST['user_password_new']);

            $client = new SoapClient($serviceWSDLurl);
            $response = $client->getYelpData($requestParams);

            $truefalse = print_r($response);
            if( $truefalse == "" ) {
                $this->messages[] = "Your account has been created successfully. You can now log in.";
            } else {
                $this->errors[] = $truefalse;
            } 
        } else {
            $this->errors[] = "An unknown error occurred.";
        }
    }
}
