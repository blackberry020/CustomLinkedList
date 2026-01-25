import org.example.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomLinkedListTest {

    private CustomLinkedList<String> list;

    @BeforeEach
    void setUp(){
        list = new CustomLinkedList<>();
    }

    @Test
    void testAddFirst(){
        list.addFirst("A");
        list.addFirst("B");
        assertEquals(2, list.size());
        assertEquals("B", list.getFirst());
        assertEquals("A", list.getLast());
    }

    @Test
    void testAddLast(){
        list.addLast("A");
        list.addLast("B");
        assertEquals(2, list.size());
        assertEquals("A", list.getFirst());
        assertEquals("B", list.getLast());
    }

    @Test
    void testAddByIndex(){
        list.addFirst("A");
        list.addLast("C");
        list.add(1, "B");
        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddByIndexToHeadAndTail(){
        list.add(0, "B");
        list.add(1, "C");
        list.add(0, "A");
        assertEquals("A", list.getFirst());
        assertEquals("C", list.getLast());
    }

    @Test
    void testGetFirstAndLast(){
        list.addLast("X");
        assertEquals("X", list.getFirst());
        assertEquals("X", list.getLast());
    }

    @Test
    void testGetByIndex(){
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testRemoveFirst(){
        list.addLast("A");
        list.addLast("B");
        String removed = list.removeFirst();
        assertEquals("A", removed);
        assertEquals(1, list.size());
        assertEquals("B", list.getFirst());
    }

    @Test
    void testRemoveLast(){
        list.addLast("A");
        list.addLast("B");
        String removed = list.removeLast();
        assertEquals("B", removed);
        assertEquals(1, list.size());
        assertEquals("A", list.getLast());
    }

    @Test
    void testRemoveByIndex(){
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testRemoveFromSingleElementList(){
        list.addFirst("A");
        String removed = list.removeFirst();
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void testIndexOutOfBounds(){
        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testGetFromEmptyListTrows(){
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
        assertThrows(NoSuchElementException.class, list::removeFirst);
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    void testAddNulls(){
        list.addFirst(null);
        list.addLast(null);
        assertNull(list.getFirst());
        assertNull(list.getLast());
        assertEquals(2, list.size());
    }

}
