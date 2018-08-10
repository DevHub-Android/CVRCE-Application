<?php
 include 'config.php';
 
 $user_id = $_GET["user_id"];
 $mentor_id = $_GET["mentor_id"];
 
 $sql = "update users set mentor_id = '$mentor_id' where REGID = '$user_id'";
 
 mysqli_query($conn,$sql);

 ?>