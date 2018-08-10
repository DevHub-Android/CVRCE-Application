<?php
	include 'config.php';
	$start = strtotime(date('y-m-d'));
	
	
	
	
	while($row = mysqli_fetch_assoc($result)){
		$end = strtotime($row["created_at"]);
		$days_between = ceil(abs($end - $start) / 86400);

		echo $days_between;
	}



 ?>