package radar.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="forecastResult")
public class forecastResult {
	
	private Integer forecastResultId;//故障预测记录id
	private Radar radarId;//	所属雷达id,外键
	private System systemId;  //分系统id,外键
	private faultType faultTypeId;//	故障类型id,外键
	private Equipment equipId; //设备id(故障位置),外键
	private Date assessDate;//	评估时间


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name = "forecastResultId",unique=true,nullable=false, length = 11)
	public Integer getForecastResultId() {
		return forecastResultId;
	}
	
	public void setForecastResultId(Integer forecastResultId) {
		this.forecastResultId = forecastResultId;
	}
	@ManyToOne
	@JoinColumn(name="radarId")
	public Radar getRadarId() {
		return radarId;
	}
	public void setRadarId(Radar radarId) {
		this.radarId = radarId;
	}
	@ManyToOne
	@JoinColumn(name="systemId")
	public System getSystemId() {
		return systemId;
	}

	public void setSystemId(System systemId) {
		this.systemId = systemId;
	}
	@ManyToOne
	@JoinColumn(name="faultTypeId")
	public faultType FaultTypeId() {
		return faultTypeId;
	}
	public void setFaultTypeId(faultType faultTypeId) {
		this.faultTypeId = faultTypeId;
	}
	@ManyToOne
	@JoinColumn(name="equipId")
	public Equipment getEquipId() {
		return equipId;
	}

	public void setEquipId(Equipment equipId) {
		this.equipId = equipId;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="assessDate")
	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

	}
