$(function() {
	let $user = sessionStorage.getItem('user')
	let user = JSON.parse($user)
	if(user != null) {
		$('#success').text(user.nickname)
	} else {
		logut()
	}
	$('#logout_bt').click(function() {
		logut()
	})
});
let logut = function() {
	sessionStorage.clear()
	location.replace('/html/user/login.html')
}