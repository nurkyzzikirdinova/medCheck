//
////
//1)  Оорукана тууралу бир программа жазыныз. Dao жана Service менен иштешкиле (ар бир класстын dao жана service interface'тери жана ошол interface'ти implements класстары болуш керек). Database деген класс ачып, ичинде бардык маалыматтарды сактаган маалымат структурасы болсун(List<Hospital> hospitals).
//
//        2) Класстар:
//        * Hospital (Long id, String hospitalName, String address, List<Department> departments, List<Doctor> doctors, List<Patient> patients),
//       * Department (Long id, String departmentName, List<Doctor> doctors),
//       * Doctor (Long id, String firstName, String lastName, Gender gender, int experienceYear),
//       * Patient (Long id, String firstName, String lastName, int age, Gender gender);3) Gender enum.
//
//        3)
//
//HospitalService :
//
//String addHospital(Hospital hospital);
//Hospital findHospitalById(Long id);
//List<Hospital> getAllHospital();
//List<Patient> getAllPatientFromHospital(Long id);
//String deleteHospitalById(Long id);
//Map<String, Hospital> getAllHospitalByAddress(String address);
//
//
//
//GenericService : (Departmet, Doctor , Patient)
//
//
//String add(Long  hospitalId, T t);
//
//void removeById(Long id);
//
//String updateById(Long id, T t);
//
//
//
//DepartmentService:
//
//
//List<Department> getAllDepartmentByHospital(Long id);
//Department findDepartmentByName(String name);
//
//
//
//DoctorService:
//
//
//Doctor findDoctorById(Long id);
//
//// Department‘ти id менен табып, анан Doctor‘лорду листтеги айдилери менен табып анан ошол табылган Department'ге табылган Doctor'лорду кошуп коюнунуз
//String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);
//List<Doctor> getAllDoctorsByHospitalId(Long id);
//List<Doctor> getAllDoctorsByDepartmentId(Long id);
//
//
//
//PatientService:
//
//
//String addPatientsToHospital(Long id,List<Patient> patients);
//Patient getPatientById(Long id);
//Map<Integer, Patient> getPatientByAge();
//List<Patient> sortPatientsByAge(String ascOrDesc);

