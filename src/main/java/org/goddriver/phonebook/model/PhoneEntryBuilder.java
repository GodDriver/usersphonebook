package org.goddriver.phonebook.model;

public class PhoneEntryBuilder {

    private String fullName;
    private String phoneNumber;

    public PhoneEntryBuilder(String fullName,
                             String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public PhoneEntryBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public PhoneEntryBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PhoneEntry build() {
        return new PhoneEntry(this.fullName, this.phoneNumber);
    }
}
