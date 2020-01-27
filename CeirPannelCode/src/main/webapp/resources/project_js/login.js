$('#langlist').on('change', function() {
			window.lang = $('#langlist').val() == 'km' ? 'km' : 'en';
			var url_string = window.location.href;
			var url = new URL(url_string);
			
			var type = url.searchParams.get("type");
			
			alert("hi3");
			window.location.assign("login?&lang=" + window.lang);
		});

		$(document).ready(function() {
			var url = new URL(window.location.href);
			var langParameter = url.searchParams.get("lang");
			$('#langlist').val(langParameter == 'km' ? 'km' : 'en');
			dataByTag("link_dmc_portal", "newUserLink", 1);
		});

		$(document)
				.ready(
						function() {
							$("#show_hide_password a")
									.on(
											'click',
											function(event) {
												event.preventDefault();
												if ($(
														'#show_hide_password input')
														.attr("type") == "text") {
													$(
															'#show_hide_password input')
															.attr('type',
																	'password');
													$('#show_hide_password i')
															.addClass(
																	"fa-eye-slash");
													$('#show_hide_password i')
															.removeClass(
																	"fa-eye");
												} else if ($(
														'#show_hide_password input')
														.attr("type") == "password") {
													$(
															'#show_hide_password input')
															.attr('type',
																	'text');
													$('#show_hide_password i')
															.removeClass(
																	"fa-eye-slash");
													$('#show_hide_password i')
															.addClass("fa-eye");
												}
											});
						});
