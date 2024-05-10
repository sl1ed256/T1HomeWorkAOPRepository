package com.sl1ed.t1openschoolhw1.service;

import com.sl1ed.t1openschoolhw1.dto.BookDto;
import com.sl1ed.t1openschoolhw1.model.Book;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookService {

  Long save(BookDto bookDto);

  BookDto getById(Long id);

  List<BookDto> getAll();

}
