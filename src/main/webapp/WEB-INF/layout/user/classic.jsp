<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- <link rel="stylesheet"  href="/resources/css/style.css"> -->

<style type="text/css">
body {
	padding-bottom: 0px; 
	padding-top: 70px;
}

*{
	margin:0;
	padding:0;
}
.ShopNow:hover{
	background-color:white;
	text-decoration:none;
	color: black;
}
.ShopNow{
	background-color:black;
	float:right;
	display:block;
	margin-right:220px;
	margin-top:7px;
	 border: 3px solid #fff;
    padding: 12px;
	font-size:14pt; 
	text-decoration:none;
	color:white;
}
.modClick:hover{
	background-color:black;
	text-decoration:none;
	color: white;
}
.modClick{
	background-color:white;
	float:right;
	display:block;
	margin-top:0px;
	 border: 3px solid black;
    padding: 12px;
	font-size:14pt; 
	text-decoration:none;
	color:black;
}
.shadows:hover{
	background-color:white;
	
}
.shadows{
	background-color:black;
	height:400px;
	width:500px;
	
}
.mainAdv{
	margin-top:-25px;
	
}
.bodyCat{
	margin-top:-25px;
	padding-left:10px;
}
.mainSpace{
	margin-bottom:50px;
}
.mainFreedom{
	margin-top:300px;
	margin-left:50px;
}
.steamMachine{
	margin-left:450px;
	margin-top:-500px;
	margin-bottom:-150px;
}
.buttonn{
	margin-left:-110px;
	
}
.botText{
	margin-left:300px;
	margin-right:300px;
}

.imagesCrop{
	width: 10%;
	height: 100%;
}

.backgroundstyle{
	/* background-image:url(https://store.storeimages.cdn-apple.com/4974/as-images.apple.com/is/image/AppleInc/aos/published/images/m/bp/mbp15ex/gallery1/mbp15ex-gallery1-201610?wid=2000&hei=1536&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=1476384157498);
	 */backgroung-image:url(img/11.jpg);
	
	background-attachment:fixed;
	margin-bottom: 20px;
	margin-top: 0px;
}
.regText{
	color:white;
}

</style>
<!-- сюди буде підставлено атрибут з ім'ям title -->
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<!-- 	а сюди jsp файл з ім'ям header -->
    <tiles:insertAttribute name="header" />
	<div class="container-fluid">
<!-- 	сюди jsp файл з ім'ям body -->
		<tiles:insertAttribute name="body" />
	</div>
<!-- 	сюди jsp файл з ім'ям footer -->
		<tiles:insertAttribute name="footer" />
<!-- 	зверніть увагу на те що в темплейт файлі (тут) -->
<!-- 	вже описано основний HTML код, в інших файлах -->
<!-- 	його писати не потрібно(я про <head><body><html>) -->
</body>
</html>