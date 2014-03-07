<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/mahout.css">
    <% 
    String rooPath = request.getContextPath();
    %>
</head>
<body>
	<div id="wrap">
		<h3>推荐算法</h3>
		<form action="<%=rooPath %>/movie/userBaseRecommenderMovieAction" method="get">
			<div>
				<label for="">推荐用户：</label>
				<input type="text" name="userid" class="form-input required" value="">
			</div>
			<div>
				<label for="">推荐电影个数：</label>
				<input type="text" name="item_num" class="form-input required number" value="">
			</div>
			<div>
			</div>
			<div id="btn-group">
				<input type="reset" class="btn">
				<input type="submit" class="btn">
			</div>
		</form>
		<div id="recommder_movie">
		
		</div>
		<div id="Alarm">
		
		</div>
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/recommder_movie.js"></script>
</body>
</html>