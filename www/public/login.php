<?php

	include 'config.php';
	
	$userid  =  $_GET["userid"];
	$password = md5($_GET["password"]);
	$json_array = array();
	$sql = "select * from users where REGID = '$userid' and PASS = '$password'";
	
	$result = mysqli_query($conn,$sql);
	if($result){
		
		while($row = mysqli_fetch_assoc($result)){
		//echo "ROW". $row['username'];
		$json_array['users'] = $row;
	}
           
	echo json_encode($json_array); 
	}else {
		echo "ERROR:". mysqli_error($conn);
	}
	
 	

 ?>