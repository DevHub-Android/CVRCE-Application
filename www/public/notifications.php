<?php
	include 'config.php';
	
	
	
	$sql = "select * from notifications";
	
	$result = mysqli_query($conn,$sql);
	for($i=0;$i<mysqli_num_rows($result);$i++){
		$row = mysqli_fetch_assoc($result);
		$my_array[$i] = $row;
	}
           $json_array["notifications"] = $my_array; 
		   echo json_encode($json_array); 


 ?>