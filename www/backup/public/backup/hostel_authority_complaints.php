<?php
	include 'config.php';
	
	$priority = $_GET["priority"];
	
	$sql = "select * from hostel_complaints";
	
	$result = mysqli_query($conn,$sql);
	
	$uid = 0;
	$array_u=null;
	while($row = mysqli_fetch_assoc($result)){
		
		$c_id  =  $row["complaint_id"];
		
		
		$sql1 = "select * from complaints where complaint_id = '$c_id' and priority='$priority'";
		
		$resultC = mysqli_query($conn,$sql1);
		
		while($rows = mysqli_fetch_assoc($resultC)){
			$array_yo[] = $rows;
			
			$uid = $rows["user_id"];
			
			$sql3 = "select * from users where REGID = '$uid'";
	
			$result3 = mysqli_query($conn,$sql3);
		
	
			while($myRows = mysqli_fetch_assoc($result3)){
			$array_u[] = $myRows;
			}
		}
		
	}
	
	
	
	
	
	$json_array["complaints"] = $array_yo;
	$json_array2["users"] = $array_u;
	
	$solveArray["complaints"] = $json_array["complaints"];
	$solveArray["users"] = $json_array2["users"];	
	
	$root_array["root"] = $solveArray;
	echo json_encode($root_array);
	
	
		    


 ?>