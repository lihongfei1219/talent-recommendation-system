//图片转base64编码
const getBase64Data = function(imgSrc){
//    var imgSrc = "img/1.jpg";
      function getBase64(img){//传入图片路径，返回base64
        function getBase64Image(img,width,height) {//width、height调用时传入具体像素值，控制大小 ,不传则默认图像大小
          var canvas = document.createElement("canvas");
          canvas.width = width ? width : img.width;
          canvas.height = height ? height : img.height;
 
          var ctx = canvas.getContext("2d");
          ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
          var dataURL = canvas.toDataURL();
          return dataURL;
        }
        var image = new Image();
        image.crossOrigin = '';
        image.src = img;
        var deferred=$.Deferred();
        if(img){
          image.onload =function (){
            deferred.resolve(getBase64Image(image));//将base64传给done上传处理
          }
          return deferred.promise();//问题要让onload完成后再return sessionStorage['imgTest']
        }
      }
      getBase64(imgSrc)
        .then(function(base64){
          console.log(base64);
        },function(err){
          console.log(err);
        });
}

//图片转base64解码
const getImgData = function(){
	return ""
}

export default{
	getBase64Data,getImgData
}