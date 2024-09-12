package finalassignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AppointmentManagementSystem system = new AppointmentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Register doctors
        System.out.println("Register doctors:");
        while (true) {
            System.out.print("Enter doctor name (or type '-1' to stop): ");
            String doctorName = scanner.nextLine();
            if (doctorName.equals("-1")) {
                break;
            }
            System.out.print("Enter doctor specialization: ");
            String specialization = scanner.nextLine();
            system.registerDoctor(new Doctor(doctorName, specialization));
        }

        // Register patients
        System.out.println("Register patients:");
        while (true) {
            System.out.print("Enter patient name (or type '-1' to stop): ");
            String patientName = scanner.nextLine();
            if (patientName.equals("-1")) {
                break;
            }
            System.out.print("Enter patient contact number: ");
            String contact = scanner.nextLine();
            system.registerPatient(new Patient(patientName, contact));
        }

        // Book appointments
        Date appointmentDate = null;
        while (appointmentDate == null) {
            try {
                System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm:ss): ");
                String dateString = scanner.nextLine();
                appointmentDate = dateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in 'yyyy-MM-dd HH:mm:ss' format.");
            }
        }

        if (!system.getDoc().isEmpty() && !system.getPatients().isEmpty()) {
            System.out.println("Booking appointments:");
            for (int i = 0; i < Math.min(system.getDoc().size(), system.getPatients().size()); i++) {
                Doctor doctor = system.getDoc().get(i);
                Patient patient = system.getPatients().get(i);
                system.bookAppointment(appointmentDate, doctor.getName(), patient.getName());
                System.out.println("Booked appointment with Dr. " + doctor.getName() + " for " + patient.getName() + " on " + dateFormat.format(appointmentDate));
            }
        }

        // Display available doctors
        List<Doctor> availableDoctors = system.getAvailableDoctors(appointmentDate);
        System.out.println("Available doctors on " + dateFormat.format(appointmentDate) + ":");
        for (Doctor doctor : availableDoctors) {
            System.out.println(doctor.getName() + " - " + doctor.getSpecialization());
        }

        // Display appointments for a doctor
        if (!system.getDoc().isEmpty()) {
            System.out.print("Enter doctor name to view appointments: ");
            String doctorName = scanner.nextLine();
            List<Appointment> doctorAppointments = system.getAppointmentsForDoctor(doctorName);
            System.out.println("Appointments for Dr. " + doctorName + ":");
            for (Appointment appointment : doctorAppointments) {
                System.out.println(appointment.getDate() + " - " + appointment.getPatientName() + " (Patient)");
            }
        }

        // Display appointments for a patient
        if (!system.getPatients().isEmpty()) {
            System.out.print("Enter patient name to view appointments: ");
            String patientName = scanner.nextLine();
            List<Appointment> patientAppointments = system.getAppointmentsForPatient(patientName);
            System.out.println("Appointments for " + patientName + ":");
            for (Appointment appointment : patientAppointments) {
                System.out.println(appointment.getDate() + " - " + appointment.getDoctorName() + " (Doctor)");
            }
        }

        // Save data to files
        system.storeFile();
        System.out.println("Data saved to files.");
        
        scanner.close();
    }
}
