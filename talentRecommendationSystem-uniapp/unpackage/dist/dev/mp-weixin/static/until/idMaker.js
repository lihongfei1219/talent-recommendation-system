const idMaker = function(){
	//id保证唯一性
	var timestamp = (new Date()).valueOf();
	return timestamp;
}



export default{
	idMaker
}