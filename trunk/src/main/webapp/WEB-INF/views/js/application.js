// Semicolon (;) to ensure closing of earlier scripting
// Encapsulation
// $ is assigned to jQuery
;(function($) {

	// DOM Ready
	$(function() {

		getSignUpForm();
		submitSignUpForm();
		registerNewSchool();
		getFullNewsAndEvents();
		eventBindingForProfilePage();
		
	});

})(jQuery);
function getFullNewsAndEvents(){
	$('.school-news-and-event .read-more').on('click', function(e) {
		// Triggering bPopup when click event is fired
		var popUpContent = $(this).parent().find(".description").clone();
		popUpContent.wrap( "<div class='popup-container'></div>" );
	
		popUpContent.bPopup({
			fadeSpeed : 'slow', //can be a string ('slow'/'fast') or int
			followSpeed : 1500, //can be a string ('slow'/'fast') or int
			modalColor : 'black'
		});

	});
}
function getSignUpForm (){
	// Binding a click event
	// From jQuery v.1.7.0 use .on() instead of .bind()
	$('#sign-up-button').bind('click', function(e) {
		// Triggering bPopup when click event is fired
		$('#sign-up').bPopup({
			fadeSpeed : 'slow', //can be a string ('slow'/'fast') or int
			followSpeed : 1500, //can be a string ('slow'/'fast') or int
			modalColor : 'black'
		});

	});
	
}
function submitSignUpForm() {
	$("#signUpForm").submit(function(e) {
		e.preventDefault();
		if (form.validateForm($(this))) {
			submitFormThroughAjax($(this));
		}

	});
}

function registerNewSchool() {
	$("#registerSchoolBtn").on("click",function(e) {
		e.preventDefault();
		if(form.validateForm($("#registerSchoolForm"))){
			$("#registerSchoolForm").submit();
		}

	});
}

function submitFormThroughAjax(form) {
	var formData = form.serialize();
	var url = form.attr("action");
	$.ajax({
		type : "POST",
		url : url,
		data : formData

	}).done(function(data) {
		console.log(data);
	});
}

var submitPersonalDetails = function(){

var form = $('#personalDetails');
	var formData = form.serialize();
	var url = form.attr("action");
	alert(formData);
	$.ajax({
		type : "POST",
		url : url,
		data : formData

	}).done(function(data) {
		console.log(data);
		alert('done dona done.. done....')
	});
	
};

function eventBindingForProfilePage(){
	//editForm();
	//cancelUpdateAction();
	getAddMySchoolForm();
	//submitPersonalDetails();

	$(".personalDetailsForm").bindEditUpdateFunctionality({callback:submitPersonalDetails});
	$(".schoolDetailsForm").bindEditUpdateFunctionality();
}

function getAddMySchoolForm (){
	$('.addMySchool').bind('click', function() {
		$('#addMydSchool').bPopup({
			fadeSpeed : 'slow', //can be a string ('slow'/'fast') or int
			followSpeed : 1500, //can be a string ('slow'/'fast') or int
			modalColor : 'black'
		});
	});
}

