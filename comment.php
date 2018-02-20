<?php
require "conn.php";

if (isset ($_POST['client_id'],$_POST['date'],$_POST['time'])){
	$client_id= $_POST['client_id'];
	$date= $_POST['date'];
	$time= $_POST['time'];
	$querry = mysqli_query($conn,"SELECT appointment_id, appointment_time, appointment_date FROM appointment WHERE appointment_time=(SELECT MAX(appointment_time) FROM appointment WHERE appointment_date =(SELECT MAX(appointment_date) FROM appointment WHERE client_id='$client_id')) AND appointment_date =(SELECT MAX(appointment_date) FROM appointment WHERE client_id='$client_id')");
	if (mysqli_num_rows($querry)>0){
		while ($r = mysqli_fetch_object($querry)){
			$app_id = $r->appointment_id;
			$app_time = $r->appointment_time;
			$app_date = $r->appointment_date;
		}
		if ($app_date <= $date && $app_time<=$time){
			$querry2 = mysqli_query($conn,"SELECT * FROM review WHERE appointment_id = '$app_id'");
			if (mysqli_num_rows($querry2)==0){
				echo $app_id;
			}else echo "0";
		}else echo "0";
			
	}else echo "0";
}else echo "err";
?>