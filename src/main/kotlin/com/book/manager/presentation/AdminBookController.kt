package com.book.manager.presentation

import com.book.manager.application.AdminBookService
import com.book.manager.domain.Book
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class AdminBookController(
    private val service: AdminBookService
) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterBookRequest) {
        service.register(
            Book(
                id = request.id,
                title = request.title,
                author = request.author,
                releaseDate = request.releaseDate
            )
        )
    }

    @PostMapping("/update")
    fun update(@RequestBody request: UpdateBookRequest) {
        service.update(request.id, request.title, request.author, request.releaseDate)
    }

    @PostMapping("/delete/{book_id}")
    fun delete(@PathVariable("book_id") bookId: Long) {
        service.delete(bookId)
    }
}