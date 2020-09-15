<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 세션값 초기화 --%>
<% session.invalidate();%>

<%
Cookie[] cookies = request.getCookies();
 if (cookies != null) {
	 for (Cookie cookie : cookies) { 
		if(cookie.getName().equals("id")) {
			cookie.setMaxAge(0); // 유효기간 0 설정
			cookie.setPath("/"); // 경로설정 path는 로그인 로그아웃 둘다 추가 
			response.addCookie(cookie);
		}
	 }
 }
%>

<%-- "로그아웃됨" index.jsp로이동 --%>
<script>
	alert('로그아웃됨');
	location.href='/index.jsp'; // '../index.jsp'
</script>

