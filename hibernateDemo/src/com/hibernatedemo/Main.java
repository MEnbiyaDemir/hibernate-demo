package com.hibernatedemo;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      SessionFactory factory=new Configuration()
    		  .configure("hibernate.cfg.xml")
    		  .addAnnotatedClass(City.class).buildSessionFactory();
      
      Session session= factory.getCurrentSession();
      
      try{
    	  session.beginTransaction();
    	  
    	  //SORGULAR
    	  
    	  //List <City> cities=session.createQuery("from City c where c.countryCode='TUR' AND c.district='Duzce'").getResultList(); turkiye ve düzce bölgesi
    	  //"from City c where c.name LIKE '%kar%' " içinde kar olan þehirler , kar% ise ilk 3 kelime kar, %kar ise son 3 kelime kar
    	  //order by asc adan zye- desc zden a'ya sýralar
    	  
    	  List <City> cities=session.createQuery("from City c ORDER BY c.name DESC").getResultList();
    	  for(City city:cities) 
    	  {System.out.println(city.getName());}
    	  
    	  List <String> countryCodes=session.createQuery("select c.countryCode from City c GROUP BY c.countryCode").getResultList();
    	  for(String countryCode:countryCodes) 
    	  {System.out.println(countryCode);}
    	 
    	  //INSERT
    	  //City kuzce=new City();
    	 // kuzce.setName("Kuzce");
    	 // kuzce.setCountryCode("TUR");
    	 // kuzce.setDistrict("Karadeniz");
    	 // kuzce.setPopulation(313131);
    	 // session.save(kuzce);
    	  
    	  //UPDATE
    	  City ist=session.get(City.class, 3357);
    	  System.out.println(ist.getName());
    	  ist.setPopulation(313131);
    	  session.save(ist);
    	  
    	  //DELETE
    	  City kuzce2=session.get(City.class, 4084);
    	  session.delete(kuzce2);
    	  
    	  
    	  
    	  session.getTransaction().commit();
      }
      finally{factory.close();}
      
      
      
      
	}

}
