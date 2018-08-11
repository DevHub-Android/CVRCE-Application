<?php

	include 'config.php';
	
	$userid  =  $_GET["userid"];
	$password = $_GET["password"];
	
	$sql = "select * from users where REGID = '$userid' and PASS = '$password'";
	
	$result = mysqli_query($conn,$sql);
	
	while($row = mysqli_fetch_assoc($result)){
		$json_array["users"] = $row;
	}
           
		   echo json_encode($json_array); 
 	

 ?>