import enums.Gender;
import generic.GenericService;
import id.GeneratedId;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import service.DepartmentService;
import service.HospitalService;
import service.PatientService;
import service.serviceImpl.DepartmentServiceImpl;
import service.serviceImpl.DoctorServiceImpl;
import service.serviceImpl.HospitalServiceImpl;
import service.serviceImpl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        HospitalService hospitalService = new HospitalServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();
        GenericService<Department> genericServiceDepartment = new DepartmentServiceImpl();
        GenericService<Doctor> genericServiceDoctor = new DoctorServiceImpl();
        GenericService<Patient> genericServicePatient = new PatientServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        boolean isTrueM = true;
        while (isTrueM) {
            System.out.println("""
                    1. Hospital
                    2. Department
                    3. Doctor
                    4. Patient
                    """);
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> {
                    boolean exit = true;
                    while (exit) {
                        System.out.println("""
                                1. Add hospital              4. Get all patient from hospital
                                2. Find hospital By id       5. Delete hospital by id
                                3. Get all hospital          6. Get all hospital by address
                                                      0. Exit
                                                       """);

                        switch (scannerInt.nextInt()) {
                            case 1 -> {
                                Hospital hospital = new Hospital();
                                System.out.println("Add hospital");
                                hospital.setId(GeneratedId.getHospitalId());
                                System.out.println("Hospital id: " + hospital.getId());
                                System.out.println("Write hospital name: ");
                                hospital.setHospitalName(scannerStr.nextLine());
                                System.out.println("Write hospital address: ");
                                hospital.setAddress(scannerStr.nextLine());
                                hospital.setDoctors(new ArrayList<>());
                                hospital.setPatients(new ArrayList<>());
                                System.out.println(hospitalService.addHospital(hospital));

                            }

                            case 2 -> {
                                System.out.println("Write hospital id: ");
                                Long hosId = scannerInt.nextLong();
                                System.out.println(hospitalService.findHospitalById(hosId));

                            }
                            case 3 -> {
                                System.out.println("All hospitals");
                                System.out.println(hospitalService.getAllHospital());
                            }
                            case 4 -> {
                                System.out.println("Write hospital id: ");
                                System.out.println(hospitalService.getAllPatientFromHospital(
                                        new Scanner(System.in).nextLong()
                                ));
                            }
                            case 5 -> {
                                System.out.println("Write hospital id for delete: ");
                                System.out.println(hospitalService.deleteHospitalById(scannerInt.nextLong()));

                            }
                            case 6 -> {
                                System.out.println("Write hospital address: ");
                                System.out.println(hospitalService.getAllHospitalByAddress(scannerStr.nextLine()));
                            }
                            case 0 -> exit = false;
                        }

                    }
                }


                case 2 -> {
                    boolean exitD = true;
                    while (exitD) {
                        System.out.println("""
                                1. Add department              4. Get all department by hospital
                                2. Remove department by id     5. Find department by name
                                3. Update department by id     0. Exit
                                                    
                                                       """);
                        Department department = new Department();
                        try {
                            switch (scannerInt.nextInt()) {
                                case 1 -> {

                                    System.out.println("Write hospital id: ");
                                    Long hospitalId = new Scanner(System.in).nextLong();
                                    System.out.println("Department id:");
                                    department.setId(GeneratedId.getDepartmentId());
                                    System.out.println(department.getId());
                                    System.out.println("Write name of department: ");
                                    String name = new Scanner(System.in).nextLine();
                                    department.setDepartmentName(name);
                                    System.out.println("Doctors");
                                    department.setDoctors(new ArrayList<>());
                                    System.out.println(genericServiceDepartment.add(hospitalId, department));
                                }
                                case 2 -> {
                                    System.out.println("Write id for remove:");
                                    Long depId = new Scanner(System.in).nextLong();
                                    genericServiceDepartment.removeById(depId);
                                }
                                case 3 -> {
                                    System.out.println("Write id: ");
                                    Long depId = new Scanner(System.in).nextLong();
                                    System.out.println("Write name of department: ");
                                    String name = new Scanner(System.in).nextLine();
                                    department.setDepartmentName(name);
                                    genericServiceDepartment.updateById(depId, department);
                                    System.out.println("successfully updatedMai");
                                }
                                case 4 -> {
                                    System.out.println("Write hospital id: ");
                                    Long hosId = new Scanner(System.in).nextLong();
                                    System.out.println(departmentService.getAllDepartmentByHospital(hosId));
                                }
                                case 5 -> {
                                    System.out.println("Write department name for find : ");
                                    String name = new Scanner(System.in).nextLine();
                                    department.setDepartmentName(name);
                                    System.out.println(departmentService.findDepartmentByName(name));
                                }
                                case 0 -> exitD = false;
                            }
                        } catch (RuntimeException e) {
                            System.out.println("write number");
                        } catch (Exception e) {
                            System.out.println("exception");
                        }

                    }
                }

                case 3 -> {
                    boolean exitDoctor = true;
                    while (exitDoctor) {
                        System.out.println("""
                                1. Add doctor               5. Assign doctor to department
                                2. Remove doctor by id      6. Get all doctors by hospital id
                                3. Update doctor by id      7. Get all doctors by department id     
                                4. Find doctor by id        0. Exit            
                                                                                       """);

                        try {
                            switch (scannerInt.nextInt()) {
                                case 1 -> {
                                    Doctor doctor = new Doctor();
                                    System.out.println("Write hospital id: ");
                                    Long hospitalId = new Scanner(System.in).nextLong();
                                    doctor.setId(GeneratedId.getDoctorId());
                                    System.out.println("Doctor id:" + doctor.getId());
                                    System.out.println("Write name of doctor: ");
                                    doctor.setName(new Scanner(System.in).nextLine());
                                    System.out.println("Write gender (FEMALE/MALE)");
                                    doctor.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                    System.out.println("Write experience year:");
                                    doctor.setExperienceYear(new Scanner(System.in).nextInt());
                                    System.out.println(genericServiceDoctor.add(hospitalId, doctor));
                                }
                                case 2 -> {
                                    System.out.println("Write id for remove:");
                                    Long docId = new Scanner(System.in).nextLong();
                                    genericServiceDoctor.removeById(docId);
                                }
                                case 3 -> {
                                    Doctor doctor = new Doctor();
                                    System.out.println("Write current doctor id: ");
                                    Long docId = new Scanner(System.in).nextLong();
                                    System.out.println("Write new name of doctor: ");
                                    doctor.setName(new Scanner(System.in).nextLine());
                                    System.out.println("Write new your gender: ");
                                    doctor.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                    System.out.println("Experience: ");
                                    doctor.setExperienceYear(new Scanner(System.in).nextInt());
                                    System.out.println(genericServiceDoctor.updateById(docId, doctor));
                                }
                                case 4 -> {
                                    System.out.println("Write doctor id for find: ");
                                    Long docId = new Scanner(System.in).nextLong();
                                    System.out.println(doctorService.findDoctorById(docId));
                                }
                                case 5 -> {
                                    System.out.println("Assign doctor to department");
                                    System.out.println("Write department id: ");
                                    Long depId = new Scanner(System.in).nextLong();
                                    System.out.println("Write doctor id: ");
                                    Long docId = new Scanner(System.in).nextLong();
                                    System.out.println(doctorService.assignDoctorToDepartment(depId, Collections.singletonList(docId)));

                                }
                                case 6 -> {
                                    System.out.println("Get all doctors by hospital id");
                                    System.out.println("Write hospital id: ");
                                    Long hosId = new Scanner(System.in).nextLong();
                                    System.out.println(doctorService.getAllDoctorsByHospitalId(hosId));
                                }
                                case 7 -> {
                                    System.out.println("Get all doctors by department id ");
                                    System.out.println("Write hospital id: ");
                                    Long depId = new Scanner(System.in).nextLong();
                                    System.out.println(doctorService.getAllDoctorsByDepartmentId(depId));
                                }
                                case 0 -> exitDoctor = false;

                            }


                        } catch (RuntimeException e) {
                            System.out.println("write number");
                        }
                    }
                }

                case 4 -> {
                    boolean exitPatient = true;
                    while (exitPatient) {
                        System.out.println("""
                                1. Add patient                5. Get patient  by id
                                2. Remove patient by id       6. Get patient by age
                                3. Update patient by id       7. Sort patients by age   
                                4. Add patients to hospital   0. Exit         
                                                      
                                            """);


                        try {
                            switch (scannerInt.nextInt()) {

                                case 1 -> {
                                    Patient patient = new Patient();
                                    System.out.println("Write hospital id: ");
                                    Long hospitalId = new Scanner(System.in).nextLong();
                                    patient.setId(GeneratedId.getDoctorId());
                                    System.out.println("Patient id:" + patient.getId());
                                    System.out.println("Write name of patient: ");
                                    patient.setName(new Scanner(System.in).nextLine());
                                    System.out.println("Patient age: ");
                                    patient.setAge(new Scanner(System.in).nextInt());
                                    System.out.println("Write gender (FEMALE/MALE)");
                                    patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                    System.out.println(genericServicePatient.add(hospitalId, patient));
                                }
                                case 2 -> {
                                    System.out.println("Write id for remove:");
                                    Long pacId = new Scanner(System.in).nextLong();
                                    genericServicePatient.removeById(pacId);
                                }
                                case 3 -> {
                                    Patient patient = new Patient();
                                    System.out.println("Write id for uptade: ");
                                    Long pacId = new Scanner(System.in).nextLong();
                                    System.out.println("Write new name: ");
                                    patient.setName(new Scanner(System.in).nextLine());
                                    System.out.println("Write new age: ");
                                    patient.setAge(new Scanner(System.in).nextInt());
                                    System.out.println("WRITE GENDER:");
                                    patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                    System.out.println(genericServicePatient.updateById(pacId, patient));
                                }
                                case 4 -> {
                                    System.out.println("Write Hospital ID:");
                                    Long hospitalId = new Scanner(System.in).nextLong();
                                    List<Patient> patients = new ArrayList<>();
                                    boolean isTrue = false;
                                    do {
                                        Patient patient = new Patient();
                                        System.out.println("Write name patient");
                                        patient.setName(new Scanner(System.in).nextLine());
                                        System.out.println("Write age patient");
                                        patient.setAge(new Scanner(System.in).nextInt());
                                        patient.setId(GeneratedId.getPatientId());
                                        System.out.println("Patient id" + patient.getId());
                                        System.out.println("Gender ");
                                        patient.setGender(Gender.valueOf(new Scanner(System.in).nextLine()));
                                        patients.add(patient);

                                        System.out.println("Do you want to add patient");
                                        String ask = new Scanner(System.in).nextLine();
                                        if (ask.equalsIgnoreCase("no")) {
                                            isTrue = true;
                                            System.out.println("Successfully added");
                                        } else if (ask.equalsIgnoreCase("yes")) {
                                            System.out.println(patientService.addPatientsToHospital(hospitalId, patients));
                                            System.out.println("You can add patients: ");
                                            isTrue = false;

                                        }
                                    } while (!isTrue);

                                }

                                case 5 -> {
                                    System.out.println("Write patient ID:");
                                    Long patientId = new Scanner(System.in).nextLong();
                                    System.out.println(patientService.getPatientById(patientId));
                                }
                                case 6 -> {
                                    System.out.println("Write age : ");
                                    System.out.println(patientService.getPatientByAge(new Scanner(System.in).nextInt()));
                                }
                                case 7 -> {
                                    System.out.println("Sort patients by age (ascOrDesc): ");
                                    String ascOrDesc = new Scanner(System.in).nextLine();
                                    List<Patient> sortedPatients = new ArrayList<>();
                                    System.out.println("Sorted Patients:"+patientService.sortPatientsByAge(ascOrDesc));
                                    for (Patient p : sortedPatients) {
                                        System.out.println(p);
                                    }
                                }

                                case 0 -> exitPatient = false;

                            }
                        } catch (RuntimeException e) {
                            System.out.println("write number");
                        }
                    }
                }
            }
        }
    }
}









