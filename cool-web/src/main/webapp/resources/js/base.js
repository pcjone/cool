var dicts;

//加载数据字典数据  参数为需要加载的数组
function loadDic(categorys) {
	$.ajax({
		url : '../dic/sysDic',
		data : {
			"categorys" : categorys
		},
		type : 'post',
		async : false,
		dataType:"json",
		traditional:true,
		success : function(result) {
			dicts=result;
		},
		error : function(data) {
			swal("错误", data.responseText, "error");
			}
		}); 

}
//数据字典中文格式化方法
function dic_value_text(category){
	return function(value) {
		//特殊全局处理
		if(category == 'ENABLE' && value == 1){
			return "<a class='btn btn-danger btn-rounded btn-xs'>"+dicts[category+value]+"<a>";
		}else if(category == 'ENABLE' && value == 0){
			return "<a class='btn btn-success btn-rounded btn-xs'>"+dicts[category+value]+"<a>";
		}else{
			return dicts[category+value];
		}
	};
}