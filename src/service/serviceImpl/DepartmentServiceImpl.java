package service.serviceImpl;

import dao.DepartmentDao;
import dao.daoImpl.DepartmentDaoImpl;
import database.Database;
import generic.GenericService;
import model.Department;
import model.Hospital;
import service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();


    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId, department);
    }

    @Override
    public void removeById(Long id) {
        departmentDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Department department) {
        return departmentDao.updateById(id, department);
    }


    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {

        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }
}
