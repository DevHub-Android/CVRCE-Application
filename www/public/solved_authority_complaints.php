<?php
	require "config.php";
	$type= $_GET['type'];
	$response = array();
	//$sql = "select * from resolver where type=$type";
	$sql = "select * from resolver r inner join users u on(r.issued_by=u.REGID) where r.type='$type'";
	$result = mysqli_query($conn,$sql);
	if($result)
	{
		while($row = mysqli_fetch_assoc($result))
		{
			$response[]=$row;
		}
	}else {
		$response['error']= "ERROR : ".mysqli_error($conn);
	}
	$json_array['root']=$response;
	echo json_encode($json_array);



?>