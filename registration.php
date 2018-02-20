<?php
require "conn.php";
mysqli_set_charset($conn,"utf8");
if (isset ($_POST['login'],$_POST['password'],$_POST['salt'],$_POST['reg_date'],$_POST['name'],$_POST['number'])){
	$login= $_POST['login'];
	$pass= $_POST['password'];
	$salt= $_POST['salt'];
	$reg_date= $_POST['reg_date'];
	$name= $_POST['name'];
	$number= $_POST['number'];
	$querry0 = mysqli_query($conn,"SELECT login FROM account WHERE login = '$login'");
	if(mysqli_num_rows($querry0) == 0){
		$querry1 = mysqli_query($conn,"INSERT INTO account (login, password,salt, registration_date) VALUES ('$login', '$pass','$salt', '$reg_date')") or die(mysqli_error($conn));
		$querry2 = mysqli_query($conn,"INSERT INTO client (client_name, phone_number, email) VALUES ('$name', '$number','$login' )") or die(mysqli_error($conn));
		echo "1";
	}else echo "0";
}else echo "err";
?>