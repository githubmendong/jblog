<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>

    <link rel='stylesheet' href='${pageContext.request.contextPath}/assets/css/jblog.css'>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/assets/css/search.css'>

</head>

<body>
<div class='center-content'>
    <h1 class='logo'>JBlog</h1>

    <ul class='menu'>
        <c:import url="/WEB-INF/views/includes/header.jsp"/>
        <!-- 입력창 HTML 코드 -->
        <div class='search-wrapper' action='https://search.naver.com/search.naver' method='GET'>
            <div class='input-holder'>
                <input type='submit' class="search-input" name='query' id='searchInput' placeholder='검색'/>
                <button class='search-icon' onclick='searchToggle(this, event);'><span></span></button>
            </div>
            <span class='close' onclick='searchToggle(this, event);'></span>
        </div>


    </ul>
    <!-- footer -->

    <footer></footer>


    <!-- footer -->


    <footer></footer>


    <!-- footer -->


    <footer></footer>


    <script src="//code.jquery.com/jquery.min.js"></script>


    <script type="text/javascript">
        function searchToggle(obj, evt) {
            var container = $(obj).closest('.search-wrapper');
            if (!container.hasClass('active')) {
                container.addClass('active');
                evt.preventDefault();
            } else if (container.hasClass('active') && $(obj).closest('.input-holder').length == 0) {
                container.removeClass('active');
                // clear input
                container.find('.search-input').val('');
            }
        }
    </script>


</body>




