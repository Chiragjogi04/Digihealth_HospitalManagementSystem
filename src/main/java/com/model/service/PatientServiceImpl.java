package com.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Appointment;
import com.bean.AppointmentResponse;
import com.bean.Patient;
import com.bean.PatientDetailsResponse;
import com.bean.PatientLogin;
import com.model.persistence.AppointmentDao;
import com.model.persistence.PatientDao;
import com.model.persistence.PatientLoginDao;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private PatientLoginDao loginDao;

    @Override
    public List<Patient> getAllPatient() {
        return patientDao.findAll();
    }

    @Override
    @Transactional
    public Patient addPatient(Patient patient) {
        if (patient == null) {
            logger.error("Patient object is null");
            throw new IllegalArgumentException("Patient cannot be null");
        }
        String patientId = setNewPatientId();
        patient.setPatientId(patientId);

        Patient savedPatient = patientDao.save(patient);
        PatientLogin login = new PatientLogin();
        login.setId(patientId);
        login.setPassword(patientId);
        loginDao.save(login);

        return savedPatient;
    }

    @Override
    public boolean deletePatient(String patientId) {
        Patient patient = getPatientById(patientId);
        if (patient != null) {
            PatientLogin pl = loginDao.findPatientLoginById(patientId);
            if (pl != null) loginDao.delete(pl);
            List<Appointment> apps = appointmentDao.getAllAppointmentsByPatientId(patientId);
            apps.forEach(a -> appointmentDao.deleteById(a.getAppointmentId()));
            patientDao.deleteById(patientId);
            return true;
        }
        return false;
    }

    @Override
    public Patient getPatientById(String patientId) {
        return patientDao.findById(patientId).orElse(null);
    }

    @Override
    public String getLastPatientId() {
        Patient last = patientDao.findTopByOrderByPatientIdDesc();
        return (last != null) ? last.getPatientId() : null;
    }

    @Override
    public String setNewPatientId() {
        String lastId = getLastPatientId();
        if (lastId != null && lastId.length() > 1) {
            try {
                int id = Integer.parseInt(lastId.substring(1)) + 1;
                return "P" + id;
            } catch (NumberFormatException e) {
                return "P101";
            }
        }
        return "P101";
    }

    @Override
    public boolean rescheduleAppointment(int aid, Date newDate) {
        int rows = appointmentDao.reschedule(aid, newDate);
        return rows > 0;
    }

    @Override
    public List<Appointment> getMyAppointments(String pid) {
        return appointmentDao.getAllAppointmentsByPatientId(pid);
    }

    @Override
    public boolean cancelAppointmentRequest(int aid) {
        Optional<Appointment> opt = appointmentDao.findById(aid);
        if (opt.isPresent()) {
            appointmentDao.deleteById(aid);
            return true;
        }
        return false;
    }

    @Override
    public PatientDetailsResponse getPatientDetails(String patientId) {
        Patient p = patientDao.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));
        List<Appointment> apps = appointmentDao.getAllAppointmentsByPatientId(patientId);
        List<AppointmentResponse> appResponses = apps.stream().map(a -> {
            AppointmentResponse ar = new AppointmentResponse();
            ar.setDate(a.getDate().toLocalDate());
            ar.setSlot(a.getSlot().toString());
            ar.setDoctorName(a.getDoctorName());
            return ar;
        }).collect(Collectors.toList());
        PatientDetailsResponse resp = new PatientDetailsResponse();
        resp.setPatientName(p.getPatientName());
        resp.setAge(p.getPatientAge());
        resp.setContact(p.getPatientContact());
        resp.setAppointments(appResponses);
        return resp;
    }
}
