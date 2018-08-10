<?php
	include 'config.php';
	
	$complaint_id = $_GET["complaint_id"];
	$user_id = $_GET["user_id"];
	
	$sql = "select * from check_if_vote where user_id='$user_id' and complaint_id='$complaint_id'";
	
	$result = mysqli_query($conn,$sql);
	
	
	if(mysqli_num_rows($result)==1){
		$array_success["success"] = true;
		
	}else{
		$array_success["success"] = false;
	}
	
	echo(json_encode($array_success));
	
	

 ?>