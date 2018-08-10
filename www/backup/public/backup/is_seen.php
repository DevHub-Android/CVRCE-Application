<?php
	include 'config.php';
	
	$notification_id = $_GET["notification_id"];
	
	$sql = "update notifications set is_seen = 1 where id = '$notification_id'";
	
	mysqli_query($conn,$sql);
	

 ?>