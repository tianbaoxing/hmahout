<%@page import="org.apache.mahout.cf.taste.recommender.RecommendedItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta charset="utf-8"> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/mahout.css">
    <%
    List<RecommendedItem> list = ( List<RecommendedItem> )request.getAttribute("listRecommendedItem");
    %>
</head>
<body>
	<div id="wrap">
		<h3>推荐算法</h3>
		<form action="movie/userRecommenderMovieAction" method="get">
			<div>
				<label for="">电影</label>
				<label>评分</label>
			</div>
			<%if(list!=null){
				for(RecommendedItem item:list){%>
			<div>
				<label for=""><%=item.getItemID() %></label>
				<label> <%=item.getValue() %></label>
			</div>
			<%}} %>
		</form>
	</div>
</body>
</html>