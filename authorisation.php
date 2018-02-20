<?php
require "conn.php";
mysqli_set_charset($conn,"utf8");
if (isset ($_POST['login'],$_POST['pass'])){
	$login = $_POST['login'];
	$pass = $_POST['pass'];
	$querry = mysqli_query($conn,"SELECT client_id,login, password FROM account WHERE login = '$login' and password = '$pass'");
	
	
	if (mysqli_num_rows($querry) < 1){
		echo "0";
		
	}else{
		while ($r = mysqli_fetch_object($querry))
		{
			$client_id = $r->client_id;
		}
	}
	$querry2 = mysqli_query($conn,"SELECT client_name FROM client WHERE client_id = '$client_id'");
	if (mysqli_num_rows($querry2) == 1){
		while ($r2 = mysqli_fetch_object($querry2))
		{
			$client_name = $r2->client_name;
			echo $client_id . " " . $client_name;
		}
	}
}else echo "error";

?>