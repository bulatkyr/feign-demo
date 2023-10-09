package com.kbulat;

import com.kbulat.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

  private final BookService service = new BookService();

  @Test
  public void findAllTest() {
    assertEquals(4, service.findAll().size());
  }

  @Test
  public void findByIdTest() {
    Book book = service.findById(1L);
    assertEquals("UIUOLYD8XXX", book.getIsbn());
  }

  @Test
  public void createDeleteTest() {
    Book book = new Book();
    book.setId(5L);
    book.setAuthor("Ondrej Chuba");
    book.setName("Buba");
    book.setIsbn("UIUOLYD7XXX");

    service.create(book);

    Book persisted = service.findById(5L);
    assertEquals("UIUOLYD7XXX", persisted.getIsbn());

    service.deleteById(5L);

    assertEquals(4, service.findAll().size());
  }

  @Test
  public void updateTest() {
    Book book = service.findById(4L);
    String originalName = book.getName();
    book.setName("Kyrylo on the trip");

    service.update(book);

    book = service.findById(4L);
    assertEquals("Kyrylo on the trip", book.getName());

    book.setName(originalName);

    service.update(book);
  }
}
