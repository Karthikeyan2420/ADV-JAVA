<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="register" method="post" enctype="multipart/form-data">
        <input type="text" name="sname" id="sname" placeholder="Enter your name">
        <input type="number" name="age" id="age" min="0" max="100" placeholder="Age">
        <input type="file" name="photo" id="photo">
        <input type="date" name="dob" id="dob">
        <button type="submit">Register</button>
    </form>
</body>
</html>