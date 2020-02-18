<%@ include file="headerpage.jsp" %>


<div class="container">
	<div class="col-md-7 mx-auto">
       <div class="card my-5">
       		<div class="card-body text-center">
       			<h3>Register</h3>
       			<form:form action="registerSubmit" method="post" modelAttribute="user">
                ${errorMessage}
                
                <div class="form-group-inline row">
                       <div class="col-md-7">
	                       <form:label path="username">Username:</form:label>
	                       <form:input path="username" name="username" required="required"
                       			class="form-control" placeholder="Enter username" />
                       </div>
                       <div class="col-md-7">
	                       <form:label path="password">Password:</form:label>
	                       <form:input path="password" name="password" required="required"
	                       		class="form-control" placeholder="Enter password" />
                       </div>
                       <div class="col-md-7">
	                       <label for="confirmPassword">Confirm Password:</label>
	                       <input type="password" name="confirmPassword" required="required" class="form-control" 
	                    		   placeholder="Confirm Password"/>
	                   </div>
	                   <div class="col-md-7">
							<form:label path="firstname">Firstname:</form:label>
							<form:input path="firstname" name="firstname" required="true" class="form-control"
									placeholder="Enter firstname"/>
					   </div>
					   <div class="col-md-7">
							<form:label path="lastname">Lastname:</form:label>
							<form:input path="lastname" name="lastname" required="true"	class="form-control"
									placeholder="Enter lastname"/>
					  </div>
                 </div>
                 <br>
                 <button type="submit" class="btn btn-info shadow">Register</button>
                 </form:form>
           </div>
    </div>
</div>
</body>
</html>