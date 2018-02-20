<?php
require "conn.php";

if (isset ($_POST['id'])){
	$id= $_POST['id'];
	$querry = mysqli_query($conn,"DELETE FROM appointment WHERE appointment_id='$id'");

}else echo "err";
?>