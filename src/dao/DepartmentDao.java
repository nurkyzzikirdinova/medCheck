package dao;

import generic.GenericService;
import model.Department;

import java.lang.reflect.Type;
import java.util.List;

public interface DepartmentDao  {
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);


}
