package com.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bean.Doctor;
import com.bean.Patient;
@Service
public interface AdminService {

	boolean registerDoctorToDatabase(Doctor doctor);


	boolean removeDoctorFromDatabase(String doctorID);
	
	List<Patient> getAllPatient();
	List<Doctor> getAllDoctor();
	
	boolean removePatientFromDatabase(String patientId);

}