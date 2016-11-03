<%@ page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix="v" uri='http://vdab.be/tags'%>
<!DOCTYPE html>
<html lang='nl'>
<v:menu/>
<v:head title='Orderdetail ${order.id}' />

<c:if test="${not empty fout}">
	<div >
		<div  role="alert">
			<span  aria-hidden="true"></span>
			<span ><strong>Error:</strong></span>
			<strong>WARNING!</strong> ${fout}.
		</div>
	</div>
</c:if>
<c:if test="${not empty order}">
	<div >
		<h1>Order ${order.id}</h1>
	</div>
	<dl>
		<dt>Ordered:</dt>
		<dd><fmt:formatDate value='${order.orderDate}' type='date' dateStyle='short' /></dd>
		<br>
		<dt>Required:</dt>
		<dd><fmt:formatDate value='${order.requiredDate}' type='date' dateStyle='short' /></dd>
		<br>
		<dt>Customer name:</dt>
		<dd>${order.customer.name}<br>
		<dt>Street and number :</dt>
		<dd>${order.customer.adres.streetAndNumber}<br>
		<dt>Postalcode, City and state :
		<dd>${order.customer.adres.postalCode}&nbsp;${order.customer.adres.city}&nbsp;${order.customer.adres.state}<br>
		<dt>Country :</dt>
		<dd>${order.customer.adres.country.name}<br>
		</dd>
		<br>
		<dt>Comments:</dt>
		<dd>${order.comments}</dd>
		<br>
		<dt>Details:</dt>
		<dd>
			<table >
				<tr>
					<th >Product</th>
					<th >Price each</th>
					<th >Quantity</th>
					<th >Value</th>
					<th >Deliverable</th>
				</tr>
				<c:forEach items='${order.orderdetails}' var='orderdetail'>
					<tr>
						<td>${orderdetail.product.name}</td>
						<td ><fmt:formatNumber value='${orderdetail.priceEach}'/></td>
						<td ><fmt:formatNumber value='${orderdetail.quantityOrdered}'/></td>
						<td ><fmt:formatNumber value='${orderdetail.totalValue}'/></td>
						<td ><c:choose>
								<c:when
									test="${orderdetail.quantityOrdered <= orderdetail.product.quantityInStock}">
									<span ></span>
								</c:when>
								<c:otherwise>
									<span ></span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
				<tr >
					<th>Total Amount:</th>
					<th ></th>
					<th ></th>
					<th ><fmt:formatNumber value='${order.totalValue}'/></th>
					<th ></th>
				</tr>
			</table>
		</dd>
	</dl>
</c:if>
