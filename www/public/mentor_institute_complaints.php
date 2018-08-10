<?php
	include 'config.php';
	
	$mentor_id = $_GET["mentor_id"];
	
	$uid = 0;
	$array_u=null;
	$array_yo = null;
	$sql = "select * from users where mentor_id='$mentor_id'";
	$result = mysqli_query($conn,$sql);
	if($result)
	{
		while($row = mysqli_fetch_assoc($result))
		{
			$uid = $row['REGID'];
			//echo "UID".$uid;
			$sql1 = "select * from institute_complaints where user_id='$uid'";
			$result1 = mysqli_query($conn,$sql1);
			if($result1){
				while($row1=mysqli_fetch_assoc($result1))
				{
					$c_id = $row1['complaint_id'];
					//echo "COMPLAINT ID :".$c_id;
					$sql2 = "select * from complaints where complaint_id = '$c_id'";
					$result2=mysqli_query($conn,$sql2);
					while($row2=mysqli_fetch_assoc($result2))
					{
						$array_yo[]=$row2;
					}
					
				}
			}else {
				echo "ERROR RESULT 1". mysqli_error($conn);
			}
			
					$sql3 = "select * from users where regid=$uid and mentor_id=$mentor_id";
					$result3 = mysqli_query($conn,$sql3);
					if($result3)
					{	//echo "Comming in here";
						while($row3=mysqli_fetch_assoc($result3))
						{
							$array_u[] = $row3;
						}
					}else{
						echo "ERROR RESULT 1". mysqli_error($conn);
					}
		}
	}else{
		echo "ERROR RESULT 1". mysqli_error($conn);
	}
	
	$json_array["complaints"] = $array_yo;
	$json_array2["users"] = $array_u;
	
	$solveArray["complaints"] = $json_array["complaints"];
	$solveArray["users"] = $json_array2["users"];	
	
	$root_array["root"] = $solveArray;
	echo json_encode($root_array);
	
	
		    


 ?>