package org.goddriver.phonebook.controller;

import org.goddriver.phonebook.model.Model;
import org.goddriver.phonebook.model.PhoneEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/contacts")
public class PhoneBookController {
    @Autowired
    private Model model;

    @PutMapping("/{id}")
    public void createUserPhoneEntry(@PathVariable long id,
                                     @RequestParam("name") String fullName,
                                     @RequestParam("phone") String phoneNumber) {
        model.createUserPhoneEntry(id, fullName, phoneNumber);
    }

    @GetMapping("/{id}/{entry-id}")
    public PhoneEntry getUserPhoneEntry(@PathVariable long id,
                                        @PathVariable("entry-id") long entryId) {
        return model.getUserPhoneEntry(id, entryId);
    }

    @DeleteMapping("/{id}/{entry-id}")
    public void removeUserPhoneEntry(@PathVariable long id,
                                     @PathVariable("entry-id") long entryId) {
        model.removeUserPhoneEntry(id, entryId);
    }

    @PostMapping("/{id}/{entry-id}")
    public void updateUserPhoneEntry(@PathVariable long id,
                                     @PathVariable("entry-id") long entryId,
                                     @RequestParam("name") String fullName,
                                     @RequestParam("phone") String phoneNumber) {
        model.updateUserPhoneEntry(id, entryId, fullName, phoneNumber);
    }

    @GetMapping("/{id}")
    public HashMap<Long, PhoneEntry> getAllPhoneEntriesFromUserEntry(@PathVariable long id) {
        return model.getAllPhoneEntriesFromUserEntry(id);
    }

    @GetMapping("/{id}/by-phone-number/{phone}")
    public PhoneEntry getPhoneEntryByPhoneNumber(@PathVariable long id,
                                                 @PathVariable("phone") String phoneNumber) {
        return model.getPhoneEntryByPhoneNumber(id, phoneNumber);
    }
}
