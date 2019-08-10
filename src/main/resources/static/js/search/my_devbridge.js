var banks= [
	{"value":"CDB","text":"国家开发银行","abbr":"gjkfyh","fullpinyin":"guojiakaifayinhang"},
    {"value":"ICBC","text":"中国工商银行","abbr":"zggsyh","fullpinyin":"zhongguogongshangyinhang"}
]
$(function(){
$('#autocp').autocomplete({
	lookup: banks,//本地查询
//    serviceUrl: "subs/bank.json",
    onSelect: function (suggestion) {
        console.log('You selected: ' + suggestion.value+ ', '  
        							 + suggestion.text + ', ' 
        							 + suggestion.abbr + ', ' 
        							 + suggestion.fullpinyin
        							 );
    }
});

$.ajax({
	



})

})