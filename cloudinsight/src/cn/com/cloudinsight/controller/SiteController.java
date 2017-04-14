package cn.com.cloudinsight.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ethz.ssh2.Session;
import cn.com.cloudinsight.dao.HostDao;
import cn.com.cloudinsight.dao.SiteDao;
import cn.com.cloudinsight.model.Host;
import cn.com.cloudinsight.model.PageBean;
import cn.com.cloudinsight.model.User;
import cn.com.cloudinsight.utils.PageUtil;
import cn.com.cloudinsight.utils.StringUtil;

@Controller
public class SiteController {
    private SiteDao siteDao=new SiteDao();
	
  
   /**
    * 获取当天的监控数据
    * @param add
    * @param time
    * @param item
    * @param name
    * @param request
    * @return
    * @throws ParseException
    * @throws JsonProcessingException
    */
    
	@RequestMapping("/getNow_site.do")
	@ResponseBody
	public Object getHostData(@RequestParam(value="add") String add,@RequestParam(value="time") String time,@RequestParam(value="item") String item,@RequestParam(value="name") String name,HttpServletRequest request) throws ParseException, JsonProcessingException{
	
		HttpSession session=request.getSession();
		session.setAttribute("add", add);
		session.setAttribute("name", name);
		List<String> list=new ArrayList<String>();

	    list=siteDao.getNow_site(add, time, item,name);
	    ObjectMapper mapper =new ObjectMapper();
    	String json=mapper.writeValueAsString(list);
   
		return json;
		
	}
	/**
	 * 获取监控详细信息
	 * @param add
	 * @param name
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/siteMore.do",produces="text/plain;charset=UTF-8")
	public String siteMore(@RequestParam(value="add") String add,@RequestParam(value="name") String name,HttpServletRequest request) throws UnsupportedEncodingException{
		String addr=java.net.URLDecoder.decode(add, "UTF-8");
		String Name=java.net.URLDecoder.decode(name, "UTF-8");//转换字体
		HttpSession session=request.getSession();
		session.setAttribute("add", addr);
		session.setAttribute("name", Name);
		return "redirect:site_more.jsp";
		
	}
   
	/**
	 * 获取具体时间的监控数据
	 * @param add
	 * @param pretime
	 * @param time
	 * @param id
	 * @param name
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/getSite_data.do")
	@ResponseBody
	public Object getMoreSite_info(@RequestParam(value="add") String add,@RequestParam(value="preTime") String pretime,@RequestParam(value="time") String time,@RequestParam(value="id") String id,@RequestParam(value="name") String name) throws JsonProcessingException{
		List<String> list=new ArrayList<String>();
		list=siteDao.getMoreInfo(add,pretime, time,name, id);
		 ObjectMapper mapper =new ObjectMapper();
	    String json=mapper.writeValueAsString(list);
		return json;
		
	}
	
	/**
	 * 获取分布式网站
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/admin/getSite.do")
	public String getData(HttpServletRequest request) throws JsonProcessingException{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("currentUser");
		List<List<List<String>>> list=siteDao.getMaps(user);//链接数据库获取数据
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);//将数据转成json格式
		session.setAttribute("list", json);
		return "redirect:../monitor.jsp";
		
	}
 }
