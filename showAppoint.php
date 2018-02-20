<?php
require "conn.php";

if (isset ($_POST['id'],$_POST['date'])){
	$id= $_POST['id'];
	$date= $_POST['date'];
	$querry = mysqli_query($conn,"SELECT appointment_id,appointment_time,appointment_date,interior_cleaning,diagnostics FROM appointment WHERE client_id='$id' AND appointment_date>='$date'");
while ($r = mysqli_fetch_object($querry))
{
	echo $r->appointment_id ." ".$r->appointment_time ." ".$r->appointment_date ." ". $r->interior_cleaning ." ". $r->diagnostics ." ";
}
}else echo "err";
?>