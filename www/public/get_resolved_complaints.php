<?php
	include 'config.php';
	
	$mentor_id = $_GET["mentor_id"];
	
	
	
	$sql = "select * from resolver r inner join users u on(r.issued_by=u.REGID) where u.mentor_id='$mentor_id'";
	
	$result = mysqli_query($conn,$sql);
	
	$get_complaints = array();
	
	while($row = mysqli_fetch_assoc($result)){
		$get_complaints[] = $row;
	}
	
	$json_array["root"] = $get_complaints;
	
	echo(json_encode($json_array));
	
	



 ?>