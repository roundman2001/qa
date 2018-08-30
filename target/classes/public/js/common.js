function initValidator(formId, picFlg) {
	if (!arguments[1]) {
		$('#' + formId).bootstrapValidator({
			message : '',
			feedbackIcons : {
				// valid: 'glyphicon glyphicon-ok',
				// invalid: 'glyphicon glyphicon-remove', 暂时去除
				validating : 'glyphicon glyphicon-refresh'
			}
		});

	} else {
		$('#' + formId).bootstrapValidator({
			message : '',
			feedbackIcons : {
				validating : 'glyphicon glyphicon-refresh'
			}
		});

	}

}

function ajaxPost(p_url, p_data) {
	var retData;
	var ret = $.ajax({
		type : 'post',
		async : false,
		url : p_url,
		data : JSON.stringify(p_data),
		cache : false,
		dataType : 'json',
		contentType : 'application/json;charset=UTF-8',
		success : function(data) {
			console.log(data);
			retData = data;
		},
		error : function(xhr,textStatus,errorThrown){		 
	       　　if (xhr.status == 401) {
	    	alert('Session丢失，请重新登陆');
	       　　　　  location.href ='./login';
	         　} else{
	           　　// 调用外部的error
	            　 error && error(xhr,textStatus,errorThrown);
	      　　 }
	    　　}
	});

	return retData;

} 

$.fn.serializeJson = function() {
	var arr = this.serializeArray();
	var json = {};
	arr.forEach(function(item) {
		var name = item.name;
		var value = item.value;

		if (!json[name]) {
			json[name] = value;
		} else if ($.isArray(json[name])) {
			json[name].push(value);
		} else {
			json[name] = [ json[name], value ];
		}
	});
	return json;
}

/**
 * 将form序列化Json对象 {key1:"value1",key2:"value2"}
 * 
 * @example <script> var formParams = $("#formId").serializeObject(); </script>
 */
$.prototype.serializeObject = function() {
	var a, o, h, i, e;
	a = this.serializeArray();
	o = {};
	h = o.hasOwnProperty;
	for (i = 0; i < a.length; i++) {
		e = a[i];
		if (!h.call(o, e.name)) {
			o[e.name] = e.value;
		}
	}
	return o;
};
