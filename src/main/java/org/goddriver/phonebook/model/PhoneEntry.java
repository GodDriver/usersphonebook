package org.goddriver.phonebook.model;

public class PhoneEntry {

    final private String fullName;
    final private String phoneNumber;

    private void check(String fullName,
                       String phoneNumber) {
        if (fullName == null || fullName.isEmpty()
            || phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public PhoneEntry(String fullName,
                      String phoneNumber) {
        check(fullName, phoneNumber);
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        if (fullName == null) {
            throw new IllegalArgumentException();
        }
        return fullName;
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) {
            throw new IllegalArgumentException();
        }
        return phoneNumber;
    }

    public PhoneEntryBuilder toBuilder() {
        return new PhoneEntryBuilder(this.fullName, this.phoneNumber);
    }

}
