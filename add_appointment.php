<?php
require "conn.php";
if(isset($_POST['client_id'],$_POST['time'],$_POST['date'],$_POST['car_type'],$_POST['i_cleaning'],$_POST['diagnostocs'],$_POST['price'])){
	$client_id = $_POST['client_id'];
	$time = $_POST['time'];
	$date = $_POST['date'];
	$car_type = $_POST['car_type'];
	$i_cleaning = $_POST['i_cleaning'];
	$diagnostocs = $_POST['diagnostocs'];
	$price = $_POST['price'];
	
	$querry = mysqli_query($conn,"INSERT INTO appointment (client_id,appointment_time,appointment_date,box_number,car_type,interior_cleaning,diagnostics,price) VALUES ('$client_id', '$time', '$date',1, '$car_type', '$i_cleaning', '$diagnostocs', '$price')") or die(mysqli_error($conn));
	echo "succes";
}else echo "not succes";
?>