// 当domReady的时候开始初始化
	$(function() {
	var singleTrustJsonupload={};
	//初始化Web Uploader
	var uploaderTrustJson = WebUploader.create({
	    auto: true,                                  // 自动上传
	    swf: '/webuploader/dist/Uploader.swf',     	  // swf文件路径
	    server: globalContextPath+'/upload/uploadImage.html',                   // 文件接收服务端。
	    timeout: 5*60*1000,
	    formData: {  
	        
	    },
	    pick: {
	    		id:'#singleTrustJson',
	    		//选择一个文件
	    		multiple:false
	    	  },                          // 选择文件的按钮。可选。
	    accept: {  
	        title: 'Files',  
	        extensions: 'jpg,png,mp4',
	        mimeTypes: '.jpg,.png,.mp4'  
	    },
	    fileNumLimit: 1,                              //最大上传数量为1
	    fileSingleSizeLimit: 3000 * 1024 * 1024,         //限制上传单个文件大小30M
	    fileSizeLimit: 3000 * 1024 * 1024,              //限制上传所有文件大小30M
	    resize: false                                  // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	});
	
	
	
	
	//当文件被加入队列之前触发
	uploaderTrustJson.on('beforeFileQueued', function (file) {
        if(file.size ==0){
        	alert('提示', '上传文件为空文件');
        	return false;
        }
    });
	
	
	
	//上传前的判断处理
	uploaderTrustJson.on('error', function( type ){
	    if ( type === 'Q_EXCEED_NUM_LIMIT' ) {
	        alert('提示', '最多允许上传1份文件');
	    }
	    if ( type === 'F_DUPLICATE' ) {
            alert('提示', '不能重复上传');
        }
	    if (type=="Q_TYPE_DENIED"){
            alert('提示', '请上传JSON格式文件');
        }
	    if(type=="F_EXCEED_SIZE"){
	    	alert('提示', '文件大小不能超过30M');
	    }else if(type =="Q_EXCEED_SIZE_LIMIT"){
	    	alert('提示', '文件大小不能超过30M');
	    }
	});
	
	
	
	// 当有文件被添加进队列的时候
	uploaderTrustJson.on( 'fileQueued', function( file ) {
		 $('#singleTrustJsonFileName').text(file.name);
		 var tr= ('<div class="progress">' +
			        '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">' +
			        	'<span class="sr-only">0% Complete</span>' +
			        '</div>' +
			      '</div>');
		 $('#singleTrustJsonFileName').append(tr);
	});
	
	

	// 文件上传过程中创建进度条实时显示。
	uploaderTrustJson.on( 'uploadProgress', function( file, percentage ) {
		var $percent = $('.progress-bar-striped');
	    $percent.css( 'width', percentage * 100 + '%' );
	    
    });
	
	
	    
	//文件成功、失败处理
	uploaderTrustJson.on( 'uploadSuccess', function( file, data ) {
		var rs=data;
		var returnCode = rs.return_code;
		$("#picPath").val(data.data);
		$("#viewpic").attr("src",globalContextPath + data.data);
	});
	

	
	//上传错误
	uploaderTrustJson.on( 'uploadError', function( file ) {
		alert('提示', '上传出错');
		
	});

	//上传完成
	uploaderTrustJson.on( 'uploadComplete', function( file ) {
		
	});
	// 所有文件上传成功后调用        
	uploaderTrustJson.on('uploadFinished', function () {
	    //清空队列
		uploaderTrustJson.reset();
	});
	

	//删除
	$(".singleTrustJsonDl").on("click",  function () {
		$('#singleTrustJsonFileName').text('');
		$('#singleTrustJson .webuploader-pick').text("上传");
		singleTrustJsonupload={};
		uploaderTrustJson.reset();
		
	}); 
	
});