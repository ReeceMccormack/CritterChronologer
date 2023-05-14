package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {


    List<Schedule> findPetsById(Long id);

    List<Schedule> findEmployeesById(Employee employeeList);

    List<Schedule> findByPetList(Pet petList);

    List<Schedule> findByEmployeeList(Employee employeeList);

    List<Schedule> findAllByPetListIn(List<Pet> pets);

}
