<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>version2 실습과제</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2 style="margin: 100px 0 50px 0; text-align: center;">ajax Test2</h2>
		<div class="row">
			<div class="col-md-3">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2" style="text-align: center;">TeamList</th>
						</tr>
					</thead>
					<thead>
						<tr style="text-align: center;">
							<th>번호</th>
							<th>팀목록</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="teamList" items="${teamLists}">
							<tr style="cursor: pointer;" onclick="findPlayerList('${teamList.teamName}')">
								<td>${teamList.id}</td>
								<td>${teamList.teamName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


			<div class="col-md-3">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2" style="text-align: center;">PlayersList</th>
						</tr>
					</thead>
					<thead>
						<tr style="text-align: center;">
							<th>선수번호</th>
							<th>선수이름</th>
						</tr>
					</thead>
					<tbody id="findPlayerList">
					</tbody>
				</table>
			</div>


			<div class="col-md-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="3" style="text-align: center;">Player</th>
						</tr>
					</thead>
					<thead>
						<tr style="text-align: center;">
							<th>번호</th>
							<th>팀목록</th>
							<th>포지션</th>
						</tr>
					</thead>
					<tbody id="findPlayer">
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
		function findPlayerList(teamName) {
			$.ajax({
				type:"get",
				url:"/ajaxTest2/baseBall?cmd=findPlayerList&playerTeam="+teamName, //무조건 넣어야 할것!
				dataType:"json"
			}).done(function(result){ //products이 json형식으로 담김
				
				console.log(result);
				$("#findPlayerList").empty();

				// result의 product 가져오기 (forEach)
				for(var players of result) {

					var string = // 다른 곳 java파일에 ""안에 붙여넣으면 자동으로 string화 		
						"					<tr style=\"cursor: pointer;\" onclick=\"findPlayer('"+players.playerName+"','"+teamName+"')\">\r\n" + 
						"						<td>"+players.id+"</td>\r\n" + 
						"						<td>"+players.playerName+"</td>\r\n" + 
						"					</tr>";
						$("#findPlayerList").append(string);		
			
				}
				$("#findPlayer").empty();
			}).fail(function(error){
				alert("실패");
			});
		}
		
		
		function findPlayer(playerName,playerTeam) {
			$.ajax({
				type:"get",
				url:"/ajaxTest2/baseBall?cmd=findPlayer&playerName="+playerName+"&playerTeam="+playerTeam, //무조건 넣어야 할것!
				dataType:"json"
			}).done(function(result){ //products이 json형식으로 담김
				
				console.log(result)
				$("#findPlayer").empty();

				// result의 product 가져오기 (forEach)
				for(var players of result) {

					var string = // 다른 곳 java파일에 ""안에 붙여넣으면 자동으로 string화 		
						"					<tr>\r\n" + 
						"						<td>"+players.id+"</td>\r\n" + 
						"						<td>"+players.playerName+"</td>\r\n" + 
						"						<td>"+players.playerPosition+"</td>\r\n" + 
						"					</tr>";
						$("#findPlayer").append(string);		

				}
			}).fail(function(error){
				alert("실패");
			});
		}
	</script>
</body>
</html>