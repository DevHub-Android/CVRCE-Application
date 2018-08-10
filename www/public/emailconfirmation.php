<?php
	require "config.php";
	$username = $_GET["username"];
	$confirmcode = $_GET["confirmcode"];
	//echo $username." ".$confirmcode;
	$query = mysqli_query($conn,"SELECT * FROM USERS WHERE regid=$username");
	$row = mysqli_fetch_assoc($query);
	$db_code = $row['CONFIRM_CODE'];
	//echo $db_code;
	if($confirmcode==$db_code)
	{
		//$sql = "UDPATE susers SET COFIRMED=1";

		mysqli_query($conn,"UPDATE users SET CONFIRMED=1 where regid=$username");
		mysqli_query($conn,"UPDATE users SET CONFIRM_CODE=0 where regid=$username");
		echo "Registration confirmed!";
		
	}else{
		echo "Confirm code error";
	}

?>