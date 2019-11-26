package Probe;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MsgProbePhase {
	public int size = 5;
	//PhaseName 
	public String PhaseName =null;
	//PhaseDataType 
	public String PhaseDataType =null;
	//Phase数据列表
	public LinkedHashMap<Long,Object> dataList = null;
	//getter & setter
	public String getPhaseName() {
		return PhaseName;
	}
	public void setPhaseName(String phaseName) {
		PhaseName = phaseName;
	}
	public String getPhaseDataType() {
		return PhaseDataType;
	}
	public void setPhaseDataType(String phaseDataType) {
		PhaseDataType = phaseDataType;
	}
	public Map<Long, Object> getDataList() {
		return dataList;
	}
	public void setDataList(LinkedHashMap<Long, Object> dataList) {
		this.dataList = dataList;
	}
	
	public void putLimit(Long time,Object o){
		this.dataList.put(time, o);
		if(this.dataList.size() > this.size){
			long minTime = time ; 
			for(long l : this.dataList.keySet()){
				if(l<time){
					minTime = l;
				}
			}
			this.dataList.remove(minTime);
		}
		
	}
}
