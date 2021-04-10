package org.goddriver.phonebook.controller;

import org.goddriver.phonebook.model.Model;
import org.goddriver.phonebook.model.UserEntry;
import org.goddriver.phonebook.model.UserEntryJsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private Model model;

    @GetMapping("/")
    public ArrayList<Long> getAllUserEntries() {
        return model.getAllUserEntries();
    }

    @PutMapping("/{name}")
    public void createUserEntry(@PathVariable String name) {
        model.createUserEntry(name);
    }

    @GetMapping("/{id}")
    public UserEntryJsonSerializer getUserEntry(@PathVariable long id) {
        return model.getUserEntry(id);
    }

    @DeleteMapping("/{id}")
    public void removeUserEntry(@PathVariable long id) {
        model.removeUserEntry(id);
    }

    @PostMapping("/{id}/{name}")
    public void updateUserEntry(@PathVariable long id,
                                @PathVariable String name) {
        model.updateUserEntry(id, name);
    }

    @GetMapping("/by-name/{name}")
    public UserEntry[] getUserEntryByName(@PathVariable String name) {
        return model.getUserEntryByName(name);
    }
}
