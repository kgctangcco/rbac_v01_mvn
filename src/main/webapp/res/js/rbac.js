$(function() {
	let $user = sessionStorage.getItem('user')
	let user = JSON.parse($user)
	if(user != null) {
		$('#success').text(user.nickname)
		showAllUser(user.account)
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
let showAllUser = function(account) {}