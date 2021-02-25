package read.excel.ReadExcel.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "district_db")
@Getter
@Setter
public class District {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;
	
	private String province;
	private String district;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("District [id=");
		builder.append(id);
		builder.append(", province=");
		builder.append(province);
		builder.append(", district=");
		builder.append(district);
		builder.append("]");
		return builder.toString();
	}
}
