<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
	<div class="clear-fix">
		<div class="school-news-and-event">
			<h2>News & Events</h2>
				
			<ul>
			<c:forEach var='schoolUpdatesMap' items='${model.schoolUpdatesMap}' varStatus="count" >
				<li>
					<div class="counts">${count.index+1 }</div>
					<c:forEach var='schoolUpdateDto' items='${schoolUpdatesMap.value}' varStatus="innerCount">
						<c:if test="${innerCount.index == 0}">
							<div class="description">
								<h3 class="title">${schoolUpdateDto.title }  </h3>
								<p>${schoolUpdateDto.description }</p>
							</div>
							<div class="read-more">Read More </div>
						</c:if>
						
					</c:forEach>
				</li>
			</c:forEach>
			</ul>
				<!-- <a href="#" class="button">Add News & Event</a> -->
		</div>
		
		<div class="home-intro-section">
			<h2>Welcome to ${model.schoolName }</h2>
			<div><img src="/linkalma/images/show-case.png" width="650" /></div>
			<br />
			<p>LinkAlma.com is an Alumni Relationship Platform (ARM) founded to create an ecosystem of educational institutes in India and its alumni. This is fully owned by Arghya Consulting Services Pvt. Ltd and is inspired by combination of its founder's own schooling experience, our exposure to the western education system and our current experience with our kid's education institutes and in general state of education in India.</p>
			<a href="#" class="button">Read More</a>
		</div>
	</div>
	<div class="school-staffs clear-fix">
	<h2>Our Staff</h2>
		<div class="staff-profile">
		<c:if test="${empty staffInfoList}">
		
		<p class="description">No Staff Entries, <a href="/linkalma/${model.dashboardUrl}">Add now !</a></p>
		
		</c:if>
		<c:forEach items="${staffInfoList}" var="staff" begin="0" end="3">
		
       
			<h3>${staff.facultyName}</h3>
			<div class="pic-container"><img src="${IMAGE_HOST_PATH}//${staff.photoName}" width="100%" height="185" /></div>
			<p class="description">
				 ${staff.subjectArea}</br>
			     ${staff.facultyEmail} <a href="javascript:;">read more</a>
			</p>
		</div>
		
		
	</c:forEach>						
	</div>

<%@ include file="footer.jsp" %>

