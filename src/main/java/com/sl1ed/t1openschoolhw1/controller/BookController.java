package com.sl1ed.t1openschoolhw1.controller;

import com.sl1ed.t1openschoolhw1.annotation.TrackAsyncTime;
import com.sl1ed.t1openschoolhw1.annotation.TrackTime;
import com.sl1ed.t1openschoolhw1.dto.BookDto;
import com.sl1ed.t1openschoolhw1.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(name = "Book API", description = "API for books")
public class BookController {

  private final BookService bookService;

  @PostMapping
  @Operation(summary = "Save a new book", description = "Save a new book", operationId = "saveNewBook")
  @ApiResponses({@ApiResponse(responseCode = "201", description = "Successful save")})
  @TrackTime
  public ResponseEntity<Long> saveNewBook(@RequestBody BookDto bookDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get book by id", description = "Get a book by id.", operationId = "getBookById")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "Successful operation"),})
  @TrackTime
  public ResponseEntity<BookDto> getBookById(
      @Parameter(description = "Id of the book", required = true) @PathVariable Long id) {
    return ResponseEntity.ok().body(bookService.getById(id));
  }

  @GetMapping
  @Operation(summary = "Get all books", description = "Get list all books", operationId = "getAllBooks")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "Successful operation"),})
  @TrackAsyncTime
  public CompletableFuture<ResponseEntity<List<BookDto>>> getAllBooks() {
    return CompletableFuture.supplyAsync(() -> bookService.getAll())
        .thenApply(dtos ->
        {
          return ResponseEntity.ok().body(dtos);
        });
  }

}
