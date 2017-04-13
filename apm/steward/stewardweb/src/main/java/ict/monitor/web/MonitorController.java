package ict.monitor.web;

import ict.monitor.bean.Agent;
import ict.monitor.bean.DataMining;
import ict.monitor.bean.PinpointTarget;
import ict.monitor.bean.SlowCallData;
import ict.monitor.bean.User;
import ict.monitor.collection.entity.DevInfoEntity;
import ict.monitor.context.WebContext;
import ict.monitor.dao.AgentDao;
import ict.monitor.dao.DevInfoDao;
import ict.monitor.dao.SlowCallDataDao;
import ict.monitor.dao.appUserDao;
import ict.monitor.datamining.Apriori_transcation_compression;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class MonitorController {
	private static Logger logger = LogManager.getLogger(MonitorController.class.getName());
	@Autowired
	private AgentDao agentDao;
	@Autowired
	private DevInfoDao devInfoDao;
	@Autowired
	private SlowCallDataDao slowCallDataDao;
	
	String I;
	String T;
	String R;
	
	@RequestMapping(value = "/monitorMachineList.do")
	public String monitorMachineList(HttpServletRequest request, Model model, String agentID) {
		User userInfo = (User) request.getSession().getAttribute("userInfo");
		ArrayList<Agent> list = agentDao.findAgentByUserIDAndAgentID(userInfo.getId(), agentID);
		List<DevInfoEntity> devs = devInfoDao.findDevInfo(agentID);
		model.addAttribute("agents", list);
		model.addAttribute("devs", devs);
		return "monitorMachineList";
	}
	
	
	@RequestMapping(value = "/findDevInfo.do")
	@ResponseBody
	public DevInfoEntity findDevInfo(String agentID, String devID) {
		DevInfoEntity findDevInfoByAgentIDAndDevID = devInfoDao.findDevInfoByAgentIDAndDevID(agentID,devID);
		return findDevInfoByAgentIDAndDevID;
	}
	
	// 跳转到websocket界面
	@RequestMapping(value = "/pushData.do")
	public String pushDate(HttpServletRequest request, String agentID, Model model) {
		model.addAttribute("agentID", agentID);
		return "websocket";
	}

	@RequestMapping(value = "/transactionErrorMetadata.do")
	@ResponseBody
	public String transactionErrorMetadata(String agentID, long from, long to, int limit) {
		String url = WebContext.PINPOINT + "/transactionErrorMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit;
		return getJsonFromPinpoint(url);
	}
//tian(获取慢调用数据）
//	@RequestMapping(value = "/gettransactionSlowMetadata.do")
//	@ResponseBody
//	public String gettransactionSlowMetadata(String agentID, long from, long to, int limit, int threshold) {
//		System.out.println("*********************************");
//		String url = WebContext.PINPOINT + "/gettransactionSlowMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit + "&threshold=" + threshold;
//		return getJsonFromPinpoint(url);
//	}
//==============================================================================================
//	@RequestMapping(value = "/gettransactionSlowMetadata.do")
//	@ResponseBody
//	public String gettransactionSlowMetadata(String agentID, long from, long to, int limit, int threshold) {
//		System.out.println("*********************************");
//		String url = WebContext.PINPOINT + "/gettransactionSlowMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit + "&threshold=" + threshold;
//		String json = getJsonFromPinpoint(url);
//		ObjectMapper mapper = new ObjectMapper();
//		//System.out.println(json);
//		List<SlowCallData> agentList = new ArrayList<>();
//		try {
//			agentList = mapper.readValue(json, new TypeReference<List<SlowCallData>>() {});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		for (SlowCallData slowCallData : agentList) 
//		{
//			System.out.println("*********************************"+slowCallData.getRpc());
//			slowCallDataDao.insert(slowCallData);
//		}
//		return getJsonFromPinpoint(url);
//	}
//===============================生成数据集========================================================
//	@RequestMapping(value = "/gettransactionSlowMetadata.do")
//	@ResponseBody
//	public String gettransactionSlowMetadata(String agentID, long from, long to, int limit, int threshold) throws IOException {
//
//		String url = WebContext.PINPOINT + "/gettransactionSlowMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit + "&threshold=" + threshold;
//		String json = getJsonFromPinpoint(url);
//		ObjectMapper mapper = new ObjectMapper();
//		//System.out.println(json);
//		PrintWriter pw = new PrintWriter( new FileWriter( "H:/data/dataset.txt" ) );
//		Apriori_transcation_compression datamining=new Apriori_transcation_compression();
//		List<SlowCallData> agentList = new ArrayList<>();
//		try {
//			agentList = mapper.readValue(json, new TypeReference<List<SlowCallData>>() {});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		for (SlowCallData slowCallData : agentList) 
//		{
//		//	System.out.println("*********************************"+slowCallData.getRpc());
//		//	slowCallDataDao.insert(slowCallData);
//			if(Integer.parseInt(slowCallData.getElapsed())>200)
//    			pw.println( slowCallData.getRpc()+","+"5up");
//    		else
//    			if(Integer.parseInt(slowCallData.getElapsed())>30)//记录3s-5s以内的数据
//    				pw.println( slowCallData.getRpc()+","+"3sup");
//    			else
//    				if(Integer.parseInt(slowCallData.getElapsed())>10)//记录1s-3s以内的数据
//    					pw.println( slowCallData.getRpc()+","+"1sup");           
//		}
//		pw.close( );
//		datamining.dataMining();
//		return getJsonFromPinpoint(url);
//	}

	//===============================(數據挖掘)测试 生成数据集========================================================
	@RequestMapping(value = "/gettransactionSlowMetadata.do")
	@ResponseBody
	public List<DataMining> gettransactionSlowMetadata(String agentID, long from, long to, int limit, int threshold) throws IOException {

		System.out.println("===============进入数据挖掘分析阶段===============================");
		String url = WebContext.PINPOINT + "/gettransactionSlowMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit + "&threshold=" + threshold;
		String json = getJsonFromPinpoint(url);
		System.out.println("++++++"+json+"++++++");
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter pw = new PrintWriter( new FileWriter( "C:/data/dataset.txt" ) );//生成一个待测试的测试集集
		Apriori_transcation_compression datamining=new Apriori_transcation_compression();//初始化一个数据挖掘对象
		List<SlowCallData> agentList = new ArrayList<>();
		try {
			agentList = mapper.readValue(json, new TypeReference<List<SlowCallData>>() {});//将json数据换成list格式
		} catch (IOException e) {
			e.printStackTrace();
		}
//		for (SlowCallData slowCallData : agentList) //扫描获取的每个对象
//		{
////			System.out.println("*********************************"+slowCallData.getElapsed());
////			slowCallDataDao.insert(slowCallData);
//			if(Integer.parseInt(slowCallData.getElapsed())>5)//记录5s以上的数据
//    			pw.println( slowCallData.getRpc()+","+"5sup");
//    		else
//    			if(Integer.parseInt(slowCallData.getElapsed())>3)//记录3s-5s以内的数据
//    				pw.println( slowCallData.getRpc()+","+"3sup");
//    			else
//    				if(Integer.parseInt(slowCallData.getElapsed())>0)//记录1s-3s以内的数据
//    					pw.println( slowCallData.getRpc()+","+"1sup");           
//		}
		
		
		for (SlowCallData slowCallData : agentList) //扫描获取的每个对象
		{
//			System.out.println("*********************************"+slowCallData.getElapsed());
//			slowCallDataDao.insert(slowCallData);
			if(Integer.parseInt(slowCallData.getElapsed())>5)//记录5s以上的数据
    			pw.println( slowCallData.getRpc()+","+"5sup"+","+slowCallData.getTransactionId()+","+slowCallData.getStartTime());
    		else
    			if(Integer.parseInt(slowCallData.getElapsed())>3)//记录3s-5s以内的数据
    				pw.println( slowCallData.getRpc()+","+"3sup"+","+slowCallData.getTransactionId()+","+slowCallData.getStartTime());
    			else
    				if(Integer.parseInt(slowCallData.getElapsed())>0)//记录1s-3s以内的数据
    					pw.println( slowCallData.getRpc()+","+"1sup"+","+slowCallData.getTransactionId()+","+slowCallData.getStartTime());           
		}
		pw.close( );
		List<DataMining> list=new ArrayList<DataMining>(); 
		list =datamining.dataMining();//进行数据挖掘分析
		System.out.println("====================================================");
		for(DataMining data :list)//看得出的数据是自己想要的
		{
			System.out.println("Support="+data.getData_Support()+"  "+"degree="+data.getDegree());
		}
	
//		System.out.println(list);
		return list;
	}

	
//==============================================================================================
//tian(获取满调用数据)

//慢調用
	@RequestMapping(value = "/transactionSlowMetadata.do")
	@ResponseBody
	public String transactionSlowMetadata(String agentID, long from, long to, int limit, int threshold) {
		String url = WebContext.PINPOINT + "/transactionSlowMetadata.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit + "&threshold=" + threshold;
		System.out.println(url);
		System.out.println(getJsonFromPinpoint(url));
		return getJsonFromPinpoint(url);
	}
	
//分布式调用流
	@RequestMapping(value = "/transactionmetadata.do")
	@ResponseBody
	public String transactionmetadata(String agentID, long from, long to, int limit) {
		
		String objAllData=scatterData(agentID, from, to, limit);//获取该段时间内的所有信息
		Map map=new HashMap();
		int index=-2;
		String [] strs=objAllData.split(":");
		System.out.println(strs[2]);
		String [] strs1=strs[2].split("\\[");
		for(int i=0;i<strs1.length;i++){
			String strs2[]=strs1[i].split(",");
//			System.out.println(strs1[i]);
//			System.out.println("******"+strs2.length+"*****");
			if(strs2.length>1){
				for(int j=0;j<strs2.length-1;j++){
//					System.out.println(strs2[j]);
					if(j%5==0){
						map.put("T"+index, strs2[j]);
//						System.out.println(strs2[j]);
//						map.put("T"+index, "1490924631471");
					}
					else if(j%5==1){
						map.put("R"+index, strs2[j]);
//						map.put("R"+index, "6");
					}
					else if(j%5==2){
						map.put("I"+index,strs2[j].split("\"")[1]);
//						map.put("I"+index, "29acb705-644a-45e8^1488533015320^379970");
//						System.out.println(strs2[j].split("\"")[1]);
					}
					
				}
				index++;
			}else{
				index++;
			}
			
		}
		
//		for(int i=0;i<4;i++){
//			map.put("I"+i, "29acb705-644a-45e8^1488533015320^379970");
//			map.put("T"+i, "1490924631471");
//			map.put("R"+i, "6");
//		}
//		map.put("I0", "29acb705-644a-45e8^1488533015320^379970");
//		map.put("T0", "1490924631471");
//		map.put("R0", "6");
//		
//		map.put("I1", "29acb705-644a-45e8^1488533015320^403238");
//		map.put("T1", "1491048326606");
//		map.put("R1", "5");
		
		String url = WebContext.PINPOINT + "/transactionmetadata.pinpoint";
		System.out.println(url);
//		System.out.println(getJsonFromPinpointPost(url,map));
		return getJsonFromPinpointPost(url,map);
	}
	
	
	
	
	
	@RequestMapping(value = "/getServerMapData.do")
	@ResponseBody
	public String serverMapData(String agentID, long from, long to, String serviceTypeName) {
		String url = WebContext.PINPOINT + "/getServerMapData.pinpoint?applicationName=" + agentID + "&from=" + from + "&to=" + to +  "&serviceTypeName=" + serviceTypeName;
		System.out.println(url);
		String appurl="http://180.153.44.152:9009/o?api_key=cbf2567d0c08f9aa4b504a1e4d6a6ac0&app_id=5876f6b5d6c3c8d6fa808d21&method=total_users&metric=users&period=30days";
		String appStr=getJsonFromPinpoint(appurl);
		String appusercount="0";//app用户的访问量
		System.out.println(appStr);
		
		
		if(agentID.equals("appPhone")){
			
			ObjectMapper mapper=new ObjectMapper();//新建一个对象用来存储getJsonFromPinpoint(appurl)字符串中得到的对象
			List list=new ArrayList();//将得到的对象放到list中
			try {
				list=mapper.readValue(appStr,new TypeReference<List>(){});//将appStr中得到的东西放到list中
				System.out.println(list.get(0));
				Map map=(Map) list.get(0);//将Object类型的对象转换成Map类型的对象便于取值
				System.out.println(map.get("u"));//取到的是Integer类型的数
//				appusercount= map.get("u").toString();//将Integer类型的数转换成String类型的数
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String app_source="app_android";
			String app_target=agentID+"^TOMCAT";
			String app_applicationName="android";
			String app_serviceType="ANDROID";
//			String appusercount="2";
			String appAddAfter=null;//用來接收添加app信息后的字符串
			try {
				JSONObject dataJson=new JSONObject(getJsonFromPinpoint(url));
				JSONObject applicationMapData=dataJson.getJSONObject("applicationMapData");
				applicationMapData.put("appusercount",appusercount);
				applicationMapData.put("app_source",app_source);
				applicationMapData.put("app_target", app_target);
				applicationMapData.put("app_applicationName", app_applicationName);
				applicationMapData.put("app_serviceType", app_serviceType);
				
				appAddAfter=dataJson.toString();
				System.out.println("####"+appAddAfter+"#####");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return appAddAfter;
		}
//		System.out.println(getJsonFromPinpoint(url));
		return getJsonFromPinpoint(url);
	}
	
	
	@RequestMapping(value = "/serviceTypeName.do")
	@ResponseBody
	public String serviceTypeName(HttpServletRequest request,String agentID) {
		User userInfo = (User) request.getSession().getAttribute("userInfo");
		ArrayList<Agent> userAgentList = agentDao.findAgentIDsByUserID(userInfo.getId());
		if (userAgentList == null || userAgentList.size() == 0) {
			return "";
		}
		String json = getJsonFromPinpoint(WebContext.PINPOINT + "/applications.pinpoint");
		ObjectMapper mapper = new ObjectMapper();
		List<PinpointTarget> agentList = new ArrayList<>();
		try {
			agentList = mapper.readValue(json, new TypeReference<List<PinpointTarget>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (PinpointTarget pinpointTarget : agentList) {
			if(pinpointTarget.getApplicationName().equals(agentID)){
				return pinpointTarget.getServiceType();
			}
		}
		return "";
	}
	/**
	 * 得到监控的agentID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/applications.do")
	@ResponseBody
	public Set<String> applications(HttpServletRequest request) {
		User userInfo = (User) request.getSession().getAttribute("userInfo");
		ArrayList<Agent> userAgentList = agentDao.findAgentIDsByUserID(userInfo.getId());
		if (userAgentList == null || userAgentList.size() == 0) {
			return new HashSet<>();
		}
		String json = getJsonFromPinpoint(WebContext.PINPOINT + "/applications.pinpoint");
		ObjectMapper mapper = new ObjectMapper();
		List<PinpointTarget> agentList = new ArrayList<>();
		try {
			agentList = mapper.readValue(json, new TypeReference<List<PinpointTarget>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> allAgents = new HashSet<>();
		Set<String> allUserAgents = new HashSet<>();
		for (PinpointTarget pinpointTarget : agentList) {
			allAgents.add(pinpointTarget.getApplicationName());
		}
		for (Agent agent : userAgentList) {
			allUserAgents.add(agent.getAgentID());
		}
		allAgents.retainAll(allUserAgents);
		return allAgents;
	}
	/**
	 * 获取时间段内所有散点信息
	 * @param application
	 * @param from
	 * @param to
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/getScatterData.do")
	@ResponseBody
	public String scatterData(String agentID, long from, long to, int limit) {
		String url = WebContext.PINPOINT + "/getScatterData.pinpoint?application=" + agentID + "&from=" + from + "&to=" + to + "&limit=" + limit+"&v=2";
//		System.out.println(getJsonFromPinpoint(url));
		return getJsonFromPinpoint(url);
	}
	/**
	 * 获取调用堆栈信息
	 * @param traceId
	 * @param focusTimestamp
	 * @return
	 */
	@RequestMapping(value = "/transactionInfo.do")
	@ResponseBody
	public String transactionInfo(String traceId, long focusTimestamp) {
//		String url = WebContext.PINPOINT + "/transactionInfo.pinpoint?traceId=" + traceId + "&focusTimestamp=" + focusTimestamp;
		try{
			String url = WebContext.PINPOINT + "/transactionInfo.pinpoint?traceId=" + URLEncoder.encode(traceId, "UTF-8") + "&focusTimestamp=" + focusTimestamp;
			System.out.println(url);
			return getJsonFromPinpoint(url);
		} catch (Exception e) {
			logger.warn("不合法参数" + traceId);
		}
		return "";
	}

	//向pp发送get请求
	private String getJsonFromPinpoint(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("读取pinpoint数据异常：" + url);
		}
		return "";
	}

//向pp发送post请求
	private String getJsonFromPinpointPost(String url,Map<String,Object> map){
		try {
			HttpClient httpclient = new DefaultHttpClient();
			List<BasicNameValuePair> pairs=null;
			if(map != null && !map.isEmpty()){
				pairs = new ArrayList<BasicNameValuePair>(map.size());
				for(String key : map.keySet()){
					pairs.add(new BasicNameValuePair(key, map.get(key).toString()));
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if(pairs !=null && pairs.size()>0){
				httpPost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
			}
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity !=null){
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("读取pinpoint数据异常：" + url);
		}
		return "";
	}

}
