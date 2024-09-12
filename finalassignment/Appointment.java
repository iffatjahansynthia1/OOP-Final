package finalassignment;

import java.util.Date;

class Appointment {
    private Date date;
    private String doctorName;
    private String patientName;

    public Appointment(Date date, String doctorName, String patientName) {
        this.date = date;
        this.doctorName = doctorName;
        this.patientName = patientName;
    }

    public Date getDate() {
        return date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    @Override
    public String toString() {
        return String.format("Appointment: Date: %s, Doctor: %s, Patient: %s",
                date, doctorName, patientName);
    }
}
