<?php
	include 'config.php';
	
	$complaint_id = $_GET["complaint_id"];
	$description = $_GET["comment"];
	$created_at=date('Y-m-d');
	$user_id = $_GET["user_id"];
	
	$sql = "insert into comments(complaint_id,user_id,created_at,description) values('$complaint_id','$user_id','$created_at','$description')";
	
	mysqli_query($conn,$sql);
	
	
	
	


 ?>