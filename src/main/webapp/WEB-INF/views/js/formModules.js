var form = {
	formFields : "input[type='text'],input[type='password'],select,input[type='radio']",
	submitFormThroughAjax: function (options) {
		// This is the easiest way to have default options.
		 var settings = $.extend({
			// These are the defaults.
			 form :null,
			 callback:false
		 }, options);
		var formEl = settings.form;
		var formData = formEl.serialize();
		var url = formEl.attr("action");
		$.ajax({
			beforeSend:function disableButton(){
				$('.btn-wrapper .button', formEl).attr("disabled",true);
				$('.btn-wrapper', formEl).append('<span class="fa fa-spinner fa-spin"></span>');
			},
			type : "POST",
			url : url,
			data : formData

		}).done(function(data) {
			showMessage(data);
		}).error(function(data) {
			showMessage(data);
		}).complete(function(data) {
			console.log(data);
			if(settings.callback)
				settings.callback();
			$('.btn-wrapper .button', formEl).attr("disabled",false);
			$('.btn-wrapper .fa-spin', formEl).remove();
		});

		var showMessage = function showMessage(data){
			$('.message', formEl).html(data).show();
		};
	},
	makeFormReadonly : function(form) {
		$(this.formFields, form).each(function() {
			$(this).attr("disabled", true);
//			if ($(this).is("select")){
//				$(this).hide();
//			}
		});
		form.addClass("readOnlyForm");
	},
	makeFormEditable : function(form) {
		$(this.formFields, form).each(function() {
			$(this).attr("disabled", false);
//			if ($(this).is("select")){
//				$(this).show();
//			} 
		});
		form.removeClass("readOnlyForm");
	},
	validateForm : function(form) {

		$(".errorMsg", form).remove();
		var isFormValidate = true;

		$(".required:visible", form).each(function() {
			if ($.trim($(this).val()).length == 0) {
				$(this).addClass("vError");
			} else {
				$(this).removeClass("vError");
			}
		});
		$(".numeric:visible", form).each(function() {
			if (isNaN($(this).val())) {
				$(this).addClass("vError");
			} else {
				$(this).removeClass("vError");
			
			}
		});
		// password match validation
		if ($("input[type='password']", form).length > 1) {
			var errContainer = $("<div class='errorMsg'></div>");
			var errMsg = "Your password does not match";
			var pwdField1 = $("input[type='password']", form).eq(0);
			var pwdField2 = $("input[type='password']", form).eq(1);

			if (pwdField1.val() != pwdField2.val()) {
				$(pwdField2).addClass("vError");
				errContainer.html(errMsg);
				$(pwdField2).after(errContainer);

			} else {
				$(pwdField2).removeClass("vError");
			}

		}
		// email validation
		var emailFields = $(".emailField:visible", form);
		if (emailFields.length > 0) {
			// if any value then only validate email pattern
			var errContainer = $("<div class='errorMsg'></div>");
			if ($.trim(emailFields.val()).length > 0) {
				var errMsg = "Pleae enter a valid email address";
				var reg = /^\w.+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
				if (reg.test(emailFields.val())) {
					emailFields.removeClass("vError");
					emailFields.next("div").remove();

				} else {
					emailFields.addClass("vError");
					errContainer.html(errMsg);
					emailFields.after(errContainer);

				}
			}

			// if ($(email).attr("errMsg") == udefined ||
			// $.trim($(email).attr("errMsg")))

		}

		if ($(".vError", form).length > 0) {isFormValidate = false;	}
		return isFormValidate;

	}

};

(function($) {
	$.fn.bindEditUpdateFunctionality = function(options) {
		var myForm = this;
		//this two variable will decide is any changes done in form
		var beforeUpdateFormData = "", afterUpdateFormData = "";
		
		//var editFormBtn = $(".editFormBtn", myForm);
		//var updateFormBtn = $(".updateFormBtn", myForm);
		var editUpdateFormBtn = $(".editUpdateFormBtn", myForm);
		var cancelUpdateActionBtn = $(".cancelUpdateAction", myForm);
		

		// This is the easiest way to have default options.
		 var settings = $.extend({
		// These are the defaults.
		 callback :false,
		 submitThroughAjax:true,
		 }, options);
		
		//make form readonly on onload
		if (myForm.hasClass("readOnlyForm")){
			form.makeFormReadonly(myForm);
		}

		editUpdateFormBtn.on("click", function() {
			
			if($(this).hasClass("editForm")){
				executeEditAction();
				$(this).removeClass("editForm").addClass("updateForm").val("Update");
			} else {
				executeUpdateAction();
				
			}
			
		});
		function executeEditAction(){
			cancelUpdateActionBtn.attr("disabled", false);
			beforeUpdateFormData = myForm.serialize();
			form.makeFormEditable(myForm);
			
		}
		function executeUpdateAction(){
			afterUpdateFormData = myForm.serialize();
			if (beforeUpdateFormData != afterUpdateFormData) {
				//alert("found some updation so please valid form and submit it");
				if (form.validateForm(myForm)) {
					if (settings.submitThroughAjax){
						var formData = myForm.serialize();
						var url = myForm.attr("action");
						$.ajax({
							beforeSend:function disableButton(){
								//$('.btn-wrapper .button', formEl).attr("disabled",true);
								//$('.btn-wrapper', formEl).append('<span class="fa fa-spinner fa-spin"></span>');
							},
							type : "POST",
							url : url,
							data : formData

						}).done(function(data) {
							
						}).error(function(data) {
							
						}).complete(function(data) {
							resetForm();
							
						});
						
					} else {
						myForm.submit();
					}

	
				} else
					return false;
			} else {
				alert("please change something before update");
				return false;
			}
			$(this).removeClass("updateForm").addClass("editForm").val("Edit");
		}
		function resetForm(){
			editUpdateFormBtn.removeClass("updateForm").addClass("editForm").val("Edit");
			cancelUpdateActionBtn.attr("disabled",true);
			form.makeFormReadonly(myForm);
		}
		cancelUpdateActionBtn.on("click", function() {
			resetForm();
	
		});
	};
	
}(jQuery));

function E(id) { return document.getElementById(id); }
function changeit(drp,txf)
{
    dd = E(drp);
    E(txf).value = dd.options[ dd.selectedIndex ].text;
}