package com.udacity.jdnd.course3.critter.Controllers;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public static ScheduleDTO convertScheduleToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if (schedule.getEmployeeList()!=null){
            List<Long> employeeIdList = new ArrayList<>();
            schedule.getEmployeeList().forEach(employee -> employeeIdList.add(employee.getId()));
            scheduleDTO.setEmployeeIds(employeeIdList);
        }
        if (schedule.getPetList()!=null){
            List<Long> petList = new ArrayList<>();
            schedule.getPetList().forEach(pet -> petList.add(pet.getId()));
            scheduleDTO.setEmployeeIds(petList);
        }
        return scheduleDTO;
    }

    public static Schedule convertScheduleToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if (scheduleDTO.getEmployeeIds()!=null){
            List<Employee> employeeList = new ArrayList<>();
            scheduleDTO.getEmployeeIds().forEach(EmployeeId ->{
                Employee employee = new Employee();
                employee.setId(EmployeeId);
                employeeList.add(employee);});
                schedule.setEmployeeList(employeeList);
        }
        if(scheduleDTO.getPetIds()!=null){
            List<Pet> petList = new ArrayList<>();
            scheduleDTO.getPetIds().forEach(PetId ->{
                Pet pet = new Pet();
                pet.setId(PetId);
                petList.add(pet);});
            schedule.setPetList(petList);
        }
        return schedule;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(convertScheduleToEntity(scheduleDTO));
        return convertScheduleToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getSchedules();
        schedules.forEach(schedule -> scheduleDTOS.add(convertScheduleToDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        schedules.forEach(schedule -> scheduleDTOS.add(convertScheduleToDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        schedules.forEach(schedule -> scheduleDTOS.add(convertScheduleToDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        schedules.forEach(schedule -> scheduleDTOS.add(convertScheduleToDTO(schedule)));
        return scheduleDTOS;
    }
}
