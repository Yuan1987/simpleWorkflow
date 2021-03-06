var Login = function() {
	var e = function() {
			$(".login-form").validate({
				errorElement : "span",
				errorClass : "help-block",
				focusInvalid : !1,
				rules : {
					username : {
						required : !0
					},
					password : {
						required : !0
					},
					remember : {
						required : !1
					}
				},
				messages : {
					username : {
						required : "Username is required."
					},
					password : {
						required : "Password is required."
					}
				},
				invalidHandler : function(e, r) {
					$(".alert-danger", $(".login-form")).show()
				},
				highlight : function(e) {
					$(e).closest(".form-group").addClass("has-error")
				},
				success : function(e) {
					e.closest(".form-group").removeClass("has-error"), e.remove()
				},
				errorPlacement : function(e, r) {
					e.insertAfter(r.closest(".input-icon"))
				},
				submitHandler : function(e) {
					e.submit()
				}
			}), $(".login-form input").keypress(function(e) {
				return 13 == e.which ? ($(".login-form").validate().form() && $(".login-form").submit(), !1) : void 0
			})
		},
		r = function() {
			$(".forget-form").validate({
				errorElement : "span",
				errorClass : "help-block",
				focusInvalid : !1,
				ignore : "",
				rules : {
					email : {
						required : !0,
						email : !0
					}
				},
				messages : {
					email : {
						required : "Email is required."
					}
				},
				invalidHandler : function(e, r) {},
				highlight : function(e) {
					$(e).closest(".form-group").addClass("has-error")
				},
				success : function(e) {
					e.closest(".form-group").removeClass("has-error"), e.remove()
				},
				errorPlacement : function(e, r) {
					e.insertAfter(r.closest(".input-icon"))
				},
				submitHandler : function(e) {
					e.submit()
				}
			}), $(".forget-form input").keypress(function(e) {
				return 13 == e.which ? ($(".forget-form").validate().form() && $(".forget-form").submit(), !1) : void 0
			}), jQuery("#forget-password").click(function() {
				jQuery(".login-form").hide(), jQuery(".forget-form").show()
			}), jQuery("#back-btn").click(function() {
				jQuery(".login-form").show(), jQuery(".forget-form").hide()
			})
		},
		i = function() {
			function e(e) {
				if (!e.id) return e.text;
				var r = $('<span><img src="../assets/global/img/flags/' + e.element.value.toLowerCase() + '.png" class="img-flag" /> ' + e.text + "</span>");
				return r
			}
		};
	return {
		init : function() {
			e(), r(), i()
		}
	}
}();
jQuery(document).ready(function() {
	Login.init();
	console.info(tipMsg);
	if(tipMsg==''||tipMsg==null){
		return;
	}
	App.alert({
        container:"#tips", // alerts parent container(by default placed after the page breadcrumbs)
        place: "append", // append or prepent in container 
        type: "danger",  // alert's type
        message: tipMsg,  // alert's message
        close: 1, 
        closeInSeconds:1
    });
});