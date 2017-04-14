package ict.monitor.bean;

public class DataMining {
	String compent;//事务组件
	String uptime;//消耗时间 分为1--3秒（1up)  3--5秒(3up)  5秒以上（5up)
	String data_Support;//支持度
	String data_Confidence;//置信度
	String degree;//重要程度
	String transactionId;//全局唯一ID
	String startTime;//起始时间
	public String getCompent() {
		return compent;
	}
	public void setCompent(String compent) {
		this.compent = compent;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getData_Support() {
		return data_Support;
	}
	public void setData_Support(String data_Support) {
		this.data_Support = data_Support;
	}
	public String getData_Confidence() {
		return data_Confidence;
	}
	public void setData_Confidence(String data_Confidence) {
		this.data_Confidence = data_Confidence;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	

	
	

}
