package com.model.service;

import java.util.List;

import com.bean.Schedule;

public interface ScheduleService {
    List<Schedule> getDoctorSchedules(String doctorId);
    Schedule addSchedule(Schedule schedule);
    void deleteSchedule(Long scheduleId);
} 