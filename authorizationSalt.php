<?php
require "conn.php";
if (isset ($_POST['login'])){
	$login = $_POST['login'];
	$querry = mysqli_query($conn,"SELECT salt FROM account WHERE login = '$login'");
	if (mysqli_num_rows($querry) < 1){
		echo "0";
		
	}else{
		while ($r = mysqli_fetch_object($querry))
		{
			echo  $r->salt;
		}
	}
}else echo "error";

?>