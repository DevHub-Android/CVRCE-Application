<?php
 include 'config.php';
 
 $user_id = $_GET["user_id"];
 $mentor_id = $_GET["mentor_id"];
  $response = array();
 $sql = "update users set mentor_id = '$mentor_id' where REGID = '$user_id'";
  $result = mysqli_query($conn,$sql);
 if($result){
	// echo "Comming here!";
	 $response['msg']="Student Added Succesfully!!";
	 echo json_encode($response);
 }else {
	// echo "Comming here ELSE!";
	 $response['msg']= "ERROR :".mysqli_error($conn);
	 echo json_encode($response);
 }


 ?>