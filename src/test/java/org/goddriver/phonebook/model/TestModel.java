package org.goddriver.phonebook.model;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestModel {

    private final Model model = new Model();

    @Test
    @DisplayName("Получение списка всех пользователей(владельцев телефонных книжек)")
    public void testGetAllUserEntries() {
        assertEquals(model.getAllUserEntries(), new ArrayList<>());

        model.createUserEntry("Chuck Norris");
        model.createUserEntry("Bruce Willis");
        assertEquals(model.getAllUserEntries(), new ArrayList<>(Arrays.asList(1L, 2L)));

        model.removeUserEntry(2);
        model.createUserEntry("Sylvester Stallone");
        assertEquals(model.getAllUserEntries(), new ArrayList<>(Arrays.asList(1L, 3L)));
    }

    @Test
    @DisplayName("Создание и получение(по id) пользователя")
    public void testCreateAndGetUserEntry() {
        String name = "Sylvester Stallone";
        model.createUserEntry(name);
        UserEntry userEntry = model.getUserEntry(1);
        assertNotNull(userEntry);
        assertSame(userEntry.getClass(), UserEntry.class);
        assertEquals(userEntry.getFullName(), name);
        assertEquals(userEntry.getPhoneEntries().size(), 0);
    }

    @Test
    @DisplayName("Создание пользователя c именем null")
    public void testCreateUserEntryWithNullFullName() {
        try {
            model.createUserEntry(null);
            UserEntry userEntry = model.getUserEntry(1);
            assertNotNull(userEntry);
            assertSame(userEntry.getClass(), UserEntry.class);
            assertNull(userEntry.getFullName());
            assertEquals(userEntry.getPhoneEntries().size(), 0);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Создание пользователя c пустым именем")
    public void testCreateUserEntryWithEmptyFullName() {
        try {
            String name = "";
            model.createUserEntry(name);
            UserEntry userEntry = model.getUserEntry(1);
            assertNotNull(userEntry);
            assertSame(userEntry.getClass(), UserEntry.class);
            assertEquals(userEntry.getFullName(), name);
            assertEquals(userEntry.getPhoneEntries().size(), 0);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Удаление пользователя")
    public void testRemoveUserEntry() {
        model.createUserEntry("Chuck Norris");
        model.createUserEntry("Bruce Willis");
        assertEquals(model.getAllUserEntries().size(), 2);
        model.removeUserEntry(1);
        assertEquals(model.getAllUserEntries().size(), 1);
    }

    @Test
    @DisplayName("Редактирование пользователя")
    public void testUpdateUserEntry() {
        model.createUserEntry("Chuck Norris");
        UserEntry userEntry = model.getUserEntry(1);
        assertNotNull(userEntry);
        assertSame(userEntry.getClass(), UserEntry.class);
        assertEquals(userEntry.getFullName(), "Chuck Norris");
        assertEquals(userEntry.getPhoneEntries().size(), 0);
        model.updateUserEntry(1, "Bruce Willis");
        userEntry = model.getUserEntry(1);
        assertNotNull(userEntry);
        assertSame(userEntry.getClass(), UserEntry.class);
        assertEquals(userEntry.getFullName(), "Bruce Willis");
        assertEquals(userEntry.getPhoneEntries().size(), 0);
    }

    @Test
    @DisplayName("Создание и получение(по id) записи в телефонной книжке")
    public void testCreateAndGetUserPhoneEntry() {
        model.createUserEntry("Chuck Norris");
        String name = "Bruce Willis";
        String phoneNumber = "+7 (900) 000-00-01";
        model.createUserPhoneEntry(1, name, phoneNumber);
        PhoneEntry phoneEntry = model.getUserPhoneEntry(1, 1);
        assertNotNull(phoneEntry);
        assertSame(phoneEntry.getClass(), PhoneEntry.class);
        assertEquals(phoneEntry.getFullName(), name);
        assertEquals(phoneEntry.getPhoneNumber(), phoneNumber);
    }

    @Test
    @DisplayName("Создание записи в телефонной книжке c именем null и телефонным номером null")
    public void testCreateUserPhoneEntryWithNullFields() {
        try {
            model.createUserEntry("Chuck Norris");
            model.createUserPhoneEntry(1, null, null);
            PhoneEntry phoneEntry = model.getUserPhoneEntry(1, 1);
            assertNotNull(phoneEntry);
            assertSame(phoneEntry.getClass(), PhoneEntry.class);
            assertNull(phoneEntry.getFullName());
            assertNull(phoneEntry.getPhoneNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Создание записи в телефонной книжке c пустым именем и пустым телефонным номером")
    public void testCreateUserPhoneEntryWithEmptyFields() {
        try {
            model.createUserEntry("Chuck Norris");
            String name = "";
            String phoneNumber = "";
            model.createUserPhoneEntry(1, name, phoneNumber);
            PhoneEntry phoneEntry =  model.getUserPhoneEntry(1, 1);
            assertNotNull(phoneEntry);
            assertSame(phoneEntry.getClass(), PhoneEntry.class);
            assertEquals(phoneEntry.getFullName(), name);
            assertEquals(phoneEntry.getPhoneNumber(), phoneNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Удаление записи в телефонной книжке")
    public void testRemoveUserPhoneEntry() {
        model.createUserEntry("Chuck Norris");
        model.createUserPhoneEntry(1, "Bruce Willis", "+7 (900) 000-00-01");
        model.createUserPhoneEntry(1, "Sylvester Stallone", "+7 (900) 000-00-02");
        assertEquals(model.getAllPhoneEntriesFromUserEntry(1).size(), 2);
        model.removeUserPhoneEntry(1, 1);
        assertEquals(model.getAllPhoneEntriesFromUserEntry(1).size(), 1);
    }

    @Test
    @DisplayName("Редактирование записи в телефонной книжке")
    public void testUpdateUserPhoneEntry() {
        model.createUserEntry("Chuck Norris");
        model.createUserPhoneEntry(1, "Bruce Willis", "+7 (900) 000-00-01");
        assertEquals(model.getUserPhoneEntry(1, 1).getFullName(), "Bruce Willis");
        assertEquals(model.getUserPhoneEntry(1, 1).getPhoneNumber(), "+7 (900) 000-00-01");
        model.updateUserPhoneEntry(1, 1, "Sylvester Stallone", "+7 (900) 000-00-02");
        assertEquals(model.getUserPhoneEntry(1, 1).getFullName(), "Sylvester Stallone");
        assertEquals(model.getUserPhoneEntry(1, 1).getPhoneNumber(), "+7 (900) 000-00-02");
    }

    @Test
    @DisplayName("Получение списка всех записей в телефонной книжке пользователя")
    public void testGetAllPhoneEntriesFromUserEntry() {
        model.createUserEntry("Chuck Norris");
        assertEquals(model.getAllPhoneEntriesFromUserEntry(1).size(), 0);

        model.createUserPhoneEntry(1, "Bruce Willis", "+7 (900) 000-00-01");
        model.createUserPhoneEntry(1, "Sylvester Stallone", "+7 (900) 000-00-02");
        assertEquals(model.getAllPhoneEntriesFromUserEntry(1).size(), 2);
    }

    @Test
    @DisplayName("Поиск пользователя по имени(или его части)")
    public void testGetUserEntryByName() {
        String name = "is";
        model.createUserEntry("Chuck Norris");
        model.createUserEntry("Bruce Willis");
        assertEquals(model.getUserEntryByName(name).length, 2);

        name = "Chuck";
        assertEquals(model.getUserEntryByName(name).length, 1);

        name = "Grace";
        assertEquals(model.getUserEntryByName(name).length, 0);
    }

    @Test
    @DisplayName("Поиск телефонной записи по номеру телефона")
    public void testGetPhoneEntryByPhoneNumber() {
        model.createUserEntry("Chuck Norris");
        model.createUserPhoneEntry(1, "Bruce Willis", "+7 (900) 000-00-01");
        model.createUserPhoneEntry(1, "Sylvester Stallone", "+7 (900) 000-00-02");
        assertEquals(model.getPhoneEntryByPhoneNumber(1, "+7 (900) 000-00-01").getFullName(),
                "Bruce Willis");

        try {
            assertNull(model.getPhoneEntryByPhoneNumber(1, "+7 (900) 000-00-03").getFullName());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException " + e.getMessage());
        }
    }
}
