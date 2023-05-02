package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repo.ScheduleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<Schedule> getScheduleForPet(Long id){
        return  scheduleRepo.findPetsById(id);
    }

    public List<Schedule> getScheduleForEmployee(Long id){
        return scheduleRepo.findEmployeesById(id);
    }

    public List<Schedule> getScheduleForCustomer(Long id){
        return scheduleRepo.findCustomersById(id);
    }

    public List<Schedule> getSchedules(){
        return scheduleRepo.findAll();
    }

    public Schedule createSchedule(Schedule schedule){
        return scheduleRepo.save(schedule);
    }
}
