<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>APM 慢调用</title>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<link rel="stylesheet" href="css/jquery.dataTables.min.css" type="text/css" />
<link type="text/css" href="css/jquery.simple-dtpicker.css" rel="stylesheet" />
<script type="text/javascript" src="js/highcharts/jquery-1.8.3.min.js"></script>
<!-- <script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script> -->
<script type="text/javascript" src="js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="js/datetimepicker/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript">

var data_slow_call = [{"version":0,"agentId":"test1001","applicationId":"appName","agentStartTime":1462699069318,"traceAgentId":"test1001","traceAgentStartTime":1462699069318,"traceTransactionSequence":5,"spanId":-8244457100962795228,"parentSpanId":-1,"startTime":1462699466130,"elapsed":127499,"rpc":"/httpclient4/getTwitterUrlCount.pinpoint","serviceType":"TOMCAT","endPoint":"180.153.44.108:8090","apiId":-1,"annotationBoList":null,"flag":0,"errCode":1,"spanEventBoList":null,"collectorAcceptTime":1462699593649,"exceptionId":0,"exceptionMessage":null,"exceptionClass":null,"remoteAddr":"159.226.43.45","root":true,"transactionId":"test1001^1462699069318^5"},{"version":0,"agentId":"test1001","applicationId":"appName","agentStartTime":1462699069318,"traceAgentId":"test1001","traceAgentStartTime":1462699069318,"traceTransactionSequence":4,"spanId":-167382249182572870,"parentSpanId":-1,"startTime":1462699123898,"elapsed":256162,"rpc":"/httpclient4/getGeoCode.pinpoint","serviceType":"TOMCAT","endPoint":"180.153.44.108:8090","apiId":-1,"annotationBoList":null,"flag":0,"errCode":0,"spanEventBoList":null,"collectorAcceptTime":1462699380083,"exceptionId":0,"exceptionMessage":null,"exceptionClass":null,"remoteAddr":"159.226.43.45","root":true,"transactionId":"test1001^1462699069318^4"},{"version":0,"agentId":"test1001","applicationId":"appName","agentStartTime":1462699069318,"traceAgentId":"test1001","traceAgentStartTime":1462699069318,"traceTransactionSequence":3,"spanId":1022438039493983549,"parentSpanId":-1,"startTime":1462699112204,"elapsed":3010,"rpc":"/sleep3.pinpoint","serviceType":"TOMCAT","endPoint":"180.153.44.108:8090","apiId":-1,"annotationBoList":null,"flag":0,"errCode":0,"spanEventBoList":null,"collectorAcceptTime":1462699115218,"exceptionId":0,"exceptionMessage":null,"exceptionClass":null,"remoteAddr":"159.226.43.45","root":true,"transactionId":"test1001^1462699069318^3"},{"version":0,"agentId":"test1001","applicationId":"appName","agentStartTime":1462699069318,"traceAgentId":"test1001","traceAgentStartTime":1462699069318,"traceTransactionSequence":2,"spanId":-5728347098437728309,"parentSpanId":-1,"startTime":1462699098174,"elapsed":10053,"rpc":"/consumeCpu.pinpoint","serviceType":"TOMCAT","endPoint":"180.153.44.108:8090","apiId":-1,"annotationBoList":null,"flag":0,"errCode":0,"spanEventBoList":null,"collectorAcceptTime":1462699108229,"exceptionId":0,"exceptionMessage":null,"exceptionClass":null,"remoteAddr":"159.226.43.45","root":true,"transactionId":"test1001^1462699069318^2"},{"version":0,"agentId":"test1001","applicationId":"appName","agentStartTime":1462699069318,"traceAgentId":"test1001","traceAgentStartTime":1462699069318,"traceTransactionSequence":0,"spanId":1394612279845866822,"parentSpanId":-1,"startTime":1462699085667,"elapsed":748,"rpc":"/","serviceType":"TOMCAT","endPoint":"180.153.44.108:8090","apiId":-1,"annotationBoList":null,"flag":0,"errCode":0,"spanEventBoList":null,"collectorAcceptTime":1462699086690,"exceptionId":0,"exceptionMessage":null,"exceptionClass":null,"remoteAddr":"159.226.43.45","root":true,"transactionId":"test1001^1462699069318^0"}];

