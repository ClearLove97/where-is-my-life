package testone;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class period {
	
	private LocalDateTime oneTime;
	private LocalDateTime toTime;
	
	public LocalDateTime getOneTime() {
		return oneTime;
	}
	public void setOneTime(LocalDateTime oneTime) {
		this.oneTime = oneTime;
	}
	public LocalDateTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalDateTime toTime) {
		this.toTime = toTime;
	}
}
