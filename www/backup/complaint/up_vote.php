<?php
	include 'config.php';
	
	$complaint_id = $_GET["complaint_id"];
	$user_id = $_GET["user_id"];
	
	$sql1="insert into check_if_vote(user_id,complaint_id) values ('$user_id','$complaint_id')";
	
	mysqli_query($conn,$sql1);

	$sql = "update complaints set up_vote = up_vote+1 where complaint_id = '$complaint_id'";
	
	mysqli_query($conn,$sql);
	
?>