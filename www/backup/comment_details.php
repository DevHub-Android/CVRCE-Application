<?php
	include 'config.php';
	
	$comp_id = $_GET["complaint_id"];
	
	$sql = "select * from comments where complaint_id = '$comp_id'";
	
	$result = mysqli_query($conn,$sql);
	
	$uid = 0;
	$array_u = array();
	$array_c = array();
	while($row = mysqli_fetch_assoc($result)){
		
		
		
		$uid = $row["user_id"];
		
		$sql1 = "select * from users where REGID = '$uid'";
		
		$resultC = mysqli_query($conn,$sql1);
		
		while($rows = mysqli_fetch_assoc($resultC)){
			$array_u[] = $rows;
		}
		
		$array_c[] = $row;
		
	
	}
	
	
	$json_array["comments"] = $array_c;
	$json_array2["users"] = $array_u;
	
	$solveArray["comments"] = $json_array["comments"];
	$solveArray["users"] = $json_array2["users"];	
	
	$root_array["root"] = $solveArray;
	echo json_encode($root_array);

 ?>