package com.oracle.cloud.apaas.example.springboot.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.cloud.apaas.example.springboot.rest.entities.Customer;
import com.oracle.cloud.apaas.example.springboot.rest.entities.CustomerDAO;
import com.oracle.cloud.apaas.example.springboot.rest.entities.Location;
import com.oracle.cloud.apaas.example.springboot.rest.entities.LocationDAO;

@Controller
public class MySQLController {
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private LocationDAO locationDao;

	@CrossOrigin(origins = "*")
    @RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public Customer customers(@PathVariable(value="customerId") String customerId) {
        return customerDao.findOne(customerId);
    }
    
	@CrossOrigin(origins = "*")
    @RequestMapping(value="/customers", method = RequestMethod.GET)
	@ResponseBody
    public String customers() {
		String result = null;
		
		List<Customer> customers = (List<Customer>) customerDao.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(customers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "{\"count\":" + customers.size() + ",\"result\": " + result + "}";
    }
	
	
	@CrossOrigin(origins = "*")
    @RequestMapping(value="/locations/{locationId}", method = RequestMethod.GET)
    @ResponseBody
    public Location location(@PathVariable(value="locationId") String locationId) {
        return locationDao.findOne(locationId);
    }
    
	@CrossOrigin(origins = "*")
    @RequestMapping(value="/locations", method = RequestMethod.GET)
	@ResponseBody
    public String locations() {
		String result = null;
		
		List<Location> locations = (List<Location>) locationDao.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(locations);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "{\"count\":" + locations.size() + ",\"result\": " + result + "}";
    }
	
	
	@GetMapping("/mysql")
	public String mysql(Map<String, Object> model) {
		model.put("time", new Date());
		return "mysql";
	}
	
	@GetMapping("/env")
	public String environment(Map<String, Object> model) {
		model.put("time", new Date());
		return "environment";
	}
	
}
