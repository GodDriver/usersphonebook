package org.goddriver.phonebook.model;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Model {

    final private AtomicLong countId = new AtomicLong(0);
    final private ConcurrentHashMap<Long, UserEntry> users;

    public Model() {
        users = new ConcurrentHashMap<>();
    }

    public ArrayList<Long> getAllUserEntries() {
        //Получение списка всех пользователей (владельцев телефонных книжек)
        return new ArrayList<>(users.keySet());
    }

    public void createUserEntry(String fullName) {
        //Создание пользователя
        long nextId = countId.incrementAndGet();
        users.put(nextId, new UserEntry(fullName));
    }

    public UserEntry getUserEntry(long id) {
        //Получение (по id) пользователя
        return users.get(id);
    }

    public void removeUserEntry(long id) {
        //Удаление пользователя
        users.remove(id);
    }

    public void updateUserEntry(long id,
                                String fullName) {
        //Редактирование пользователя
        users.computeIfPresent(id, (key, val) -> val.updateFullName(fullName));
    }

    public void createUserPhoneEntry(long id,
                                     String fullName,
                                     String phoneNumber) {
        //Создание записи пользователя
        users.computeIfPresent(id, (key, val) -> val.addPhoneEntry(fullName, phoneNumber));
    }

    public PhoneEntry getUserPhoneEntry(long id,
                                        long entryId) {
        //Получение (по id) записи пользователя
        return users.get(id).getPhoneEntries().get(entryId);
    }
    public void removeUserPhoneEntry(long id,
                                     long entryId) {
        //Удаление записи пользователя
        users.computeIfPresent(id, (key, val) -> val.removePhoneEntry(entryId));
    }
    public void updateUserPhoneEntry(long id,
                                     long entryId,
                                     String fullName,
                                     String phoneNumber) {
        //Редактирование записи пользователя
        users.computeIfPresent(id, (key, val) -> val.updatePhoneEntry(entryId, fullName, phoneNumber));
    }
    public HashMap<Long, PhoneEntry> getAllPhoneEntriesFromUserEntry(long id) {
        //Получение списка всех записей в телефонной книжке пользователя
        return users.get(id).getPhoneEntries();
    }
    public UserEntry[] getUserEntryByName(String name) {
        //Поиск пользователя по имени (или его части)
        return users.values().stream()
                .filter(userEntry -> userEntry.getFullName().contains(name))
                .toArray(UserEntry[]::new);
    }
    public PhoneEntry getPhoneEntryByPhoneNumber(long id,
                                                 String phoneNumber) throws NullPointerException {
        //Поиск телефонной записи по номеру телефона
        return users.get(id).getPhoneEntries().values().stream()
                .filter(phoneEntry -> phoneEntry.getPhoneNumber().contains(phoneNumber))
                .findFirst()
                .orElse(null);
    }
}
