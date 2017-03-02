<?php
$Name=$_POST["name"];
$Email=$_POST["email"];
$Message=$_POST["message"];
$servername ="localhost";
$username ="root";
$password ="";
$dbname ="androidapp";


// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO contactus (name,email,message)
VALUES ('$Name','$Email','$Message')";

if (mysqli_query($conn, $sql)) {
    header("Location:intouch.php");
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>