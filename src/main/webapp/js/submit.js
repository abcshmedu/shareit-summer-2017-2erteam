'use strict';

/**
 * Ugly java script code for simple tests of shareit's REST interface.
 *  @author Axel Böttcher <axel.boettcher@hm.edu>
 */

/**
 * This function is used for transfer of new book info.
 */
var submitNewBook = function() {
	var json = JSON.stringify({
			title: $("input[name=title]").val(),
			author: $("input[name=author]").val(),
			isbn: $("input[name=isbn]").val()
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/books/',
        type:'POST',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			$("input[name=title]").val("");
			$("input[name=author]").val("");
			$("input[name=isbn]").val("");
        	
        	errorText.removeClass("visible");
        	errorText.addClass("hidden");
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}
/**
 * This function is used for transfer of new disc info.
 */
var newDisc = function() {
	var json = JSON.stringify({
			title: $("input[name=title]").val(),
			director: $("input[name=director]").val(),
			barcode: $("input[name=barcode]").val(),
			fsk: $("input[name=fsk]").val()
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/discs/',
        type:'POST',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			$("input[name=title]").val("");
			$("input[name=direcctor]").val("");
			$("input[name=barcode]").val("");
			$("input[name=fsk]").val("");
        	
        	errorText.removeClass("visible");
        	errorText.addClass("hidden");
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}

/**
 * Creates a list of all books using a Mustache-template.
 */
var listBooks = function() {
	$.ajax({
        url: '/shareit/media/books',
        type:'GET'
	})
	.done((data) => {
		var template = "<table class='u-full-width'><tbody>{{#data}}<tr><td>{{title}}</td><td>{{author}}</td><td>{{isbn}}</td></tr>{{/data}}</tbody></table>";
		Mustache.parse(template);
		var output = Mustache.render(template, {data: data});
		$("#content").html(output);
	});// no error handling
}
/**
 * Creates a list of all discs using a Mustache-template.
 */
var listDiscs = function() {
	$.ajax({
        url: '/shareit/media/discs',
        type:'GET'
	})
	.done((data) => {
		var template = "<table class='u-full-width'><tbody>{{#data}}<tr><td>{{title}}</td><td>{{director}}</td><td>{{barcode}}</td><td>{{fsk}}</td></tr>{{/data}}</tbody></table>";
		Mustache.parse(template);
		var output = Mustache.render(template, {data: data});
		$("#content").html(output);
	});// no error handling
}
/**
* update a Disc
*/
var updateDisc = function() {
	var helper;
	if( $("input[name=fsk]").val().length === 0 ){
		helper = -1;
	} else{
		helper = $("input[name=fsk]").val();
	}
	var json = JSON.stringify({
			title: $("input[name=title]").val(),
			director: $("input[name=director]").val(),
			barcode: $("input[name=barcode]").val(),
			fsk: helper
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/discs/'+$("input[name=barcode]").val(),
        type:'PUT',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			title: $("input[name=title]").val("");
			director: $("input[name=director]").val("");
			barcode: $("input[name=barcode]").val("");
			fsk: $("input[name=fsk]").val("");
        	
        	errorText.removeClass("visible");
        	errorText.addClass("hidden");
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}
/**
* update a Disc
*/
var updateBook = function() {
	var json = JSON.stringify({
			title: $("input[name=title]").val(),
			author: $("input[name=author]").val(),
			isbn: $("input[name=isbn]").val()
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/books/'+$("input[name=isbn]").val(),
        type:'PUT',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			$("input[name=title]").val("");
			$("input[name=author]").val("");
			$("input[name=isbn]").val("");
        	
        	errorText.removeClass("visible");
        	errorText.addClass("hidden");
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}
/**
* gets a Book by its ISBN
*/
var getBook = function() {
	var json = JSON.stringify({
			isbn: $("input[name=isbn]").val()
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/books/'+$("input[name=isbn]").val(),
        type:'GET',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			var template = "<table class='u-full-width'><tbody>{{#data}}<tr><td>{{title}}</td><td>{{author}}</td><td>{{isbn}}</td></tr>{{/data}}</tbody></table>";
			Mustache.parse(template);
			var output = Mustache.render(template, {data: data});
			$("#content").html(output);
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}
/**
* gets a Disc by its barcode
*/
var getDisc = function() {
	var json = JSON.stringify({
			barcode: $("input[name=barcode]").val()
	});
	var errorText = $("#errormessage");
    $.ajax({
        url: '/shareit/media/books/'+$("input[name=barcode]").val(),
        type:'GET',
        contentType: 'application/json; charset=UTF-8',
        data: json
        })
        .done(() => {
			var template = "<table class='u-full-width'><tbody>{{#data}}<tr><td>{{title}}</td><td>{{director}}</td><td>{{barcode}}</td><td>{{fsk}}</td></tr>{{/data}}</tbody></table>";
			Mustache.parse(template);
			var output = Mustache.render(template, {data: data});
			$("#content").html(output);
        })
        .fail((error) => {
        	errorText.addClass("visible");
        	errorText.text(error.responseJSON.detail);
        	errorText.removeClass("hidden");
        });
}
/**
 * Call backer for "navigational buttons" in left column. Used to set content in main part.
 */
var changeContent = function(content) {
	$.ajax({
        url: content,
        type:'GET'
	})
	.done((data) => {
		$("#content").html(data);
	});// no error handling
}
var login = function() {
	var json = JSON.stringify({
		usr: $("input[name=usr]").val(),
		pwd: $("input[name=pwd]").val()
	});
	var errorText = $("#errormessage");
	$.ajax({
	    url: 'shareit/users/authenticate',
	    type:'POST',
	    contentType: 'application/json; charset=UTF-8',
        data: json
	    })
	    .done(() => {
			var template = "<table class='u-full-width'><tbody>{{#data}}<tr><td>{{usr}}</td><td>{{pwd}}</td></tr>{{/data}}</tbody></table>";
			Mustache.parse(template);
			var output = Mustache.render(template, {data: data});
			$("#content").html(output);
	    })
	    .fail((error) => {
	    	errorText.addClass("visible");
	    	errorText.text(error.responseJSON.detail);
	    	errorText.removeClass("hidden");
	    });
}
