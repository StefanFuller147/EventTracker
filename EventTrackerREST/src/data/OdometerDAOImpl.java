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

	@Override
	public List<Odometer> index() {
		String query = "SELECT o FROM Odometer o";
		return em.createQuery(query, Odometer.class).getResultList();
		
	}

	@Override
	public Odometer show(int id) {
		return em.find(Odometer.class, id);
	}

	@Override
	public Odometer create(Odometer odometer) {
			
		em.persist(odometer);
		em.flush();
		return odometer;
	}

	@Override
	public int avgMilesDriven(int id) {
		Odometer odometer = new Odometer();
		int so = odometer.getStartingOdometer();
		int eo = odometer.getEndingOdometer();
		int d = odometer.getDays();
		int sum = eo-so;
		int avg = odometer.setAverage(sum/d);
		
		return avg;
	}

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
