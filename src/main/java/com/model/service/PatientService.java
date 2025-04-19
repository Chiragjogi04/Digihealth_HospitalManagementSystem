package com.model.service;

import java.sql.Date;
import java.util.List;

import com.bean.Appointment;
import com.bean.Patient;
import com.bean.PatientDetailsResponse;

public interface PatientService {

    List<Patient> getAllPatient();

    Patient addPatient(Patient patient);

    boolean deletePatient(String patientId);

    Patient getPatientById(String patientId);

    String getLastPatientId();

    String setNewPatientId();

    List<Appointment> getMyAppointments(String pid);

    boolean rescheduleAppointment(int aid, Date newDate);

    boolean cancelAppointmentRequest(int aid);

    PatientDetailsResponse getPatientDetails(String patientId);
}
