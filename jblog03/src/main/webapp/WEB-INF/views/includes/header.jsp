<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1><a href="${pageContext.request.contextPath}/${blogVo.blogId}">${blogVo.title}</a></h1>
<ul class="menu">
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/">메인으로 이동하기</a></li>
		</c:when>
		<c:otherwise>
			<li>${authUser.name } 님 반갑습니다.</li>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/${blogVo.blogId }/admin/basic">블로그 관리</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }">내블로그</a></li>
			<li><a href="${pageContext.request.contextPath}/">메인 이동하기</a></li>
		</c:otherwise>
	</c:choose>
</ul>
