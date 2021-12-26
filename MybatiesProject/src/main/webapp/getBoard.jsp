<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%
	// 	String sts = "";
// if (!session.getAttribute("userName").equals(session.getAttribute("boardWriter"))
// 		&& !session.getAttribute("userRole").equals("관리자")) {
// 	sts = "disabled";
// Object stsb = session.getAttribute("stsb");
// out.println(session.getAttribute("stsb") + ", " + stsb);
// out.println(stsb);
// 	if (adminLo.equals("관리자")) {
// 		sts = "disabled";
// }
%>
<body>
	<div class="jumbotron">
		<h1>상세 보기</h1>
	</div>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">
		<form name="fm" action="updateBoard.do" method="post">
			<input type="hidden" value="${board.seq}">
			
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">No.</span>
				</div>
				<input type="text" class="form-control innm" name="seq"
					value="${board.seq}" readonly>
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">제목</span>
				</div>
				<input type="text" class="form-control innm" name="title"
					value="${board.title}">
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">작성자</span>
				</div>
				<input type="text" class="form-control innm" name="writer"
					value="${board.writer}" readonly>
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">내용</span>
				</div>
				<textarea class="form-control innm" rows="10" id="comment"
					name="content">${board.content}</textarea>
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">등록일</span>
				</div>
				<input type="text" class="form-control innm" name="regDate"
					value="${board.regdate}" readonly>
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">조회수</span>
				</div>
				<input type="text" class="form-control innm" name="cnt"
					value="${board.cnt}" readonly>
			</div>
			<div id="footer">
				<button type="submit" class="btn btn-primary">글수정</button>
				<button id="conWrite" type="button" class="btn btn-primary">글쓰기</button>
				<button id="conDel" type="button" class="btn btn-primary">글삭제</button>
				<button id="conList" type="button" class="btn btn-primary">글목록</button>
			</div>
		</form>
	</div>


</body>
</html>
