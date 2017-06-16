<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>予約管理</title>
</head>
<body>
	<p>予約管理</p>
	<p><a href="./manage">管理画面</a></p>
	<table>
		<tr><th>予約者</th><th>本</th><th>本の名前</th><th>受取図書館</th><th>貸し出し予定日</th><th>本の状態</th></tr>
		<c:forEach items="${reservations}" var="reservation">
			<tr>
				<td>${reservation.userId}</td>
				<td>${reservation.bookId}</td>
				<td>${reservation.bookName}</td>
				<td>${reservation.libraryId}</td>
				<td>
					<c:if test="${reservation.canceling == 0 }">
						キャンセル済み
					</c:if>
					<c:if test="${reservation.canceling == 1 }">
						<c:if test = "${reservation.delivering == 0 }">
							未受取
						</c:if>
						<c:if test = "${reservation.delivering == 1 }">
							受取済み
						</c:if>
					</c:if>
				</td>



				<td>貸し出し予定日</td>
				<td>
					<form action = "deliveringBook" method = "post">
		   	 			<input type = "hidden" id = "bookId" name = "bookId" value = "${reservation.id}" >
						<c:if test = "${reservation.delivering == 0 }">
							<input type = "hidden" name = "num" value = 1 >
							<input type = "submit" value = "受取" />
						</c:if>
						<c:if test="${reservation.delivering == 1 }">
							<input type = "hidden" name = "num" value =0>
							<input type = "submit" value = "未受" />
						</c:if>
					</form>
					</td>
					<td>
					<form action = "cancelingBook" method = "post">
		   	 			<input type = "hidden" id = "bookId" name = "bookId" value = "${reservation.id}" >
						<c:if test = "${reservation.canceling == 0 }">
							<input type = "hidden" name = "num" value = 1 >
							<input type = "submit" value = "予約取消" />
						</c:if>
						<c:if test = "${reservation.canceling == 1 }">
							<input type = "hidden" name = "num" value = 0 >
							<input type = "submit" value = "予約復活" />
						</c:if>
					</form>
					</td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>