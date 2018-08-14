<?php
	include 'config.php';
	
	$priority = $_GET["priority"];
	$complaint_id = $_GET["complaint_id"];
	
	$domain =  "";
	$position = "";
	$response = array();
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
	// $complaint_id ."   ".$position."   ";
	$sql3 = "update complaints set position_seen = '$position' where complaint_id = '$complaint_id'";
	
	
	if($conn->query($sql3))
	{
		$response['message']="Marked As Seen";
	}else {
		$response['message']="Something Went Wrong!";
	}
	echo json_encode($response);
	


 ?>