<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>version1 실습과제</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<h2 style="margin:100px 0 50px 0; text-align: center;">ajax Test</h2>
		<div class="btn-group" style="width:300px;">
			<button type="button" class="btn btn-primary" onclick="first()">처음으로</button>
			<button type="button" class="btn btn-primary" onclick="price()">가격순</button>
			<button type="button" class="btn btn-primary" onclick="count()">판매순</button>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>종류</th>
					<th>가격</th>
					<th>판매수</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="first">
				<c:forEach var="product" items="${products}">
					<tr id="product-${product.id}">
						<td>${product.id}</td>
						<td>${product.name}</td>
						<td>${product.type}</td>
						<td>${product.price}</td>
						<td>${product.count}</td>
						<td onclick="deleteBtn(${product.id})"><i class="large material-icons" style="cursor:pointer;">delete</i></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
	</div>
	<script>
		function first(){
			location.href="/ajaxTest/product?cmd=home";
		}

		
		
	
		function price(){
			$.ajax({
				type:"get",
				url:"/ajaxTest/product?cmd=price", //무조건 넣어야 할것!
				dataType:"json"
			}).done(function(result){ //products이 json형식으로 담김
				$("#first").empty();

				// result의 product 가져오기 (forEach)
				for(var product of result) {

					var string = // 다른 곳 java파일에 ""안에 붙여넣으면 자동으로 string화 		
						"					<tr id=\"product-"+product.id+"\">\r\n" + 
						"						<td>"+product.id+"</td>\r\n" + 
						"						<td>"+product.name+"</td>\r\n" + 
						"						<td>"+product.type+"</td>\r\n" + 
						"						<td>"+product.price+"</td>\r\n" + 
						"						<td>"+product.count+"</td>\r\n" + 
						"						<td onclick=\"deleteBtn("+product.id+")\"><i class=\"large material-icons\" style=\"cursor:pointer;\">delete</i></td>"+
						"					</tr>";
						$("#first").append(string);					

				}
				
			}).fail(function(error){
				alert(error);
			});
		}

		
		
		
		function count(){
			$.ajax({
				type:"get",
				url:"/ajaxTest/product?cmd=count", //무조건 넣어야 할것!
				dataType:"json"
			}).done(function(result){ //products이 json형식으로 담김
				$("#first").empty();

				// result의 product 가져오기 (forEach)
				for(var product of result) {

					var string = // 다른 곳 java파일에 ""안에 붙여넣으면 자동으로 string화 		
						"					<tr id=\"product-"+product.id+"\">\r\n" + 
						"						<td>"+product.id+"</td>\r\n" + 
						"						<td>"+product.name+"</td>\r\n" + 
						"						<td>"+product.type+"</td>\r\n" + 
						"						<td>"+product.price+"</td>\r\n" + 
						"						<td>"+product.count+"</td>\r\n" + 
						"						<td onclick=\"deleteBtn("+product.id+")\"><i class=\"large material-icons\" style=\"cursor:pointer;\">delete</i></td>"+
						"					</tr>";
						$("#first").append(string);					
					
				}
				
			}).fail(function(error){
				alert(error);
			});
		}
		
		function deleteBtn(id) {
			$.ajax({
				type: "get",
				url: "/ajaxTest/product?cmd=delete&id="+id,
				dataType:"json"
			}).done(function(result) {
				if(result==1){
					$("#product-"+id).remove();
					alert("삭제성공");	
				}else{
					alert("삭제실패")
				}
	
					
			}).fail(function(error) {
				alert("삭제실패")
			})
		}
		
		
	</script>

</body>
</html>