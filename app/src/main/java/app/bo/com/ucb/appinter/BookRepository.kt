package app.bo.com.ucb.appinter

import app.bo.com.ucb.appinter.modeldb.Book

class BookRepository(private val bookDao: IBookDao) {

    suspend fun insert(book: Book) {
        bookDao.insert(book)
    }
    fun getListBook(): List<Book> {
        return bookDao.getList()
    }
}