package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.PatientDetailsResponse;
import com.model.persistence.DoctorDao;
import com.model.persistence.PatientDao;
import com.model.service.AppointmentService;
import com.model.service.DoctorService;
import com.model.service.PatientService;

@Controller
public class CommonController {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @RequestMapping("/")
    public ModelAndView homePageController() {
        return new ModelAndView("index");
    }

    @RequestMapping("/register")
    public ModelAndView registerPageController() {
        return new ModelAndView("patientRegister");
    }

    @RequestMapping("/beforeLogin")
    public ModelAndView beforeLoginController() {
        return new ModelAndView("beforeLogin");
    }

    @RequestMapping("/login")
    public ModelAndView loginPageController() {
        return new ModelAndView("login");
    }

    @GetMapping("/getPatientDetails")
    @ResponseBody
    public PatientDetailsResponse getPatientDetails(@RequestParam("pid") String pid) {
        return patientService.getPatientDetails(pid);
    }
}
