package data;

import java.util.List;

import entities.Odometer;

public interface OdometerDAO {
	public List<Odometer> index();
	public Odometer show(int id);
	public Odometer create(Odometer event);
	public boolean destroy(int id);
	public Odometer update(int id, Odometer event);
	
	public int avgMilesDriven(int id);
	
	
}