var data_slow_call_detail = {
    "applicationName": "/sleep3.pinpoint",
    "transactionId": "agent1001^1463212891434^0",
    "agentId": "agent1001",
    "applicationId": "agent",
    "callStackStart": 1463212905599,
    "callStackEnd": 3592,
    "completeState": "Complete",
    "callStackIndex": {
        "depth": 0,
        "begin": 1,
        "end": 2,
        "excludeFromTimeline": 3,
        "applicationName": 4,
        "tab": 5,
        "id": 6,
        "parentId": 7,
        "isMethod": 8,
        "hasChild": 9,
        "title": 10,
        "arguments": 11,
        "executeTime": 12,
        "gap": 13,
        "elapsedTime": 14,
        "barWidth": 15,
        "simpleClassName": 16,
        "apiType": 17,
        "agent": 18,
        "isFocused": 19,
        "hasException": 20
    },
    "callStack": [
        ["", 1463212905599, 1463212909191, false, "agent", 0, "1", "", true, true, "invoke(Request request, Response response)", "\/sleep3.pinpoint", "16:01:45 599", "0", "3,592", "1", "StandardHostValve", "TOMCAT", "agent1001", true, false],
        ["", 0, 0, true, "", 1, "2", "1", false, false, "REMOTE_ADDRESS", "192.168.25.1", "", "", "", "", "", "", "", false, false],
        ["", 1463212905861, 1463212909190, false, "agent", 1, "3", "1", true, false, "doGet(HttpServletRequest request, HttpServletResponse response)", "", "16:01:45 861", "262", "3,329", "1", "FrameworkServlet", "SPRING", "agent1001", false, false],
        ["", 1463212905904, 1463212908906, false, "agent", 2, "4", "3", true, false, "sleep3()", "", "16:01:45 904", "43", "3,002", "1", "SimpleController", "SPRING_BEAN", "agent1001", false, false]
    ],
    "applicationMapData": {
        "nodeDataArray": [{
            "key": "agent^USER",
            "applicationName": "USER",
            "category": "USER",
            "serviceType": "USER",
            "serviceTypeCode": "2",
            "isWas": false,
            "histogram": {
                "1s": 0,
                "3s": 0,
                "5s": 1,
                "Slow": 0,
                "Error": 0
            },
            "totalCount": 1,
            "errorCount": 0,
            "slowCount": 1,
            "hasAlert": false,
            "agentHistogram": {},
            "timeSeriesHistogram": [{
                "key": "1s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "3s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "5s",
                "values": [
                    [0, 0],
                    [1463212860000, 1]
                ]
            }, {
                "key": "Slow",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "Error",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }],
            "agentTimeSeriesHistogram": {},
            "serverList": {},
            "instanceCount": 0
        }, {
            "key": "agent^TOMCAT",
            "applicationName": "agent",
            "category": "TOMCAT",
            "serviceType": "TOMCAT",
            "serviceTypeCode": "1010",
            "isWas": true,
            "histogram": {
                "1s": 0,
                "3s": 0,
                "5s": 1,
                "Slow": 0,
                "Error": 0
            },
            "totalCount": 1,
            "errorCount": 0,
            "slowCount": 1,
            "hasAlert": false,
            "agentHistogram": {
                "agent1001": {
                    "1s": 0,
                    "3s": 0,
                    "5s": 1,
                    "Slow": 0,
                    "Error": 0
                }
            },
            "timeSeriesHistogram": [{
                "key": "1s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "3s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "5s",
                "values": [
                    [0, 0],
                    [1463212860000, 1]
                ]
            }, {
                "key": "Slow",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "Error",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }],
            "agentTimeSeriesHistogram": {
                "agent1001": [{
                    "key": "1s",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "3s",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "5s",
                    "values": [
                        [0, 0],
                        [1463212860000, 1]
                    ]
                }, {
                    "key": "Slow",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "Error",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }]
            },
            "serverList": {},
            "instanceCount": 0
        }],
        "linkDataArray": [{
            "key": "agent^USER~agent^TOMCAT",
            "from": "agent^USER",
            "to": "agent^TOMCAT",
            "toAgent": [],
            "sourceInfo": {
                "applicationName": "agent",
                "serviceType": "USER",
                "serviceTypeCode": 2,
                "isWas": false
            },
            "targetInfo": {
                "applicationName": "agent",
                "serviceType": "TOMCAT",
                "serviceTypeCode": 1010,
                "isWas": true
            },
            "filterApplicationName": "agent",
            "filterApplicationServiceTypeCode": 1010,
            "filterApplicationServiceTypeName": "TOMCAT",
            "totalCount": 1,
            "errorCount": 0,
            "slowCount": 1,
            "histogram": {
                "1s": 0,
                "3s": 0,
                "5s": 1,
                "Slow": 0,
                "Error": 0
            },
            "sourceHistogram": {
                "agent1001": {
                    "1s": 0,
                    "3s": 0,
                    "5s": 1,
                    "Slow": 0,
                    "Error": 0
                }
            },
            "targetHistogram": {
                "agent1001": {
                    "1s": 0,
                    "3s": 0,
                    "5s": 1,
                    "Slow": 0,
                    "Error": 0
                }
            },
            "timeSeriesHistogram": [{
                "key": "1s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "3s",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "5s",
                "values": [
                    [0, 0],
                    [1463212860000, 1]
                ]
            }, {
                "key": "Slow",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }, {
                "key": "Error",
                "values": [
                    [0, 0],
                    [1463212860000, 0]
                ]
            }],
            "sourceTimeSeriesHistogram": {
                "agent1001": [{
                    "key": "1s",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "3s",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "5s",
                    "values": [
                        [0, 0],
                        [1463212860000, 1]
                    ]
                }, {
                    "key": "Slow",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }, {
                    "key": "Error",
                    "values": [
                        [0, 0],
                        [1463212860000, 0]
                    ]
                }]
            },
            "hasAlert": false
        }]
    }
};



