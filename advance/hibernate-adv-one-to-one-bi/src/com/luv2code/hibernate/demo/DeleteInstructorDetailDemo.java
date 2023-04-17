package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int instructorDetailId = 4;
			
			InstructorDetail theInstructorDetail = 
					session.get(InstructorDetail.class, instructorDetailId);
			
			// print the instructor detail
			System.out.println("instructor details: " + theInstructorDetail);
			
			// print the associated instructor
			System.out.println("The associated instructor: " + theInstructorDetail.getInstructor());
			
			// delete the instructor detail
			System.out.println("Deleting: " + theInstructorDetail);
			
			// remove the association object reference
			// break bi-directional link
			// we are doing this to delete only instructor details
			
			theInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(theInstructorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}	
