<?php
	include 'config.php';
	
	$priority = $_GET["priority"];
	$type = $_GET["type"];
	
	//$sql = "select * from hostel_complaints";
	
	$uid = 0;
	$array_u=null;
	$array_yo = null;
	$sql = "select * from complaints where priority='$priority' and type=$type";
	$result = mysqli_query($conn,$sql);
	while($rows = mysqli_fetch_assoc($result)){
			$array_yo[] = $rows;
			
			$uid = $rows["user_id"];
			
			$sql3 = "select * from users where REGID = '$uid'";
	
			$result3 = mysqli_query($conn,$sql3);
		
	
			while($myRows = mysqli_fetch_assoc($result3)){
			$array_u[] = $myRows;
			}
		}
	$json_array["complaints"] = $array_yo;
	$json_array2["users"] = $array_u;
	
	$solveArray["complaints"] = $json_array["complaints"];
	$solveArray["users"] = $json_array2["users"];	
	
	$root_array["root"] = $solveArray;
	echo json_encode($root_array);
	
	
		    


 ?>