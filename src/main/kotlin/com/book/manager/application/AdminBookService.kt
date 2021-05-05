package com.book.manager.application

import com.book.manager.domain.Book
import com.book.manager.domain.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.time.LocalDate

@Service
class AdminBookService(
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let {
            throw IllegalArgumentException("すでに存在する書籍IDです:${book.id}")
        }
        bookRepository.register(book)
    }

    @Transactional
    fun update(bookId: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しない書籍ID: $bookId")
        bookRepository.update(bookId, title, author, releaseDate)
    }

    @Transactional
    fun delete(bookId: Long) {
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("存在しない書籍ID: $bookId")
        bookRepository.delete(bookId)
    }
}