<%@ include file="components/head.jsp" %>
<%@ include file="components/side-bar.jsp"%>

	<!-- Page Content -->

	<div id="page-content-wrapper">
		<div class="container">

<h1>Your Profile</h1>
<div>username : <c:out value="${reference.userName}" /></div>
<div>firstName : <c:out value="${reference.firstName}" /></div>
<div>lastName : <c:out value="${reference.lastName}" /></div>


	</div>
	<!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="components/script-block.jsp"%>
<%@ include file="components/foot.jsp"%>
