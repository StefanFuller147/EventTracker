package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="miles_driven")
public class Odometer {
	
	//fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="starting_odometer")
	private int startingOdometer;
	
	@Column(name="ending_odometer")
	private int endingOdometer;
	
	@Column(name="days")
	private int days;
	
	
	private int average;

	
	//getters and setters
	
	public int getAverage() {
		return average;
	}
	
	public void setAverage(int average) {
		this.average = average;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getStartingOdometer() {
		return startingOdometer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartingOdometer(int startingOdometer) {
		this.startingOdometer = startingOdometer;
	}

	public int getEndingOdometer() {
		return endingOdometer;
	}

	public void setEndingOdometer(int endingOdometer) {
		this.endingOdometer = endingOdometer;
	}

	//toString();
	@Override
	public String toString() {
		return "Event [startingOdometer=" + startingOdometer + ", endingOdometer=" + endingOdometer + "]";
	}
	
	
}
