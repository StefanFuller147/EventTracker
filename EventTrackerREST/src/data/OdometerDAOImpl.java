package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Odometer;

@Transactional
public class OdometerDAOImpl implements OdometerDAO {
	@PersistenceContext
	private EntityManager em;

	
	//shows the full list of cars and their values
	@Override
	public List<Odometer> index() {
		String query = "SELECT o FROM Odometer o";
		return em.createQuery(query, Odometer.class).getResultList();
		
	}

	//shows an individual car from id
	@Override
	public Odometer show(int id) {
		return em.find(Odometer.class, id);
	}

	
	//makes a new car
	@Override
	public Odometer create(Odometer odometer) {
		
		em.persist(odometer);
		em.flush();
		return odometer;
	}

	//setting the average mileage based on start and end odometer values
	@Override
	public int avgMilesDriven(int id) {
		Odometer odometer = new Odometer();
		int so = odometer.getStartingOdometer();
		int eo = odometer.getEndingOdometer();
		int days = odometer.getDays();
		int difference = eo-so;
		int avg = odometer.setAverage(difference/days);
		
		return avg;
	}
	
	
	//removes a car from the list 
	@Override
	public boolean destroy(int id) {
		Odometer managed = em.find(Odometer.class, id);
		em.persist(managed);
		em.remove(managed);
		em.flush();
		if (em.find(Odometer.class, id) == null) {
			return true;
		} else if (em.find(Odometer.class, id) != null) {
			return false;

		}
		return false;
	}
	
	//updates a current car
	@Override
	public Odometer update(int id, Odometer odometer) {
		Odometer managed = em.find(Odometer.class, id);
		managed.setStartingOdometer(odometer.getStartingOdometer());
		managed.setEndingOdometer(odometer.getEndingOdometer());
		managed.setDays(odometer.getDays());
		managed.setAverage(odometer.getAverage());
		managed.setName(odometer.getName());
		em.flush();
		return managed;
	}

}
