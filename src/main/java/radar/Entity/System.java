package radar.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="system")
public class System {
	
	private Integer systemId;//分系统id
	private String systemName; //系统名称
	private Integer type;//雷达型号，I型雷达：0， II型雷达：1，Default：0
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "systemId",unique=true,nullable=false, length = 11)
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	@Column(name="systemName",length=32,unique=true)
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
