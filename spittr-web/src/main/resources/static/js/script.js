$(function(){

$("#menu-toggle").on("click", ()=>{
	console.log("toggled");
	$.get( "./toggle", function( data ) {
	});
	
})

})
