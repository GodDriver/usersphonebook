package org.goddriver.phonebook.model;

import java.util.HashMap;

public class UserEntryBuilder {

    private long nextEntryId;
    private String fullName;
    private HashMap<Long, PhoneEntry> phoneEntries;

    public UserEntryBuilder(long nextEntryId,
                            String fullName,
                            HashMap<Long, PhoneEntry> phoneEntries) {
        this.nextEntryId = nextEntryId;
        this.fullName = fullName;
        this.phoneEntries = phoneEntries;
    }

    public UserEntryBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserEntryBuilder withPhoneEntry(PhoneEntry entry) {
        if (this.phoneEntries == null) {
            this.phoneEntries = new HashMap<>();
        }
        long id = this.nextEntryId++;
        this.phoneEntries.put(id, entry);
        return this;
    }

    public UserEntryBuilder withPhoneEntry(long id, PhoneEntry entry) {
        assert (this.phoneEntries != null && id < this.nextEntryId);
        this.phoneEntries.put(id, entry);
        return this;
    }

    public UserEntryBuilder withPhoneEntryAll(Iterable<PhoneEntry> entries) {
        if (this.phoneEntries == null) {
            this.phoneEntries = new HashMap<>();
        }
        entries.forEach((entry) -> {
            long id = this.nextEntryId++;
            this.phoneEntries.put(id, entry);
        });
        return this;
    }

    public UserEntry build() {
        return new UserEntry(this.nextEntryId,
                             this.fullName,
                             this.phoneEntries);
    }
}
