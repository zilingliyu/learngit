package cn.com.cloudinsight.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@Component
@Scope("prototype")
public class UtilController {

 
   /**
    * �����ļ�
    * @param request
    * @return
    * @throws IOException
    */
    @SuppressWarnings("deprecation")
	@RequestMapping(value="getAgent.do")
    public ResponseEntity<byte[]> downloadAgent(HttpServletRequest request) throws IOException{
    	String path=request.getRealPath("/")+"download/zabbix.tar.gz";  
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String("zabbix.tar.gz".getBytes("UTF-8"),"iso-8859-1");//Ϊ�˽������������������ ��  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);  	
    }
    /**
     * ����agent�ĵ�
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="getFile.do")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws IOException{
    	String path=request.getRealPath("/")+"download/agent��װ�ĵ�.docx";  
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String("agent��װ�ĵ�.docx".getBytes("UTF-8"),"iso-8859-1");//Ϊ�˽������������������    
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);  	
    }
}
