<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<form:form action="login" name="loginForm" id="loginForm" method="post">
    <div class="rows"><span class="base_tit">account</span><input type="text" name="username" id="username" class="input_text" maxlength="20"/><div class="div_tishi"></div></div>
    <div class="rows"><span class="base_tit">password</span><input type="password" name="password" id="password" class="input_text" maxlength="20"/><div class="div_tishi"></div></div>
    <p class="btns"><input type="submit" class="btn_submit" value="submit" /></p>
</form:form>
</body>
</html>
