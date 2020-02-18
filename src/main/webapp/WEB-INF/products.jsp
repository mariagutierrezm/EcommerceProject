<%@ include file="headermenu.jsp" %>

	<div class="text-center">
	<h3>Our Organic Products</h3>
	${successMessage}
	</div>
	<div class="container">
		<form:form action="filterProducts" method="POST" modelAttribute="product">
		${errorMessage}
		
			<div class="btn-group">
				<form:label path="TypeOfProduct" for="typeOfProduct" class="link">Categories: </form:label>
				<form:select path="TypeOfProduct" class="button-filter dropdown-toggle" data-toggle="dropdown">
					<form:options items="${ typeOfProduct }" itemLabel="name" class="dropdown-items"/>
				</form:select>
				<button type="submit" class="button-filter">
					<span class="glyphicon glyphicon-search"></span>Filter
				</button>
					<button type="button" class="button-reset">
					<a href="products">Reset Filter</a>
					</button>
			</div>
		</form:form>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Product Weight</th>
					<th>Price &pound</th>
					<th>Country of Origin</th>
					<th>Product Description</th>
					<th>Quantity in Stock</th>
					<th>Add to Basket</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<form method="post" action="addToBasket">
					<tr>
						<td>${product.titleProduct}<input type="hidden"
								name="productID" value="${product.productID}" />
						</td>
						<td>${product.weight}</td>
						<td>&pound${product.price}</td>
						<td>${product.countryOfOrigin}</td>
						<td>${product.description}</td>
						<td>${product.quantity}</td>
						<td>
							<div class="form-group">
								<input type="number" name="quantity" min="1" max="5" class="form-control" placeholder="0" required="true"/>
								<a href="addToBasket?productID=${product.productID}">
								<button type="submit" class="btn btn-success">Add</button>
								</a>
			                </div>
						</td>
					</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>