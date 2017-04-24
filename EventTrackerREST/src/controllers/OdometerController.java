package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.OdometerDAO;
import entities.Odometer;
@RestController
public class OdometerController {

	@Autowired
	private OdometerDAO odometerDAO;
	
	@RequestMapping(value ="kromulon", method = RequestMethod.GET)
	public String ping(){
	return "I LIKE WHAT YOU GOT!!";
	}
	
	//shows a list of starting and ending odometer values
	@RequestMapping(value="odometers", method= RequestMethod.GET)
	public List<Odometer> index(){
		return odometerDAO.index();
	}
	
	//returns the starting and ending values of an odometer by id
	@RequestMapping(value="odometers/{id}", method=RequestMethod.GET)
	public Odometer showStartingOdometer(@PathVariable int id){
		return odometerDAO.show(id);
	}
	

	//create new car
		@RequestMapping(value = "odometers", method = RequestMethod.POST)
		public Odometer create(@RequestBody String jsonQuiz, HttpServletResponse res){
			
			System.out.println(jsonQuiz);
			try{
				ObjectMapper mapper = new ObjectMapper();
				Odometer mappedEvent=mapper.readValue(jsonQuiz, Odometer.class);
				res.setStatus(201);
				return odometerDAO.create(mappedEvent);
				
			}catch(Exception e){
				System.out.println("WHOOPS! You goofed");
				e.printStackTrace();
			}
			return null;
		}
		
		//removes an odometer by id
		@RequestMapping(value="odometers/{id}", method = RequestMethod.DELETE)
		public boolean destroyQuestion(@PathVariable("id") int id){	
			return odometerDAO.destroy(id);
		}
		
		//updates an odometers start and end values by id
		@RequestMapping(value = "odometers/{id}", method = RequestMethod.PUT)
		public Odometer update(@PathVariable int id, @RequestBody String jsonUpdateEvent,
				HttpServletResponse res){
			try{
				ObjectMapper mapper = new ObjectMapper();
				Odometer mappedEvent=mapper.readValue(jsonUpdateEvent, Odometer.class);
				res.setStatus(201);
				return odometerDAO.update(id, mappedEvent);
				
			}catch(Exception e){
				System.out.println("WHOOPS! You goofed");
				e.printStackTrace();
			}
			return null;
		}
		
//		//sets the average miles driven 
//		@RequestMapping(value = "odometers/{averageId}", method = RequestMethod.GET)
//		public int retrieveAverageMilesDriven(@PathVariable int averageId){
//			//********
//			Odometer o = new Odometer();
//			int startM = o.getStartingOdometer();
//			int endM = o.getEndingOdometer();
//	
//			int daysDriven = o.getDays();
//			
//			int difference = endM - startM;
//			
//			int average = difference/daysDriven;
//			
//			o.setAverage(average);
//			
//					
//			//********		
//			
//			return average;
//		}
		
}
