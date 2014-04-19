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
		editForm();
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
		if (validateForm($(this))) {
			submitFormThroughAjax($(this));
		}

	});
}

function registerNewSchool() {
	$("#registerSchoolBtn").on("click",function(e) {
		e.preventDefault();
		if(validateForm($("#registerSchoolForm"))){
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
function validateForm(form) {
	
	$(".errorMsg", form).remove();
	var isFormValidate = true;
	
	$(".required", form).each(function() {
		if ($.trim($(this).val()).length == 0) {
			$(this).addClass("vError");
		} else {
			$(this).removeClass("vError");
		}
	});

	//password match validation
	if($("input[type='password']", form).length > 1){
		var errContainer = $("<div class='errorMsg'></div>");
		var errMsg = "Your password does not match";
		var pwdField1 = $("input[type='password']", form).eq(0);
		var pwdField2 = $("input[type='password']", form).eq(1);

		if (pwdField1.val() != pwdField2.val()){
			$(pwdField2).addClass("vError");
			errContainer.html(errMsg);
			$(pwdField2).after(errContainer);
			
		} else {
			$(pwdField2).removeClass("vError");
		}
		
	}
	//email validation
	var emailFields = $(".emailField", form);
	if(emailFields.length > 0){
		// if any value then only validate email pattern
		var errContainer = $("<div class='errorMsg'></div>");
		if($.trim(emailFields.val()).length > 0){
			var errMsg = "Pleae enter a valid email address";
			var reg = /^\w.+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/ ;
			
			if (reg.test(emailFields.val())){
				emailFields.removeClass("vError");
				emailFields.next("div").remove();
				
			} else {
				emailFields.addClass("vError");
				errContainer.html(errMsg);
				emailFields.after(errContainer);
			
			}
		}
		
		//if ($(email).attr("errMsg") == udefined || $.trim($(email).attr("errMsg"))) 
		
	}

	if($(".vError",form).length > 0){
		isFormValidate = false;
	}
	
	return isFormValidate;

}
/* generic function which make form fields editable */
function editForm(){
	var beforeUpdateFormData = "", afterUpdateFormData=""; 
	$(".editFormBtn").on("click", function() {
		var formEl = $(this).closest("form");
		
		
		if ($(this).hasClass("editFormBtn")){
			beforeUpdateFormData = formEl.serialize();
			$(this).text("Update");
			formEl.removeClass("readOnlyForm");
			makeFormReadonly(formEl);
		} else {
			afterUpdateFormData = formEl.serialize();

			if (beforeUpdateFormData != afterUpdateFormData){
				alert("Submit Form");
				$(this).text("Edit");
				formEl.addClass("readOnlyForm");
				makeFormEditable(formEl);
			} else {
				$(this).text("Edit");
				formEl.addClass("readOnlyForm");
				makeFormEditable(formEl);
			}
			
			
		}
		$(this).toggleClass("editFormBtn");
	});
}
function makeFormEditable(form){
	$("input", form).each(function() {
		$(this).attr("readonly",true);
	});
}

function makeFormReadonly(form){
	$("input", form).each(function() {
		$(this).attr("readonly",false);
	});
}