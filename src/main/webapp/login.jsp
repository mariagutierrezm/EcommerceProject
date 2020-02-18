<%@ include file="headerpage.jsp" %>

<body>
	<div class="container">
		<div class="mx-auto">
			<div class="card my-5">
		    	<div class="card-body text-center">
			    	<h3>Log in</h3>
			        <br>
		            	<form action="LoginSubmit" method="post">
		                           ${errorMessage}
	
		                   <div class="form-row">
		                   		<div class="col-md-7">
		                   			<input type="text" name="username" required="required"
		                                 class="form-control" placeholder="Enter Username" />
		                         </div>
		                         <br>
		                         <br>
		                         <div class="col-md-7">
		                         	<input type="text" name="password" required="required"
		                         		class="form-control" placeholder="Enter Password" />
		                         </div>
		                   </div>
		                   <br>
		                   <button type="submit" class="btn btn-info shadow">Login</button>
		               </form>
		        </div>
		   </div>
		</div>
	</div>
</body>
</html>