package com.book.manager.application

import com.book.manager.domain.BookRepository
import com.book.manager.domain.BookWithRental
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Long): BookWithRental {
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しないBookIdです: $bookId")
    }
}