package com.pmo.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmo.service.entity.EmployeeDetailsEntity;
import com.pmo.service.model.EmployeeDetails;

@Repository
public class PMOEmployeeDAO {

	@Autowired
	PasswordEncoder encoder;

	@PersistenceContext
	EntityManager em;

	public EmployeeDetailsEntity validateEmploeeDetails(String empId) {
		return em.find(EmployeeDetailsEntity.class, empId);
	}

	@Transactional
	public boolean saveEmploeeDetails(EmployeeDetails empDetalis) {
		EmployeeDetailsEntity detailsEntity = null;
		try 
		{
			detailsEntity = validateEmploeeDetails(empDetalis.getEmp_id());
			if (detailsEntity == null) {
				EmployeeDetailsEntity details = new EmployeeDetailsEntity();
				details.setEnabled(0);
				details.setEmp_id(empDetalis.getEmp_id());
				details.setFirst_name(empDetalis.getFirst_name());
				details.setLast_name(empDetalis.getLast_name());
				details.setIbm_mail_id(empDetalis.getIbm_mail_id());
				details.setPh_number(empDetalis.getPh_number());
				details.setPassword(encoder.encode(empDetalis.getPassword()));

				em.persist(details);
				return true;
			} 
			else 
			{
				return false;
			}

		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
