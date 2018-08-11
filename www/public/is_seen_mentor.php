<?php
	include 'config.php';
	
	$complaint_id = $_GET["complaint_id"];
	
	$sql = "update complaints set mentor_seen = 1 where id = '$complaint_id'";
	
	mysqli_query($conn,$sql);

 ?>