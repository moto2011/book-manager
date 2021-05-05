package com.book.manager.infrastructure

import com.book.manager.domain.Book
import com.book.manager.domain.BookRepository
import com.book.manager.domain.BookWithRental
import com.book.manager.domain.Rental
import com.book.manager.infrastructure.database.mapper.*
import com.book.manager.infrastructure.database.record.BookRecord
import com.book.manager.infrastructure.database.record.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
    }

    override fun delete(id: Long) {
        bookMapper.deleteByPrimaryKey(id)
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }

    private fun toRecord(model: Book): BookRecord =
        BookRecord(
            id = model.id,
            title = model.title,
            author = model.author,
            releaseDate = model.releaseDate
        )
}