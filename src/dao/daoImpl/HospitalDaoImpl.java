package dao.daoImpl;

import dao.HospitalDao;
import database.Database;
import model.Hospital;
import model.Patient;

import java.util.*;

import static database.Database.hospitals;

public class HospitalDaoImpl implements HospitalDao {


    @Override
    public String addHospital(Hospital hospital) {
        hospitals.add(hospital);
        return "Hospital added successfully";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital;
            }
        }
        throw new NoSuchElementException("Hospital with id: "+id+ " not found!");
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : hospitals) {
            if (hospital.getId() == id) {
                return hospital.getPatients();
            }
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId() != null && hospital.getId().equals(id)) {
                Database.hospitals.remove(hospital);
                return "Deleted hospital in id " + id;
            }
        }
        return "Not found hospital like this id";
    }

    @Override
    public Map<String, List<Hospital>> getAllHospitalByAddress(String address) {
        List<Hospital> foundHospitalByAddress = new ArrayList<>();
        for (Hospital hospital : hospitals) if (hospital.getAddress().equals(address)) foundHospitalByAddress.add(hospital);
        return Map.of(address, foundHospitalByAddress);
    }
}

