package dao.daoImpl;

import dao.DepartmentDao;
import database.Database;
import generic.GenericService;
import model.Department;
import model.Hospital;
import myException.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static database.Database.hospitals;

public class DepartmentDaoImpl implements DepartmentDao, GenericService<Department> {


    @Override
    public String add(Long hospitalId, Department department) {
        try{
            for (Hospital hospital : Database.hospitals) {
                if(hospital.getId().equals(hospitalId)){
                    hospital.getDepartments().add(department);
                    return "Successfully added";
                }
            }
            throw  new MyException("No such hospital id");
        }catch (MyException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }




@Override
    public void removeById(Long id) {
        try {
            boolean removed = false;
            for (Hospital hospital : Database.hospitals) {
                List<Department> departments = hospital.getDepartments();
                for (Department department : departments) {
                    if (department.getId().equals(id)) {
                        departments.remove(department);
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    System.out.println("Department with ID " + id + " removed successfully.");
                    return;
                }
            }
            throw new MyException("Department with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String updateById(Long id, Department department) {
        try {
            for (Hospital hospital : Database.hospitals) {
                List<Department> departments = hospital.getDepartments();
                for (Department dept : departments) {
                    if (dept.getId().equals(id)) {
                        dept.setDepartmentName(department.getDepartmentName());
                        return "Department with ID " + id + " updated successfully.";
                    }
                }
            }
            throw new MyException("Department with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        List<Department> departments = new ArrayList<>();
        for (Hospital hospital : Database.hospitals) {
            if(hospital.getId().equals(id)){
                return hospital.getDepartments();
            }
        }
        return departments;
    }

    public Department findDepartmentByName(String name) {
        for (Hospital hospital : Database.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getDepartmentName().equals(name)) {
                    return department;
                }
            }
        }
        return null;
    }
}
