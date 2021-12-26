<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="header.jsp"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
h2, table {
	text-align: center;
	margin: auto;
}

table {
	border-collapse: collapse;
	border: 1px solid #444;
	width: 60%;
}

th, td {
	border: 1px solid #444;
}

th {
	background-color: #666;
	color: #fff;
}
</style>
<script>
	function getBoardListShow(val) {
		var objParams = {
			seq : val
		};
		var values = []; //ArrayList 값을 받을 변수를 선언

		$.ajax({
			method : "POST", //"POST"
			url : "reqAjax2.do",
			data : objParams,
			success : function(data) {
				if (data.code == "OK") { //controller에서 넘겨준 성공여부 코드
// 					if(a=2) return;
					values = data.boardList; //java에서 정의한 ArrayList명을 적어준다.
					$.each(values, function(index, value) {
						console.log(index + " : " + value.title); //Book.java 의 변수명을 써주면 된다.
						$("#status").append(
								"<tr><td>" + value.seq + "</td><td>"
										+ value.title + "</td><td>"
										+ value.writer + "</td></tr>");
// 						String a= 2;
					});
					// 					$("#demo").hide();
					console.log("성공");
				} else {
					console.log("실패");
				}
			},
			error : function(request, status) {
				alert("오류가 발생했습니다.");
			}
		});
	}
	// 	function getBoardHide(var) {
	// 		$("#status").hide();
	// 	}
</script>
<body>
	<%!String greeting = "환영합니다";
	String tagline = "Welcome to Gesipan!";%>
	<div class="jumbotron"
		style="background-color: #ccc !important; text-align: center; padding-bottom: 30px !important">
		<div class="container">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
			<h3>
				<%=tagline%>
			</h3>
		</div>
	</div>

	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="text-center">
			<%
				Date day = new java.util.Date();
			String am_pm;
			int hour = day.getHours();
			int minute = day.getMinutes();
			int second = day.getSeconds();
			if (hour / 12 == 0) {
				am_pm = "AM";
			} else {
				am_pm = "PM";
				hour = hour - 12;
			}
			String CT = hour + ":" + minute + ":" + second + " " + am_pm;
			out.println("현재 접속  시각: " + CT + "\n");
			%>
		</div>
		<hr>
	</div>
	<h2>ajax Object 받기</h2>
	<table id="status">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	</table>
	<br>
	<div id="demo">
		<button type="button" onclick="getBoardListShow(1)">내보이기</button>
		<button type="button" onclick="getBoardListHide(1)">숨기기</button>
	</div>


</body>
