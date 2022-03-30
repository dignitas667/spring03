<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<caption>게시물 리스트</caption>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
<c:forEach items="${list}" var="dto">
<tr>
	<td>${dto.no}</td>
	<td><a href="detail?no=${dto.no}">${dto.title}</a></td>
	<td>${dto.name}</td>
	<td><fmt:formatDate value="${dto.regdate}" type="date" /></td>
	<td>${dto.readcount}</td>
</tr>
</c:forEach>
<tr>
	<td colspan="5">
		<c:forEach begin="1" end="${pageCount}" var="p">
			<a href="list?pg=${p}">${p}</a>
		</c:forEach>
	</td>
</tr>
</table><br/>
<a href="insert">글쓰기</a>
</body>
</html>