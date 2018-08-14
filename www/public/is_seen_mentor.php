<?php
	include 'config.php';
	
	$complaint_id = $_GET["complaint_id"];
	$response = array();
	$sql = "update complaints set mentor_seen = 1 where complaint_id = '$complaint_id'";
	
	if($conn->query($sql))
	{
		$response['message']="Marked As Seen!";
	}else {
		$response ['message']="ERROR:".$conn->error();
	}
	echo json_encode($response);
 ?>