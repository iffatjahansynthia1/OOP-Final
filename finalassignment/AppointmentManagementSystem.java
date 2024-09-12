package finalassignment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class AppointmentManagementSystem {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public List<Doctor> getDoc() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void registerPatient(Patient patient) {
        patients.add(patient);
    }

    public void bookAppointment(Date date, String doctorName, String patientName) {
        Doctor doctor = findDoctorByName(doctorName);
        Patient patient = findPatientByName(patientName);

        if (doctor != null && patient != null) {
            doctor.addAppointment(date, patientName);
            appointments.add(new Appointment(date, doctorName, patientName));
        }
    }

    public void cancelAppointment(Date date, String doctorName, String patientName) {
        Doctor doctor = findDoctorByName(doctorName);
        Patient patient = findPatientByName(patientName);

        if (doctor != null && patient != null) {
            doctor.removeAppointment(date, patientName);
            appointments.removeIf(a -> a.getDate().equals(date) && a.getDoctorName().equals(doctorName) && a.getPatientName().equals(patientName));
        }
    }

    public List<Doctor> getAvailableDoctors(Date date) {
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (!doctor.getAppointments().containsKey(date) || doctor.getAppointments().get(date).isEmpty()) {
                availableDoctors.add(doctor);
            }
        }
        return availableDoctors;
    }

    public List<Appointment> getAppointmentsForDoctor(String doctorName) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorName().equals(doctorName)) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    public List<Appointment> getAppointmentsForPatient(String patientName) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientName().equals(patientName)) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    private Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    private Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    public void storeFile() {
        try {
            File file = new File("Doc.txt");
            FileWriter fileWriter = new FileWriter(file);
            for (Doctor x : doctors) {
                fileWriter.write(x.toString() + System.lineSeparator());
            }
            fileWriter.close();

            file = new File("patient.txt");
            fileWriter = new FileWriter(file);
            for (Patient x : patients) {
                fileWriter.write(x.toString() + System.lineSeparator());
            }
            fileWriter.close();

            file = new File("appointment.txt");
            fileWriter = new FileWriter(file);
            for (Appointment x : appointments) {
                fileWriter.write(x.toString() + System.lineSeparator());
            }
            fileWriter.close();

            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
