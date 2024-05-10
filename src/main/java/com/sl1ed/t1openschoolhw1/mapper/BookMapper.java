package com.sl1ed.t1openschoolhw1.mapper;

import com.sl1ed.t1openschoolhw1.dto.BookDto;
import com.sl1ed.t1openschoolhw1.model.Book;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

  BookDto convertToDto(Book book);

  Book convertToEntity(BookDto bookDto);

  List<BookDto> convertToDtoList(List<Book> books);

}
