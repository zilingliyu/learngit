/**
 * JS文件，对应services.jsp页面
 */
$(function() {
	
	//bind the click action to the query button
	$('#query-chart').bind('click', function(e){
		if($('#query-time').val().trim()=='') {
			alert('请选择时间');
			return;
		}
		var theButton = $(this);
		theButton.button('loading');
		//check the checked providers on the left div and get the combined value
		var proString = $('#providers').find('input:checkbox:checked').map(function() {
			if ($(this).val() != '')
				return $(this).val();
		}).get().join(",");
		//empty the canvas before using
		$('#charts-canvas').empty();
		//get the checked conditions and create a chart for each 
		$('#conditions').find('input:checkbox:checked').each(function(){
			if ($(this).val() != ''){
				var id = $(this).val();
				var idChart = $(this).val()+"-chart";
				$('#charts-canvas').append('<div id="'+idChart+'" class="col-md-12 col-sm-12" style="height:400px;"></div>');
			//	alert(proString);
				$.ajax({//閫氳繃ajax璇锋眰锛屽緱鍒版暟鎹紙涓�埇涓簀son鏁版嵁锛�
					"dataType": 'json',//娉ㄦ剰鍒囧洖jsonp
					"type":'post',
					"url": 'getDatas.do',
					"data":{'key':id,'providers':proString,'time':$('#query-time').val().trim()},
					"success": function(dataMap){
						//load the chart
						loadTheChart(id,idChart,proString,dataMap);
						theButton.button('reset');
					},
					"error":function(e){
						alert('缃戠粶閿欒锛�);
					}
				} );				
			}
		});
		
	});
	
	$('.reservation').datepicker({
		format: "yyyy-mm",
		weekStart: 1,
		startView: 1,
		minViewMode: 1,
		todayBtn: "linked",
		language: "zh-CN",
		toggleActive: true
	});
});

/**璇ュ嚱鏁颁腑鐨刢ase瀵瑰簲浜巗ervices.jsp涓殑checkbox鐨剉alue鍊�
 * 
 * @param id
 * @param idChart
 * @param proString ???浠庡摢閲屾潵锛�
 * @param dataMap  ???浠庡摢閲屾潵锛�
 * 
 * legend鏄痚chart椤堕儴鐨勫浘渚�
 * xAxis鏄痚chart妯酱
 * 
 */
function loadTheChart(id,idChart,proString,dataMap){
	var legendData = proString.split(',');
	var xAxisData = proString.split(',');
	var seriesData=[];
	
	//鍏堥�杩噑witch-case鏉ョ粍瑁卐chart鐨勫姩鎬佷釜鎬у寲鍐呭
	switch (id) {
	case 'coremark':
		legendData=['CPU鎬ц兘鏁堢巼'];
		titleText="CPU鏁堣兘";
		subText="鍗曚綅锛氬垎鏁皊core";
		var seriesInnerxAxisData = [];
		for(var index in xAxisData){
			seriesInnerxAxisData.push(dataMap[xAxisData[index]][0]); //锛燂紵锛�
		}
		seriesData.push({
            name:'CPU鎬ц兘鏁堢巼', //娉ㄦ剰name蹇呴』绛変簬鍓嶉潰鎸囧畾鐨刲egendData
            type:'bar',
            data:seriesInnerxAxisData,
            markPoint : {
                data : [
                    {type : 'max', name: '鏈�ぇ鍊�},
                    {type : 'min', name: '鏈�皬鍊�}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '骞冲潎鍊�}
                ]
            }
    	});
		break;
	case 'unixbench':
		legendData=['OS缁煎悎寰楀垎'];
		titleText="鎿嶄綔绯荤粺鎬ц兘";
		subText="鍗曚綅锛氬垎鏁皊core";
		var seriesInnerxAxisData = [];
		for(var index in xAxisData){
			seriesInnerxAxisData.push(dataMap[xAxisData[index]][0]); //锛燂紵锛�
		}
		seriesData.push({
            name:'OS缁煎悎寰楀垎',
            type:'bar',
            data:seriesInnerxAxisData,
            markPoint : {
                data : [
                    {type : 'max', name: '鏈�ぇ鍊�},
                    {type : 'min', name: '鏈�皬鍊�}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '骞冲潎鍊�}
                ]
            }
    	});
		break;
	case 'oltp':
		legendData=['Transaction_ID'];
		titleText="鍏崇郴鍨嬫暟鎹簱浜嬪姟鐜�;
		subText="鍗曚綅锛歵ransactions per sec";
		var seriesInnerxAxisData = [];
		for(var index in xAxisData){
			seriesInnerxAxisData.push(dataMap[xAxisData[index]][0]); //锛燂紵锛�
		}
		seriesData.push({
            name:'Transaction_ID',
            type:'bar',
            data:seriesInnerxAxisData,
            markPoint : {
                data : [
                    {type : 'max', name: '鏈�ぇ鍊�},
                    {type : 'min', name: '鏈�皬鍊�}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '骞冲潎鍊�}
                ]
            }
    	});
		break;
	case 'scimark':
		xAxisData=['鍦烘櫙1','鍦烘櫙2','鍦烘櫙3','鍦烘櫙4'];
		titleText="绉戝璁＄畻";
		subText="鍗曚綅锛歮illion floating-point operations per sec";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	case 'hpcc':
		xAxisData=['闅忔満璁垮瓨*100','鍐呭瓨鍗曞厓鎷疯礉','鍐呭瓨鍗曞厓涔樻硶','鍐呭瓨鍗曞厓鍔犳硶','鍐呭瓨鍗曞厓缁勫悎'];
		titleText="鍐呭瓨鏁堢巼";
		subText="鍗曚綅锛欸B per sec";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	case 'ping':
		xAxisData=['鏈�皬寤惰繜','鏈�ぇ寤惰繜','骞冲潎寤惰繜','寤惰繜鏂瑰樊'];
		titleText="缃戠粶寤惰繜";
		subText="鍗曚綅锛歮illisecond";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	case 'netperf':
		xAxisData=['鍗昑CP杩炴帴鍚炲悙','澶歍CP杩炴帴鍚炲悙','TCP鎵归噺浼犺緭鍚炲悙','UDP璇锋眰鍝嶅簲鍚炲悙'];
		titleText="缃戠粶鍚炲悙";
		subText="鍗曚綅锛歵ransactions per sec";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	case 'fio':
		xAxisData=['搴忓垪鍐橧OPS','搴忓垪璇籌OPS','闅忔満鍐橧OPS','闅忔満璇籌OPS'];
		titleText="瀛樺偍鍚炲悙";
		subText="鍗曚綅锛歰perations per sec";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	case 'nosql':
		xAxisData=['鍐欏瘑闆嗗瀷璐熻浇','璇诲瘑闆嗗瀷璐熻浇'];
		titleText="NoSQL鏁版嵁搴撲簨鍔＄巼";
		subText="鍗曚綅锛歰perations per sec";
		for(var index in legendData){
			seriesData.push({
	            name:legendData[index],
	            type:'bar',
	            data:dataMap[legendData[index]],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '鏈�ぇ鍊�},
	                    {type : 'min', name: '鏈�皬鍊�}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '骞冲潎鍊�}
	                ]
	            }
	    	});
		}
		break;
	default:
		break;
	}
	
	//杩欓噷寮�鏍规嵁涓�鍖栫殑鍔ㄦ�鍐呭鏈�粓鐢熸垚echart锛屽叕鐢╫ption閫夐」锛岄渶瑕佸姩鎬佺敓鎴愮殑鍐呭瑙佷笅闈㈡敞閲�
    var myChart = echarts.init(document.getElementById(idChart),echarts_blue_theme); 
    var option = {
    	    title : {
    	        text: titleText,
    	        subtext: subText
    	    },
    	    tooltip : {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data:legendData //鍔ㄦ�鐢熸垚澶氫釜涓嶅悓绫诲埆
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {show: true, type: ['bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : xAxisData //鍔ㄦ�鐢熸垚x妯酱鍧愭爣
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : seriesData //鍔ㄦ�鐢熸垚绾佃酱鏁版嵁
    };
	 // 涓篹charts瀵硅薄鍔犺浇鏁版嵁 
    myChart.setOption(option);
	window.addEventListener("resize",function(){
			myChart.resize();
	});
}
