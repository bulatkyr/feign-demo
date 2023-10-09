package com.kbulat.api;

import com.kbulat.model.Book;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface BookClient {

  @RequestLine("GET /{id}")
  Book findById(@Param("id") Long id);

  @RequestLine("GET")
  List<Book> findAll();

  @RequestLine("POST")
  @Headers("Content-Type: application/json")
  void create(Book book);

  @RequestLine("PUT /{id}")
  @Headers("Content-Type: application/json")
  void update(@Param("id") Long id, Book book);

  @RequestLine("DELETE /{id}")
  void deleteById(@Param("id") Long id);
}
