package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Odometer;

public class OdometerTest {
	EntityManagerFactory emf = null;
	EntityManager em = null;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("Event");
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() {
		em.close();
		emf.close();

	}

	@Test
	public void test() {
		boolean test = true;
		assertEquals(test, true);
	}

	@Test
	public void testStartingOdometer() {
		Odometer event = em.find(Odometer.class, 1);
		String q = "SELECT o FROM Odometer o";
		List<Odometer> events = em.createQuery(q, Odometer.class).getResultList();
		System.out.println(events.size());
		for (Odometer event2 : events) {
			System.out.println(event2);
		}
		assertEquals(23, event.getStartingOdometer());
	}

	@Test
	public void testEndingOdometer() {
		Odometer odometer = em.find(Odometer.class, 1);
		assertEquals(1245, odometer.getEndingOdometer());
	}

	@Test
	public void testDays() {
		Odometer odometer = em.find(Odometer.class, 1);
		assertEquals(5, odometer.getDays());
	}
	
	@Test
	public void getAverageMilesDriven(){
		Odometer odometer = em.find(Odometer.class, 8);
		assertEquals(0, odometer.getAverage());
		
	}
}
