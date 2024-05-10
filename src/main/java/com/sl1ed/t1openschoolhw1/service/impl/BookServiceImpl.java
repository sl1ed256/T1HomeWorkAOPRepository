package com.sl1ed.t1openschoolhw1.service.impl;

import com.sl1ed.t1openschoolhw1.dto.BookDto;
import com.sl1ed.t1openschoolhw1.exceptions.NotFoundException;
import com.sl1ed.t1openschoolhw1.mapper.BookMapper;
import com.sl1ed.t1openschoolhw1.repository.BookRepository;
import com.sl1ed.t1openschoolhw1.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final BookMapper bookMapper;

  @Override
  public Long save(BookDto bookDto) {
    return bookRepository.save(bookMapper.convertToEntity(bookDto)).getId();
  }

  @Transactional(readOnly = true)
  @Override
  public BookDto getById(Long id) {
    return bookMapper.convertToDto(bookRepository.findById(id)
        .orElseThrow(
            () -> new NotFoundException(String.format("Book with id = %d not found", id))));
  }

  @Transactional(readOnly = true)
  @Override
  public List<BookDto> getAll() {
    return bookMapper.convertToDtoList(bookRepository.findAll());
  }
}
