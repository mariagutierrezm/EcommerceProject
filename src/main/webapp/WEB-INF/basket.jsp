<%@ include file="headermenu.jsp" %>

	<div class="text-center">
	<h3>Your basket ${ user.username }</h3>
	</div>
	${message}
	<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Products</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Date: ${basket.orderDate}</th>
				<th>Total Price</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="basketproduct">
				<tr>
					<td>${basketproduct.titleProduct}<input type="hidden"
						name="productID" value="${basketproduct.productID}" />
					</td>
					<td>${basketproduct.quantityBasket}</td>
					<td>${basketproduct.price}</td>
					<td>
					<a href="deleteFromBasket?productID=${basketproduct.productID}">
					<button type="submit" class="btn btn-success">Delete</button>
					</a>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<div>
							<c:if test="${basket.totalPrice != null}">
								<a href="checkout?basketID=${basketproduct.basketID}">
									<button type="submit" class="btn btn-info">Checkout</button>
								</a>
							</c:if>
						</div>
					</td>
					<td>&pound${basket.totalPrice}</td>
				</tr>
			
		</tbody>
	</table>
</div>
</body>
</html>