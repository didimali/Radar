package radar.UI;

import javax.swing.JPanel;
import radar.Tools.InitUIAndAction;
import radar.UI.ContentPanel.ManagerExportExcel;
import radar.UI.ContentPanel.ManagerFault;
import radar.UI.ContentPanel.ManagerHealth;
import radar.UI.ContentPanel.ManagerList;

import radar.UI.ContentPanel.ManagerPartsComsume;
import radar.UI.ContentPanel.NewManager;
import radar.UI.ContentPanel.NewRadar;
import radar.UI.ContentPanel.RadarActivityRecord;
import radar.UI.ContentPanel.RadarBasicInfo;
import radar.UI.ContentPanel.RadarDynamicData;
import radar.UI.ContentPanel.RadarFaultForecast;
import radar.UI.ContentPanel.RadarFaultRecord;
import radar.UI.ContentPanel.RadarHealthAssess;
import radar.UI.ContentPanel.RadarInAndOut;
import radar.UI.ContentPanel.RadarList;
import radar.UI.ContentPanel.RadarPartsRequirements;
import radar.UI.ContentPanel.RadarRealationshipAnalyse;
import radar.UI.LeftPanel.LManager;
import radar.UI.LeftPanel.LRadar;
import radar.UI.TopPanel.TManagerDetails;
import radar.UI.TopPanel.TRadarDataAnalyse;
import radar.UI.TopPanel.TRadarDetails;
import radar.UI.TopPanel.TManagerData;
import radar.UI.TopPanel.TRadarData;
import radar.UI.TopPanel.TRadarType;

import java.awt.BorderLayout;
import java.awt.Color;

public class Home extends JPanel implements InitUIAndAction {
	
	

	private static final long serialVersionUID = -5219016543328905524L;
	
	private LManager LManager;
	private LRadar LRadar;
	
	private TManagerDetails TManagerDetails;
	private TManagerData TManagerData;
	private TRadarDataAnalyse TDataAnalyse;
	private TRadarDetails TRdaraDetails;
	private TRadarData TRadarData;
	private TRadarType TRadarType;
	
	private ManagerList  ManagerList;
	private NewManager NewManager;
	
	private ManagerFault ManagerFault;
	private ManagerHealth ManagerHealth;
	private ManagerPartsComsume ManagerPartsComsume;
	private ManagerExportExcel ManagerExportExcel;
	
	private NewRadar NewRadar;
	private RadarActivityRecord RadarActivityRecord;
	private RadarBasicInfo RadarBasicInfo;
	private RadarDynamicData RadarDynamicData;
	private RadarFaultForecast RadarFaultForecast;
	private RadarFaultRecord RadarFaultRecord;
	private RadarHealthAssess RadarHealthAssess;
	private RadarInAndOut RadarInAndOut;
	private RadarList RadarList;
	private RadarPartsRequirements RadarPartsRequirements;
	private RadarRealationshipAnalyse RadarRealationshipAnalyse;	
	
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
		initUI();
	}

	/**
	 * 页面组件及其事件初始化
	 */
	@Override
	public void initUI() {
		
		//页面组件初始化
	
		LManager leftPanel = new LManager();
//		LRadar leftPanel = new LRadar();
		leftPanel.b1();
		add(leftPanel, BorderLayout.WEST);
		
		TManagerDetails topPanel = new TManagerDetails();
//		TDataAnalyse topPanel = new TDataAnalyse();
		topPanel.b1();
		add(topPanel, BorderLayout.NORTH);
		
		ManagerList e = new ManagerList();
//		NewManager e = new NewManager();
		add(e, BorderLayout.CENTER);

		
//		RadarFaultForecast ff = new RadarFaultForecast();
//		add(ff, BorderLayout.CENTER);
		
//		RadarPartsRequirements rr = new RadarPartsRequirements();
//		add(rr, BorderLayout.CENTER);
		//页面组件事件初始化
		Action();
	}

	@Override
	public void Action() {
		
	}

	public void setRadarRealationshipAnalyse() {
		RadarRealationshipAnalyse = new RadarRealationshipAnalyse();
	}

	public void setRadarPartsRequirements() {
		RadarPartsRequirements = new RadarPartsRequirements();
	}

	public void setRadarList() {
		RadarList = new RadarList();
	}

	public void setRadarInAndOut() {
		RadarInAndOut = new RadarInAndOut();
	}

	public void setRadarHealthAssess() {
		RadarHealthAssess = new RadarHealthAssess();
	}

	public void setRadarFaultRecord() {
		RadarFaultRecord = new RadarFaultRecord();
	}

	public void setRadarFaultForecast(RadarFaultForecast radarFaultForecast) {
		RadarFaultForecast = radarFaultForecast;
	}

	public void setRadarDynamicData(RadarDynamicData radarDynamicData) {
		RadarDynamicData = radarDynamicData;
	}

	public void setRadarBasicInfo(RadarBasicInfo radarBasicInfo) {
		RadarBasicInfo = radarBasicInfo;
	}

	public void setRadarActivityRecord(RadarActivityRecord radarActivityRecord) {
		RadarActivityRecord = radarActivityRecord;
	}

	public void setNewRadar(NewRadar newRadar) {
		NewRadar = newRadar;
	}
	
	public void setManagerExportExcel(ManagerExportExcel managerExportExcel) {
		ManagerExportExcel = managerExportExcel;
	}

	public void setManagerPartsComsume(ManagerPartsComsume managerPartsComsume) {
		ManagerPartsComsume = managerPartsComsume;
	}

	public void setManagerHealth(ManagerHealth managerHealth) {
		ManagerHealth = managerHealth;
	}

	public void setManagerFault(ManagerFault managerFault) {
		ManagerFault = managerFault;
	}

	public void setNewManager(NewManager newManager) {
		NewManager = newManager;
	}

	public void setManagerList(ManagerList managerList) {
		ManagerList = managerList;
	}

	public void setTRadarType(TRadarType tRadarType) {
		TRadarType = tRadarType;
	}

	public void setTRadarData(TRadarData tRadarData) {
		TRadarData = tRadarData;
	}

	public void setTRdaraDetails(TRadarDetails tRdaraDetails) {
		TRdaraDetails = tRdaraDetails;
	}

	public void setTDataAnalyse(TRadarDataAnalyse tDataAnalyse) {
		TDataAnalyse = tDataAnalyse;
	}

	public void setTManagerData(TManagerData tManagerData) {
		TManagerData = tManagerData;
	}

	public void setTManagerDetails(TManagerDetails tManagerDetails) {
		TManagerDetails = tManagerDetails;
	}

	public void setLRadar(LRadar lRadar) {
		LRadar = lRadar;
	}

	public void setLManager(LManager lManager) {
		LManager = lManager;
	}

}
