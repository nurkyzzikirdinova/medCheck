package dao.daoImpl;

import dao.PatientDao;
import database.Database;
import generic.GenericService;
import model.Doctor;
import model.Hospital;
import model.Patient;
import myException.MyException;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericService<Patient> {

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId().equals(hospitalId)) {
                    hospital.getPatients().add(patient);
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
                List<Patient> patients = hospital.getPatients();
                for (Patient patient : patients) {
                    if (patient.getId().equals(id)) {
                        patients.remove(patient);
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    System.out.println("Patient with ID " + id + " removed successfully.");
                    return;
                }
            }
            throw new MyException("Patient with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
            for (Hospital hospital : Database.hospitals) {
                List<Patient> patients = hospital.getPatients();
                ;
                for (Patient patient1 : patients) {
                    if (patient1.getId().equals(id)) {
                        patient1.setName(patient1.getName());
                        return "Patient with ID " + id + " updated successfully.";
                    }
                }
            }
            throw new MyException("Patient with ID " + id + " not found.");
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (hospital.getId().equals(id)) {
                    hospital.getPatients().addAll(patients);
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
    public Patient getPatientById(Long id) {

        for (Hospital hospital : Database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    return patient;

                }
            }

            }
        throw new RuntimeException("Patient not found");
        }




    @Override
    public Map<Integer, Patient> getPatientByAge(int age) {
        Map<Integer, Patient> patientMap = new HashMap<>();
        try {
            for (Hospital hospital : Database.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getAge() == age) {
                        patientMap.put(age, patient);
                        return patientMap;
                    } else System.out.println("Not such age");
                }
            }

            throw new MyException("Patient not found");
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            for (Hospital hospital : Database.hospitals) {
                if (ascOrDesc.equals("asc")) {
                    hospital.getPatients().sort(Comparator.comparing(Patient::getAge));
                    return hospital.getPatients();
                } else if (ascOrDesc.equals("desc")) {
                    hospital.getPatients().sort((o1, o2) -> Integer.compare(o2.getAge(), o1.getAge()));
                    return hospital.getPatients();
                }

            }
            throw new MyException("Patient not found");
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
    }


}
