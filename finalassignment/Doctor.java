package finalassignment;

import java.util.*;

class Doctor {
    private String name;
    private String specialization;
    private Map<Date, List<String>> appointments = new HashMap<>();

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Map<Date, List<String>> getAppointments() {
        return appointments;
    }

    public void addAppointment(Date date, String patientName) {
        List<String> patients = appointments.getOrDefault(date, new ArrayList<>());
        patients.add(patientName);
        appointments.put(date, patients);
    }

    public void removeAppointment(Date date, String patientName) {
        List<String> patients = appointments.get(date);
        if (patients != null) {
            patients.remove(patientName);
            if (patients.isEmpty()) {
                appointments.remove(date);
            }
        }
    }

    @Override
    public String toString() {
        return "Doctor: " + name + " (Specialization: " + specialization + ")";
    }
}
