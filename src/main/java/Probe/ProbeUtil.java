package Probe;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProbeUtil {
	
	private ProbeUtil() {
		
	}
	
	private static ProbeUtil instance ;
	
	public static ProbeUtil getInstance() {
		if(instance ==null) {
			return new ProbeUtil();
		}else {
			return instance;
		}
	}
	
	public static ArrayList getProbeResult() {
		return PROBEs;
	}
	//不能探测 int Integer boolean等类型数据
	//默认死亡时间为60S，暂不支持更改...
	//默认存储数据的列表为大小为5，暂不支持更改...
	private static ArrayList<MsgProbeDesc> PROBEs = new ArrayList<>();
	public static void addProbe(Probe p) {
		//是否具有当前Msg的监控列表 -1 表示没有当前消息...
		int current_msg_index = -1;
		for(int i = 0 ; i < PROBEs.size(); i++){
			if(p.MsgName.equals( PROBEs.get(i).msgName)){
				current_msg_index = i;
				break;
			}
		}
		
		if(current_msg_index != -1){ //此分支表示:当前[Probe报告] 有此messge的Probe.....
			MsgProbeDesc mpd  = PROBEs.get(current_msg_index); //当前消息节点入口
			int current_msg_phase_index = -1; 
			for(int j= 0  ; j < mpd.MsgPhaseList.size();j++){
				if(p.PhaseName.equals(mpd.MsgPhaseList.get(j).PhaseName)){
					current_msg_phase_index = j;
					break;
				}
			}
			if(current_msg_phase_index != -1){
				PROBEs.get(current_msg_index).MsgPhaseList.get(current_msg_phase_index).putLimit(p.timeProbe.getTime(), p.dataProbed);//dataList.put(p.timeProbe.getTime(),p.dataProbed);
				PROBEs.get(current_msg_index).setTimeLatest(p.timeProbe);
			}else if(current_msg_phase_index == -1){
				MsgProbePhase mpp = new MsgProbePhase();
				mpp.setPhaseName(p.PhaseName);
				mpp.setPhaseDataType(p.typeName);
				mpp.dataList = new LinkedHashMap<Long,Object>();
					mpp.putLimit(p.timeProbe.getTime(), p.dataProbed);//dataList.put(p.timeProbe.getTime(),p.dataProbed);
				PROBEs.get(current_msg_index).MsgPhaseList.add(mpp);
				PROBEs.get(current_msg_index).setTimeLatest(p.timeProbe);
			}
		}else if(current_msg_index == -1){//此分支表示:当前[Probe报告]无Message的Probe....
			MsgProbeDesc current_msg = new MsgProbeDesc();
			current_msg.setMsgName(p.MsgName);
			current_msg.setTimeLatest(p.timeProbe);
			current_msg.MsgPhaseList = new ArrayList<>();
				MsgProbePhase mpp = new MsgProbePhase();
				mpp.setPhaseName(p.PhaseName);
				mpp.setPhaseDataType(p.typeName);
					mpp.dataList = new LinkedHashMap<Long,Object>();
					mpp.putLimit(p.timeProbe.getTime(), p.dataProbed);//dataList.put(p.timeProbe.getTime(),p.dataProbed);
			current_msg.MsgPhaseList.add(mpp);
			PROBEs.add(current_msg);
		}
	}

}
