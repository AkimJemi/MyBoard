<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<style>
#searchNav {
	-webkit-justify-content: flex-end;
	justify-content: flex-start;
}
a{
font-size: 20px;
}
a:clicked {
font-size: 40px;
}
</style>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="getBoardList.do?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<body>
	<div class="jumbotron">
		<h1>공지 사항</h1>
		<button type="button" onclick="board('')" class="btn btn-primary" >기존 게시판</button>
		<button type="button" onclick="board('a')" class="btn btn-primary" >A 게시판</button>
		<button type="button" onclick="board('b')" class="btn btn-primary" >B 게시판</button>
		<button type="button" onclick="board('c')" class="btn btn-primary" >C 게시판</button>
	</div>
	<%@ include file="menu.jsp"%>
	<nav id="searchNav" class="navbar navbar-expand-sm navbar-dark">
		<div class='alert alert-success'>
			<%
			out.println("userId:" + session.getAttribute("userId"));
			out.println("userPassword:" + session.getAttribute("userPassword"));
			out.println("userName:" + session.getAttribute("userName"));
			out.println("userRole:" + session.getAttribute("userRole"));
			%>
		</div>
		<form class="form-inline" action="getBoardList.do" method="post">
			<select class="form-control" id="sel1" name="searchCondition"
				style="display: inline-block !important; margin-right: 10px;">
				<option value="TITLE">제목</option>
				<option value="CONTENT">내용</option>
			</select><input class="form-control mr-sm-2" type="text" name="searchKeyword"
				placeholder="검색어를 입력하세요.">
			<button class="btn btn-success" type="submit">검색</button>
		</form>
		<button type="button" id="delAll"
			class="btn btn-primary">[ 전체 삭제 ]</button>
		<button type="button" onclick="autoIns(1)" class="btn btn-primary" >자동 추가x1</button>
		<button type="button" onclick="autoIns(10)" class="btn btn-primary" >자동 추가x10</button>
		<button type="button" onclick="autoIns(20)" class="btn btn-primary" >자동 추가x20</button>
		<button type="button" onclick="autoIns(40)" class="btn btn-primary">자동 추가x40</button>
		<button type="button" onclick="autoIns(1000)"class="btn btn-primary">자동 추가x1000</button>
		<button type="button" onclick="autoDel(10)" class="btn btn-primary">자동 삭제x10</button>
		<button type="button" onclick="autoDel(20)" class="btn btn-primary">자동 삭제x20</button>
		<button type="button" onclick="autoDel(40)" class="btn btn-primary">자동 삭제x40</button>
		<div style="float: right;">
			<select id="cntPerPage" onchange="selChange()">
				<option value="5"
					<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄
					보기</option>
				<option value="10"
					<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄
					보기</option>
				<option value="15"
					<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄
					보기</option>
				<option value="20"
					<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄
					보기</option>
			</select>
		</div>
		<!-- 옵션선택 끝 -->
	</nav>
	<div class="container-fluid">
		<table class="table table-hover">
			<thead class="btn-primary">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${viewAll}" var="list">
					<tr onclick="selTr(${list.seq})" style="cursor: pointer;">
						<td class="tdCenter">${list.seq}</td>
						<td class="tdCenter">${list.title}</td>
						<td class="tdCenter">${list.content}</td>
						<td class="tdCenter">${list.writer}</td>
						<td class="tdCenter">${list.regdate}</td>
						<td class="tdCenter">${list.cnt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br> <br>
		<div class='alert alert-success'>
			<a> startPage : ${paging.startPage}, &nbsp</a>
			<a> endPage : ${paging.endPage}, &nbsp</a>
			<a> start : ${paging.start}, &nbsp</a>
			<a> end : ${paging.end}, &nbsp</a>
			<a> cntPerPage : ${paging.cntPerPage}, &nbsp</a>
			<a> nowPage : ${paging.nowPage} &nbsp</a>
			<a> lastPage : ${paging.lastPage}, &nbsp</a>
			<a> total : ${paging.total}, &nbsp</a>
		</div>
		
		<div id="footer">
			<button type="button" id="conWrite" class="btn btn-primary">글쓰기</button>
		</div>
		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a href="getBoardList.do?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}">[</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage }">
						<b>${p}</b>
					</c:when>
					<c:when test="${p!= paging.nowPage }">
						<a href="getBoardList.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="getBoardList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">]</a>
			</c:if>
		</div>
	</div>
</body>
