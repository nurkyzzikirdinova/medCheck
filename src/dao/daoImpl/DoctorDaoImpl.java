package dao.daoImpl;

import dao.DepartmentDao;
import dao.DoctorDao;
import database.Database;
import generic.GenericService;
import model.Department;
import model.Doctor;
import model.Hospital;
import myException.MyException;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericService<Doctor> {
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId().equals(hospitalId)) {
                    hospital.getDoctors().add(doctor);
                    return "Successfully added";
                }
            }
            throw new MyException("No such hospital id");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Long id) {

        try {
            boolean removed = false;
            for (Hospital hospital : Database.hospitals) {
                List<Doctor> doctors = hospital.getDoctors();
                for (Doctor doctor : doctors) {
                    if (doctor.getId().equals(id)) {
                        doctors.remove(doctor);
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    System.out.println("Doctor with ID " + id + " removed successfully.");
                    return;
                }
            }
            throw new MyException("Doctor with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
            for (Hospital hospital : Database.hospitals) {
                List<Doctor> doctors = hospital.getDoctors();
                for (Doctor doc : doctors) {
                    if (doc.getId().equals(id)) {
                        doc.setName(doctor.getName());
                        doc.setGender(doctor.getGender());
                        doc.setExperienceYear(doctor.getExperienceYear());
                        return "Doctor with ID " + id + " updated successfully.";
                    }
                }
            }
            throw new MyException("Doctor with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital : Database.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : Database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    for (Long doctorId : doctorsId) {
                        Doctor doctor = findDoctorById(doctorId);
                        if (doctor != null) {
                            department.getDoctors().add(doctor);
                        } else {
                            return "Doctor with ID " + doctorId + " not found!";
                        }
                    }
                    return "Doctors by id " + doctorsId + " successfully assigned to department " + department.getDepartmentName();
                }
            }
        }
        return "Department by id " + departmentId + " not found!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        List<Doctor> doctors = new ArrayList<>();
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital.getDoctors();
            }
        }

        return doctors;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        List<Doctor> doctors = new ArrayList<>();
        for (Hospital hospital : Database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    return department.getDoctors();
                }
            }
        }
        return doctors;
    }

}