package finalassignment;

class Patient {
    private String name;
    private String contact;

    public Patient(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " (Contact: " + contact + ")";
    }
}
