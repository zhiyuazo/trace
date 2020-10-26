package com.zzy.trace.probe;

import java.util.ArrayList;
import java.util.Date;

public class MsgProbeDesc {
	//MsgName 
	public String msgName =null;
	//最新时间
	public Date timeLatest =null;
	public int  timeDead = 60;
	//消息阶段
	public ArrayList<MsgProbePhase> MsgPhaseList = null;
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public Date getTimeLatest() {
		return timeLatest;
	}
	public void setTimeLatest(Date timeLatest) {
		this.timeLatest = timeLatest;
	}
	public ArrayList<MsgProbePhase> getMsgPhaseList() {
		return MsgPhaseList;
	}
	public void setMsgPhaseList(ArrayList<MsgProbePhase> msgPhaseList) {
		MsgPhaseList = msgPhaseList;
	}
	public int getTimeDead() {
		return timeDead;
	}
	public void setTimeDead(int timeDead) {
		this.timeDead = timeDead;
	}
}
