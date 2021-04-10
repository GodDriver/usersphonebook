package org.goddriver.phonebook.model;

import java.util.HashMap;

public class UserEntry
        extends UserEntryJsonSerializer {

    final private long nextEntryId;
    final private String fullName;
    final private HashMap<Long, PhoneEntry> phoneEntries;

    private void check(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public UserEntry(String fullName) {
        check(fullName);
        this.nextEntryId = 1;
        this.fullName = fullName;
        this.phoneEntries = new HashMap<>();
    }

    public UserEntry(long nextEntryId,
                     String fullName,
                     HashMap<Long, PhoneEntry> phoneEntries) {
        check(fullName);
        this.nextEntryId = nextEntryId;
        this.fullName = fullName;
        this.phoneEntries = phoneEntries;
    }

    public String getFullName() {
        return fullName;
    }

    public HashMap<Long, PhoneEntry> getPhoneEntries() {
        return phoneEntries;
    }

    public UserEntry updateFullName(String fullName) {
        check(fullName);
        return this.toBuilder()
                .withFullName(fullName)
                .build();
    }

    public UserEntry addPhoneEntry(String fullName,
                                   String phoneNumber) {
        var entry = new PhoneEntry(fullName, phoneNumber);
        return this.toBuilder()
                .withPhoneEntry(entry)
                .build();
    }

    public UserEntry removePhoneEntry(long entryId) {
        HashMap<Long, PhoneEntry> newEntries = new HashMap<>(this.phoneEntries);
        newEntries.remove(entryId);
        return new UserEntry(this.nextEntryId,
                this.fullName,
                newEntries);
    }

    public UserEntry updatePhoneEntry(long entryId,
                                      String fullName,
                                      String phoneNumber) {
        var entry = new PhoneEntry(fullName, phoneNumber);
        return this.toBuilder()
                .withPhoneEntry(entryId, entry)
                .build();
    }

    public UserEntryBuilder toBuilder() {
        return new UserEntryBuilder(this.nextEntryId,
                                    this.fullName,
                                    this.phoneEntries);
    }
}
