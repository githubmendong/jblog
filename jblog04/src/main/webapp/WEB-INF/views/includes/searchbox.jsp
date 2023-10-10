<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<ul class="menu">
	<div class="search-wrapper">
		<div class="input-holder">
			<input type="text" class="search-input" placeholder="Type to search" />
			<button class="search-icon" onclick=searchToggle(this, event);><span></span></button>
		</div>
		<span class='close' onclick=searchToggle(this, event);></span>
	</div>
</ul>
