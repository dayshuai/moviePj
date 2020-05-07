// 当domReady的时候开始初始化
function initwebuploader() {
	var $list = $("#informationList");
	//定时器
	var timer1;
	var timer2;
	var timer3;
	var timer4;
	var timer5;
	var timer6;
	var timer7;
	var timer8;
	//初始化Web Uploader
	var uploader = WebUploader.create({
		auto : true, // 自动上传
		swf : '/webuploader/dist/Uploader.swf', // swf文件路径
		server : globalContextPath + '/upload.json', // 文件接收服务端。
		timeout : 5 * 60 * 1000,
		pick : '#filePicker', // 选择文件的按钮。可选。
		accept : 'Files',
		fileNumLimit : 20, //最大上传数量为20
		fileSingleSizeLimit : 30 * 1024 * 1024, //限制上传单个文件大小30M
		fileSizeLimit : 600 * 1024 * 1024, //限制上传所有文件大小600M
		resize : false,
		duplicate :true
	});
	
	//当文件被加入队列之前触发
	uploader.on('beforeFileQueued', function (file) {
        if(file.size ==0){
        	clearTimeout(timer1);
    	    timer1 = setTimeout(function(){
    	    	Horn.Msg.warning('提示', '上传文件为空文件');
    	    },250);
        	return false;
        }
    });
	
	//上传前的判断处理
	uploader.on('error', function(type) {
		if (type === 'Q_EXCEED_NUM_LIMIT') {
			clearTimeout(timer2);
    	    timer2 = setTimeout(function(){
    	    	Horn.Msg.warning('提示', '最多允许上传20份文件');
    	    },250);
		}
		if (type == "Q_TYPE_DENIED") {
			clearTimeout(timer3);
    	    timer3 = setTimeout(function(){
    	    	Horn.Msg.warning('提示', '请上传文件');
    	    },250);
		}
		if (type == "F_EXCEED_SIZE") {
			clearTimeout(timer4);
    	    timer4 = setTimeout(function(){
    	    	Horn.Msg.warning('提示', '文件大小不能超过30M');
    	    },250);
		} else if (type == "Q_EXCEED_SIZE_LIMIT") {
			clearTimeout(timer5);
    	    timer5 = setTimeout(function(){
    	    	Horn.Msg.warning('提示', '文件总大小不能超过600M');
    	    },250);
		}
	});
	
	// 当有文件被添加进队列的时候

	uploader.on('fileQueued',function(file) {
		var tr = ('<tr id="'
				+ file.id
				+ '" class="item">'
				+ '<td class="info">'
				+ file.name
				+ '</td>'
				+ '<td class="state">等待上传...</td>'
				+ '<td>'
				+ '<div class="progress">'
				+ '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
				+ '<span class="sr-only">0% Complete</span>'
				+ '</div>'
				+ '</div>'
				+ '</td>'
				+ '<td class="webuploadDelbtn "><a href="javascript:delBtn()">删除</a></td>' + '</tr>');
		$list.append(tr);
	});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress',function(file, percentage) {
		var $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');
		// 避免重复创建
		if (!$percent.length) {
			$percent = $(
					'<div class="progress">'
							+ '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'
							+ '<span class="sr-only">0% Complete</span>'
							+ '</div>' + '</div>')
					.appendTo($li).find('.progress-bar');
		}
		$li.find('.state').text('上传中');

		$percent.css('width', percentage * 100 + '%');
	});

	//文件成功、失败处理
	uploader.on('uploadSuccess', function(file, data) {
		var rs = data;
		var returnCode = rs.return_code;
		if(!returnCode){
			clearTimeout(timer6);
        	timer6 = setTimeout(function(){
        		Horn.Msg.warning('提示', '请检查网络');
    	    },250);
		}else if(returnCode == '-1'){
			clearTimeout(timer7);
        	timer7 = setTimeout(function(){
        		Horn.Msg.warning('提示', '处于未登录状态');
    	    },250);
		}else if(returnCode == '10000'){
			if (file) {
				file.fileId = rs.success.fileID;
				gridDate[gridDate.length] = {
					"deleFileName" : rs.success.fileName,
					"PhysicalType" : '.'+file.ext,
					"fileId" : rs.success.fileID,
					"fileName" : "",
					"filePath" : rs.success.filePath,
					"oldFileName" : rs.success.oldFileName
				};
			}
			$('#' + file.id).find(".info").text('');
			var url = globalContextPath + "/download.htm?fileId="
					+ rs.success.fileID;
			var html = "<a href='" + url;
			html = html + "' title='";
			html = html + file.name;
			html = html + "'>";
			html = html + file.name + "</a>";
			$('#' + file.id).find(".info").append(html);
	
			$('#' + file.id).find('.state').text('已上传');
		}else{
			clearTimeout(timer8);
        	timer8 = setTimeout(function(){
        		Horn.Msg.warning('提示', '上传出错');
    	    },250);
		}
	});
	//上传错误
	uploader.on('uploadError', function(file) {
		$('#' + file.id).find('.state').text('上传出错');
	});
	//上传完成
	uploader.on('uploadComplete', function(file) {
		//	    $( '#'+file.id ).find('.progress').fadeOut();
	});

	//删除
	$list.on("click", ".webuploadDelbtn", function() {
		var $ele = $(this);
		var id = $ele.parent().attr("id");
		var file = uploader.getFile(id);
		if (file == null) {
			var fileId = id;
			for ( var i in gridDate) {
				if (gridDate[i].fileId == fileId) {
					gridDate.remove(i);
				}
			}
			$('#' + id).hide();
		} else {
			var fileId = file.fileId;
			uploader.removeFile(file, true);
			for ( var i in gridDate) {
				if (gridDate[i].fileId == fileId) {
					gridDate.remove(i);
				}
			}
		}
	});

	//删除时执行的方法
	uploader.on('fileDequeued', function(file) {
		$(file.id).remove();
		$('#' + file.id).find('.state').text('已经取消');
		$('#' + file.id).hide();
	});

};
function delBtn(){
	
}
Array.prototype.remove = function(obj) {
	for (var i = 0; i < this.length; i++) {
		var temp = this[i];
		if (!isNaN(obj)) {
			temp = i;
		}
		if (temp == obj) {
			for (var j = i; j < this.length; j++) {
				this[j] = this[j + 1];
			}
			this.length = this.length - 1;
		}
	}
}
