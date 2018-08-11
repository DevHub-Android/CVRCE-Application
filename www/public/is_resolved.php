<?php
	include 'config.php';
	
	$title = $_GET["title"];
	$description = $_GET["description"];
	$created_at = $_GET["created_at"];
	$complaint_id = $_GET["complaint_id"];
	$priority = $_GET["priority"];
	$domain = "";
	$solved_by = "";
	
	$date = date('Y-m-d');
	
	
	mysqli_query($conn,"delete from complaints where complaint_id = '$complaint_id'");
	mysqli_query($conn,"delete from hostel_complaints where complaint_id = '$complaint_id'");
	mysqli_query($conn,"delete from institute_complaints where complaint_id = '$complaint_id'");
	mysqli_query($conn,"delete from user_complaints where complaint_id = '$complaint_id'");
	
	
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
		$solved_by = $row["position"];
	}
	
	
	$sql = "insert into resolver(complaint_id,created_at,solved_at,description,title) values('$complaint_id','$created_at','$date','$description','$title')";
	mysqli_query($conn,$sql);




	



 ?>