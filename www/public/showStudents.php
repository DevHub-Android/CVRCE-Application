<?php
	
	include 'config.php';
	$mentor_id = $_GET["mentor_id"];
	
	$sql = "select * from users where mentor_id = '$mentor_id'";
	$result = mysqli_query($conn,$sql);
	
	$users_array = array();
	while($row = mysqli_fetch_assoc($result)){
		$users_array[] = $row;
	}
	
	$json_array["root"] = $users_array;
	
	echo(json_encode($json_array));


 ?>