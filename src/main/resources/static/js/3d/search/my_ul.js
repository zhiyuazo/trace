function searchCity() {
    var searchCityName = $("#searchCityName").val();
    if (searchCityName == "") {
        $("ul li").show();
    } else {
        $("ul li").each(
            function() {
                var pinyin = $(this).attr("pinyin");
                var cityName = $(this).attr("cityName");
                if (pinyin.indexOf(searchCityName) != -1
                        || cityName.indexOf(searchCityName) != -1) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
    }
}
 
$(function(){
	$('#searchCityName').bind('input propertychange', function() {
	    searchCity();
	});
})