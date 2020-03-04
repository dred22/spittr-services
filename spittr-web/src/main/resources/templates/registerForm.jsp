<%@ include file="components/head.jsp" %>
<%@ include file="components/side-bar.jsp"%>

<div id="page-content-wrapper">
  <div class="container">

    <h1>Register</h1>
    <h2>Hello <security:authentication property="principal.username" />!</h2>
    <!-- enctype="multipart/form-data" -->
    <sf:form method="POST" modelAttribute="reference" >
    <sf:errors path="*" element="div" cssClass="errors" />
        <sf:label path="firstName" cssErrorClass="error">First Name</sf:label>: <sf:input path="firstName" /><br/>
        <sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>: <sf:input path="lastName" /><br/>
        <sf:label path="userName" cssErrorClass="error">Username</sf:label>: <sf:input path="userName" /><br/>
        <sf:label path="password" cssErrorClass="error">Password</sf:label>: <sf:password path="password" /><br/>
        <sf:label path="email" cssErrorClass="error">Email</sf:label>: <sf:input path="email" /><br/>
        <!--File to upload: <input type="file" name="profilePicture"><br />-->
        <input type="submit" value="Register" />
    </sf:form>

	</div><a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
	<!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->
<%@ include file="components/script-block.jsp"%>
<%@ include file="components/foot.jsp"%>