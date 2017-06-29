<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/styleOkada.css" rel="stylesheet" type="text/css">
<link href="../css/styleTaiki.css" rel="stylesheet" type="text/css">
<link href="../css/styleKuniyoshi.css" rel="stylesheet" type="text/css">
<title><c:out value="${receive.userName}" />さんの備考</title>
</head>
<body>
	<h2><c:out value="${receive.userName}" />さんの備考</h2>
	<h4><c:out value="${receive.comment}" /></h4>
</body>
</html>