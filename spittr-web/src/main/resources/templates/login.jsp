<%@ include file="components/head.jsp"%>
<%@ include file="components/side-bar.jsp"%>


	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container">

			<div class="col-sm-offset-3 col-sm-6">
            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                  <p>
                    <font color="red">
                      Incorrect credentials <br/>
                      <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                    </font>
                  </p>
            </c:if>

						<div class="panel panel-warning">
							<div class="panel-heading"><spring:message code="authentication"/></div>
							<div class="panel-body">
								<form name='f' method='POST'>
									<div class="form-group">
										<label for="username"><spring:message code="authentication.username"/></label>
										<input type='text' name='username' value='' class="form-control"/>
									</div>
									<div class="form-group">
										<label for="password"><spring:message code="authentication.password"/></label>
										<input type='password' name='password' class="form-control"/>
									</div>
									<input name="submit" type="submit" value="Login"/>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
									<!--<button type="submit" class="btn btn-default"><spring:message code="authentication.login"/></button>-->
								</form>

							</div>
						</div>

			</div>


		</div>
		<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle
			Menu</a>
	</div>
	<!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="components/script-block.jsp"%>
<%@ include file="components/foot.jsp"%>
