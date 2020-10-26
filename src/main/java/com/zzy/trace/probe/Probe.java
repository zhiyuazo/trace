package com.zzy.trace.probe;
import java.util.Date;

public class Probe {
	public String MsgName  = "";
	//处理阶段   
	public String PhaseName = "" ;
	
	//数据类型
	public String typeName = "";
	
	//处理时间
	public Date timeProbe = new Date();
	
	//实体数据
	public Object dataProbed =null;
	
	public Probe(String msgName,String phase,String type, Object data) {
		MsgName = msgName;
		PhaseName = phase;
		typeName = type;
		if(data == null) {
			dataProbed = "null";
		}else {
			dataProbed = data;
		}
	}
}
