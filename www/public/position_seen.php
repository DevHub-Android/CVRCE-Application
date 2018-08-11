<?php
	include 'config.php';
	
	$priority = $_GET["priority"];
	$complaint_id = $_GET["complaint_id"];
	
	$domain =  "";
	$position = "";
	
	$sql1 = "select * from complaints where complaint_id = '$complaint_id'";
	$result = mysqli_query($conn,$sql1);
	
	while($rows = mysqli_fetch_assoc($result)){
		if($rows["type"]==0){
			$domain = "student welfare";
		}elseif($rows["type"]==1){
			$domain = "hostel";
		}
		else{
			$domain = "institute";
		}
	}
	
	$finalSQl = "select * from employee where domain = '$domain' and priority = '$priority'";
	$finalResult = mysqli_query($conn,$finalSQl);
	
	while($row = mysqli_fetch_assoc($finalResult)){
		$position = $row["position"];
	}
	
	$sql = "update complaints set position_seen = '$position' where id = '$complaint_id'";
	
	mysqli_query($conn,$sql);
	
	


 ?>