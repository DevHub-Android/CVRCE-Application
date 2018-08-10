<?php
	include 'config.php';
	
	$sql = "select * from hostel_complaints";
	
	$result = mysqli_query($conn,$sql);
	
	$priority = 2;
	
	
	while($row = mysqli_fetch_assoc($result)){
		
		$c_id  =  $row["complaint_id"];
		
		$result1 = mysqli_query($conn,"select * from complaints where complaint_id='$c_id'");
		
		while($rows = mysqli_fetch_assoc($result1)){
			$start = strtotime(date('y-m-d'));
			$end = strtotime($rows["created_at"]);
			$days_between = ceil(abs($end - $start) / 86400);
			
			if($days_between>2){
				$sql1 = "update complaints set priority = $priority where complaint_id = '$c_id'";
				mysqli_query($conn,$sql1);
			}
		}
		
		
	}
	
	$array_json["success"] = true;
	echo(json_encode($array_json));

 ?>