function getPercent(numA, numB){
    if(numA=="" || numB==""){
        return "-";
    }else{
        numA = parseFloat(numA.replace(",",""));
        numB = parseFloat(numB.replace(",",""));
        var total = numA+numB;
        return total <= 0 ? "0%" : (Math.round(numB / total * 10000)/100);
    }
}



function getSlowCallDetailData(traceID, timeStamp, data){
    var callstack = data["callStack"];
    var list_detail_items = [];
    $.each(callstack, function(index, item){
        var row = {"time_begin":item[1], "time_end":item[2], "appName":item[4], "tag":item[5], "id":item[6], "parentId":item[7], "title":item[10], "args":item[11], "formatTime":item[12], "gap":item[13], "execTime":item[14], "simpleClassName":item[16], "serviceType":item[17], "agent":item[18], "hasChild":item[19], "hasException":item[20]};
        //code_row = "<tr class='item_detail'><td colspan='8'><div class='poplayer'>"+row['time_begin']+"</div></td></tr>";
        //var code_row = "<tr><td>"+row['title']+"</td><td>"+row['args']+"</td><td>"+row['formatTime']+"</td><td>"+row['gap']+"</td><td>"+row['execTime']+"</td><td>100%</td><td>"+row['simpleClassName']+"</td><td>"+row['serviceType']+"</td><td>"+row['agent']+"</td><td>"+row['appName']+"</td></tr>"
        list_detail_items.push(row);
    });
    return list_detail_items;
};
function getSlowCallData(data){
    var list_items = [];
    $.each(data, function(index, item){
        var code_item = {
            "index": (index+1),
            "compent": item.compent,
            "uptime": item.uptime,
            "data_Support": item.data_Support,
            "data_Confidence": item.data_Confidence,
            "degree": item.degree,
            "transactionId": item.transactionId,
            "time": {
                "timeFormat": Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', item.startTime),
                "timeStamp": item.startTime
            }
        }
        list_items.push(code_item);
    });
    return list_items;
};


