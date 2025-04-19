package com.bean;

import java.util.List;

public class PatientDetailsResponse {
    private String patientName;
    private Integer age;
    private String contact;
    private List<AppointmentResponse> appointments;

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<AppointmentResponse> getAppointments() {
        return appointments;
    }
    public void setAppointments(List<AppointmentResponse> appointments) {
        this.appointments = appointments;
    }
}
