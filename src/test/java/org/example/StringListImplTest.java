package org.example;

import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.example.Exception.StorageIsFullException;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StringListImplTest {

    private final StringListImpl stringListImpl = new StringListImpl();

    @org.junit.jupiter.api.Test
    void add_success() {
        Assertions.assertEquals(stringListImpl.add("1"), "1");
    }
    @org.junit.jupiter.api.Test
    void add_StorageIsFullException() {
        setUp();
        stringListImpl.add("9 ");
        assertThrows(StorageIsFullException.class, () -> stringListImpl.add("9"));
    }
    @org.junit.jupiter.api.Test
    void add_NullItemException() {
        setUp();
        assertThrows(NullItemException.class, () -> stringListImpl.add(null));
    }

    @org.junit.jupiter.api.Test
    void AddByIndex_success() {
        setUp();
        Assertions.assertEquals(stringListImpl.add(1, "t"), "t");
        Assertions.assertArrayEquals(stringListImpl.toArray(), new String[]{"0", "t","1", "2"});
    }
    @org.junit.jupiter.api.Test
    void AddByIndex_InvalidIndexException() {
        setUp();
        assertThrows(InvalidIndexException.class, () -> stringListImpl.add(5, "t"));
    }

    @org.junit.jupiter.api.Test
    void set_success() {
        setUp();
        Assertions.assertEquals(stringListImpl.set(1, "t"), "t");
        Assertions.assertArrayEquals(stringListImpl.toArray(), new String[]{"0", "t","2"});
    }

    @org.junit.jupiter.api.Test
    void remove_success() {
        setUp();
        stringListImpl.remove("0");
        Assertions.assertArrayEquals(stringListImpl.toArray(), new String[]{"1","2"});
    }

    @org.junit.jupiter.api.Test
    void RemoveByIndex() {
        setUp();
        Assertions.assertEquals(stringListImpl.remove(1), "1");
        Assertions.assertArrayEquals(stringListImpl.toArray(), new String[]{"0", "2"});
    }

    @org.junit.jupiter.api.Test
    void contains() {
        setUp();
        Assertions.assertTrue(stringListImpl.contains("0"));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        setUp();
        stringListImpl.set(0, "4");
        stringListImpl.set(1, "4");
        Assertions.assertEquals(stringListImpl.indexOf("4"), 0);
        Assertions.assertEquals(stringListImpl.indexOf("5"), -1);
    }

    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        setUp();
        stringListImpl.set(0, "4");
        stringListImpl.set(2, "4");
        Assertions.assertEquals(stringListImpl.lastIndexOf("4"), 2);
        Assertions.assertEquals(stringListImpl.indexOf("5"), -1);
    }

    @org.junit.jupiter.api.Test
    void get_success() {
        setUp();
        Assertions.assertEquals(stringListImpl.get(2), "2");
    }

    @org.junit.jupiter.api.Test
    void Equals_success() {
        setUp();
        StringListImpl newStringList = new StringListImpl();
        newStringList.add("0");
        newStringList.add("1");
        newStringList.add("2");
        Assertions.assertTrue(stringListImpl.equals(newStringList));
    }
    @org.junit.jupiter.api.Test
    void size_success() {
        setUp();
        stringListImpl.remove("1");
        Assertions.assertEquals(stringListImpl.size(), 2);
    }

    @org.junit.jupiter.api.Test
    void isEmpty_success() {
        stringListImpl.add("0");
        stringListImpl.remove("0");
        Assertions.assertTrue(stringListImpl.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clear_success() {
        stringListImpl.clear();
        Assertions.assertTrue(stringListImpl.isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void toArray_success() {
        setUp();
        Assertions.assertArrayEquals(stringListImpl.toArray(), new String[]{"0", "1", "2"});

    }

    private void setUp(){
        stringListImpl.add("0");
        stringListImpl.add("1");
        stringListImpl.add("2");
    }
}