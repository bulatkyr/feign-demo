package com.kbulat;

import com.kbulat.api.BookClient;
import com.kbulat.model.Book;
import com.kbulat.model.PageRequest;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.List;
import java.util.Map;

public class BookService {

  public static final String BOOKS_API_URL = "https://64c753bb0a25021fde924b73.mockapi.io/books";
  private final BookClient bookClient;

  public BookService() {
    bookClient =
        Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(BookClient.class))
            .logLevel(Logger.Level.FULL)
            .target(BookClient.class, BOOKS_API_URL);
  }

  public List<Book> findAll(String sort, int pageNumber, int count) {
    return bookClient.findAll(sort, pageNumber, count);
  }

  public List<Book> findAllQueryMap(String sort, int pageNumber, int count) {
    Map<String, Object> queryMap = Map.of("sort", sort, "pageNumber", pageNumber, "count", count);
    return bookClient.findAll(queryMap);
  }

  public List<Book> findAllPageRequest(String sort, int pageNumber, int pageSize) {
    return bookClient.findAll(new PageRequest(sort, pageNumber, pageSize));
  }

  public Book findById(Long id) {
    return bookClient.findById(id);
  }

  public void create(Book book) {
    bookClient.create(book);
  }

  public void update(Book book) {
    bookClient.update(book.getId(), book);
  }

  public void deleteById(Long id) {
    bookClient.deleteById(id);
  }
}
