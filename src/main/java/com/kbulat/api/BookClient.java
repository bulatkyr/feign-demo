package com.kbulat.api;

import com.kbulat.model.Book;
import com.kbulat.model.PageRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

public interface BookClient {

  @RequestLine("GET /{id}")
  Book findById(@Param("id") Long id);

  @RequestLine("GET ?sort={sort}&pageNumber={pageNumber}&count={count}")
  List<Book> findAll(
      @Param("sort") String sort, @Param("pageNumber") int pageNumber, @Param("count") int count);

  @RequestLine("GET")
  List<Book> findAll(@QueryMap Map<String, Object> queryMap);

  @RequestLine("GET")
  List<Book> findAll(@QueryMap PageRequest pageRequest);

  @RequestLine("POST")
  @Headers("Content-Type: application/json")
  void create(Book book);

  @RequestLine("PUT /{id}")
  @Headers("Content-Type: application/json")
  void update(@Param("id") Long id, Book book);

  @RequestLine("DELETE /{id}")
  void deleteById(@Param("id") Long id);
}