function format(d){
    console.log("/stewardweb/transactionInfo.do?traceId="+d.transactionId+"&focusTimestamp="+d.time.timeStamp);
    var json_url = "/stewardweb/transactionInfo.do?traceId="+d.transactionId+"&focusTimestamp="+d.time.timeStamp;
    var result = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px; width:100%; border:1px red solid;">'+
            '<tr>'+
                '<td>调用方法</td>'+
                '<td>参数</td>'+
                '<td>调用时间</td>'+
                '<td>间隔时间(ms)</td>'+
                '<td>执行时间(ms)</td>'+
                '<td>时间占比(%)</td>'+
                '<td>组件类型</td>'+
                '<td>应用名称</td>'+
                '<td>探针Id</td>'+
                '<td>应用名称</td>'+
            '</tr>';
    $.ajax({
        type:"GET",
        dataType:"JSON",
        async:false,
        url:json_url,
        success:function(data) {
            var data_slow_call_detail = data;
            //console.log('slowCallDetailData:'+data_slow_call_detail);
            var list_detail_items = getSlowCallDetailData(d.transactionId, d.time.timeStamp, data_slow_call_detail);
           
            $.each(list_detail_items, function(index, item){
                result = result +
                    '<tr>'+
                        '<td>'+item['title']+'</td>'+
                        '<td>'+item['args']+'</td>'+
                        '<td>'+item['formatTime']+'</td>'+
                        '<td>'+item['gap']+'</td>'+
                        '<td>'+item['execTime']+'</td>'+
                        '<td>'+getPercent(item['gap'], item['execTime'])+'</td>'+
                        '<td>'+item['simpleClassName']+'</td>'+
                        '<td>'+item['serviceType']+'</td>'+
                        '<td>'+item['agent']+'</td>'+
                        '<td>'+item['appName']+'</td>'+
                    '</tr>';
            });
            result += '</table>';
        }

    });
    return result;
};


function getDataByTime(time_start, time_end){
    var json_url = "/stewardweb/getScatterData.do?agentID=" + agentID + "&from="+time_start+"&to="+time_end+"&limit=5000&v=2";
    $.getJSON(json_url,function(data){  
        $.each(data,function(i,item){  
            alert(item);
        });
    }); 
};


$(document).ready(function(){
	Highcharts.setOptions({
        global: {                                                               
            useUTC: false                                                       
        }                                                                       
    });
	//获取慢调用数据，将其存储到本地mysql中
	//var json_url = '/stewardweb/transactionSlowMetadata.do?agentID=' + agentID + '&from=' + sttime + '&to=' + endtime + '&limit=5000&v=2&threshold=' + threshold;
	var json_url = '/stewardweb/gettransactionSlowMetadata.do?agentID=' + agentID + '&from=' + sttime + '&to=' + endtime + '&limit=5000&v=2&threshold=' + threshold;
    $.getJSON(json_url,function(data,status){
    	var data_slow_call = data;
        var list_items = getSlowCallData(data_slow_call);
        
        var table = $('#table_root').DataTable({
            "data": list_items,
            "columns":[
                {
                  "className":      'details-control',
                  "orderable":      false,
                  "data":           null,
                  "defaultContent": ''
                 },
                {"data": "index"},
                {"data": "compent"},
                {"data": "uptime"},
                {"data": "data_Support"},
                {"data": "data_Confidence"},
                {"data": "degree"},
                {"data": "transactionId"},
                {"data": "time.timeFormat"} 
            ],
            "columnDefs":[{
            	"data":"degree",
                "render":function(data,type,row){
                	if(data=="high")
                     return "<img src='./images/icons/red.png'/>";
                	else if (data=="mid")
                        return "<img src='./images/icons/purple.png'/>";
                		else
                        return "<img src='./images/icons/green.png'/>";
                },
                "targets":7
            }
            ],
            "order": [[1, 'asc']]
        });
        // Add event listener for opening and closing details
        $('#table_root tbody').on('click', 'td.details-control', function(){
            var tr = $(this).closest('tr');
            var row = table.row( tr );
     
            if (row.child.isShown()){
                // This row is already open - close it
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                // Open this row
                row.child(format(row.data())).show();
                tr.addClass('shown');
            }
        });
    });
});


