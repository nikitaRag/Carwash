<?php
require "conn.php";
mysqli_set_charset($conn,"utf8");
if (isset ($_POST['client_id'],$_POST['appointment_id'],$_POST['review'],$_POST['rating'])){
	$client_id = $_POST['client_id'];
	$appointment_id = $_POST['appointment_id'];
	$review = $_POST['review'];
	$rating = $_POST['rating'];
	$querry1 = mysqli_query($conn,"INSERT INTO review (appointment_id, client_id, value, comment) VALUES ('$appointment_id', '$client_id','$rating', '$review')")or die(mysqli_error($conn));
}else{
	$client_id = $_POST['client_id'];
	$appointment_id = $_POST['appointment_id'];
	$querry1 = mysqli_query($conn,"INSERT INTO review (appointment_id, client_id, value, comment) VALUES ('$appointment_id', '$client_id','0', 'не захотел')")or die(mysqli_error($conn));
}
?>