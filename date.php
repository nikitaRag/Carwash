<?php
require "conn.php";

if(isset($_POST['date'])){
$date = $_POST['date'];
$querry = mysqli_query($conn,"SELECT appointment_time FROM appointment where appointment_date = '$date'") or die(mysqli_error($conn));
}
	else echo "date = null";

while ($r = mysqli_fetch_object($querry))
{
	echo $r->appointment_time ." ";
}
?>