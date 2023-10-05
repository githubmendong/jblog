<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath }/${blogVo.blog_id }"
			style="color: white;"> ${blogTitle } </a>
	</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			</c:when>

			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:choose>
					<c:when test="${authUser.id != blogId}">
						<!-- 다른 사람이 블로그를 방문할 경우 내블로그로 가기를 위한 새로운 when 조건을 추가 -->
						<li><a href="${pageContext.request.contextPath}/${authUser.id }">내 블로그</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그
								관리</a></li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>

	</ul>
</div>