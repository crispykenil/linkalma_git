<%@ include file="header.jsp" %>
<style>
.sign-in-wrapper input[type=text], .sign-in-wrapper input[type=password]{
	width:79%;
}
#signup-panel {
	font-size: 566px;
	position: relative;
	margin: auto;
	color:#eee;
	background:none;
	display:block;
	width:360px
}
#signup-panel h2 {
	position:absolute;
	top:160px;
	left:148px;
	
}
#signup-panel:hover, #signup-panel.fa:hover {
	background:none
}
.sign-in-wrapper {
	position:absolute;
	bottom:140px;
	left:30px;
	width:85%
}
</style>

<div class="border-box fa fa-lock " id="signup-panel">
<h2>Login</h2>
	<div class="sign-in-wrapper">
	<c:if test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<div class="errorMsg">
				<c:out value="${error}" />
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty errors}">
		<form:form action="resetpassword?operation=reset" modelAttribute="resetForm" >
			<ul>
				<li><label>Password</label>			<input type="password" value="" placeholder="" name="password" autofocus="autofocus" class="required" /></li>
				<li><label>Confirm Password</label>	<input type="password" value="" placeholder=""  class="required" /></li>
				<li> <input type="submit" value="Reset Password" id="sign-in-button" class="button"/></li>

			</ul>
		</form:form>
		
	</c:if>
</div>
</div>
<%@ include file="footer.jsp" %>

