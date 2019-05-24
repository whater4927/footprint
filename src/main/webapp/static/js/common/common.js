amtFormatter = function(value, row, index){
	return parseFloat (value).toFixed(2);
};

rationFormatter = function(value, row, index){
	return (parseFloat (value).toFixed(2)) + "%" ;
};

dateFormatter = function(value, row, index){
	return value.substr(0,10)  ;
};