function submitSearchForm(){
    var time_from = $('input[name=from]').val();
    var time_to = $('input[name=to]').val();
    stamp_from = new Date(time_from);
    stamp_to = new Date(time_to);
    sttime = stamp_from.getTime();
    endtime = stamp_to.getTime();
    var new_url = '/stewardweb/datamining.do?agentID='+ agentID+'&sttime=' + sttime + '&endtime=' + endtime + '&limit=5000&v=2&threshold=' + threshold;
    window.location.href = new_url;

}
</script>

</head>

<body>
<div class="bodywrapper">
    <div class="topheader">
        <div class="left">
	    <img src="images/logo_1.png" style="float:left" />
            <h1 class="logo"><a href="/stewardweb/index.do">云眼APM<span></span></a></h1>
            <span class="slogan">后台管理系统</span>
            
            <div class="search">
                <!-- <form id="searchForm" action="getScatterData.do" method="get"> -->
                    <input type="text" id="form_from" name="from" value="" />
                    <input type="text" id="form_to" name="to" value="" />
                    <input type="hidden" id="form_limit" name="limit" value="5000" />
                    <input type="hidden" id="form_v" name="v" value="2" />                   
                    <!-- <input type="text" name="keyword" id="keyword" value="请输入" /> -->
                    <button class="submitbutton" onClick="submitSearchForm();"></button>
                <!-- </form> -->
            </div><!--search-->
            
            <br clear="all" />
            
        </div><!--left-->
        
        <div class="right">
        	<!--<div class="notification">
                <a class="count" href="notifications.html"><span>9</span></a>
        	</div>
			-->
        	<div class="userinfo">
            	<img src="images/thumbs/avatar.png" alt="" />
                <span>${sessionScope.userInfo.username}</span>
            </div><!--userinfo-->
            
            <div class="userinfodrop">
                <div class="userdata">
                    <h4>${sessionScope.userInfo.username}</h4>
                    <span class="email">${sessionScope.userInfo.email}</span>
                    <ul>
                        <li><a href="logout.do">退出</a></li>
                    </ul>
                </div><!--userdata-->
            </div><!--userinfodrop-->
        </div><!--right-->
    </div><!--topheader-->
    
    
    <div class="header">
    	<ul class="headermenu">
            <li><a onclick='navjump(0)'><span class="icon icon-flatscreen"></span>业务流拓扑</a></li>
            <li><a onclick='navjump(1)'><span class="icon icon-pencil"></span>业务流性能表现</a></li>
            <li><a onclick='navjump(2)'><span class="icon icon-chart"></span>应用级负载</a></li>
            <li><a onclick='navjump(3)'><span class="icon icon-speech"></span>慢调用</a></li>
            <li><a onclick='navjump(4)'><span class="icon icon-message"></span>出错调用</a></li>
            <li class="current"><a onclick='navjump(5)'><span class="icon icon-datamining"></span>数据挖掘分析</a></li>
            <li><a onclick='navjump(6)'><span class="icon icon-callflow"></span>分布式调用流</a></li>
            
        </ul>
    </div><!--header-->
    
    <div class="pageheader">
    	<h1 class="pagetitle contenttitle2">数据分析</h1>
        <!--
        <span class="pagedesc">An example of graphs &amp; charts. A page without left menu.</span>
        
        <ul class="hornav">
        	<li class="current"><a href="#charts">Charts</a></li>
            <li><a href="#statistics">Statistics</a></li>
        </ul>
        --> 
    </div><!--pageheader-->
    
    <div class="contentwrapper">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="table_root">
            <thead>
                <tr>
                    <th></th>
                    <th align="left" >#</th>
                    <th align="left">调用组件</th>
                    <th align="left">响应时间(ms)</th>
                    <th align="left">支持度</th>
                    <th align="left">置信度</th>
                    <th align="left">待优级别</th>
                    <th align="left"></th>
                    <th align="left"></th>
                    <th align="left"></th>
                    

                </tr>
            </thead>
        </table>   
    </div><!--contentwrapper-->
    
    
</div><!--bodywrapper-->

</body>
</html>
