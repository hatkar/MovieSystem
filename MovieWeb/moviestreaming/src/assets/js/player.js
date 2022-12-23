$(document).ready(function () {
	"use strict"; // start of use strict

	

	/*==============================
	Player
	==============================*/
	
function initializePlayer() {
			//alert('hi player')
		if ($('#player').length) {
			const player = new Plyr('#player');
		} else {
			return false;
		}
		return false;
	}
	$(window).on('load', initializePlayer());
	
});