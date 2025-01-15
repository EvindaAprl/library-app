package com.example.libraryapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.libraryapp.databinding.ActivityReadBookBinding
import com.example.libraryapp.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReadBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBookBinding
    private var currentPage = 1
    private var totalPages = 0
    private var bookId: Int? = null
    private var bookPages: List<String> = emptyList()
    lateinit var database: AppDatabase
    private lateinit var bookDao: BookDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = AppDatabase.getInstance(this)
        bookDao = database.bookDao()

        bookId = intent.getIntExtra("BOOK_ID", 0)

        setupListeners()
        loadBookData()
    }

    private fun loadBookData() {
        bookId?.let { bookId ->
            binding.progressBar.visibility = android.view.View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val book: Book? = bookDao.getBookById(bookId)
                    withContext(Dispatchers.Main) {
                        if (book != null && !book.pageContent.isNullOrEmpty()) {
                            binding.tvBookTitle.text = book.title
                            if (!book.coverPath.isNullOrEmpty()) {
                                Glide.with(this@ReadBookActivity)
                                    .load(book.coverPath)
                                    .into(binding.ivBookCover)
                            } else {
                                binding.ivBookCover.setImageResource(R.drawable.ic_book)
                            }
                            bookPages = book.pageContent.split("===")
                            totalPages = bookPages.size
                            updatePage()
                        } else {
                            binding.tvPageContent.text = getString(R.string.book_content_not_found)
                            binding.tvPageNumber.text = "0 dari 0"
                            Toast.makeText(
                                this@ReadBookActivity,
                                getString(R.string.book_content_not_found),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        binding.progressBar.visibility = android.view.View.GONE
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        binding.progressBar.visibility = android.view.View.GONE
                        Toast.makeText(
                            this@ReadBookActivity,
                            "Error loading book data: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("ReadBookActivity", "Error loading book data", e)
                    }
                }
            }
        } ?: run {
            Toast.makeText(this, "Invalid Book ID", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPreviousPage.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                updatePage()
            }
        }

        binding.btnNextPage.setOnClickListener {
            if (currentPage < totalPages) {
                currentPage++
                updatePage()
            }
        }
    }

    private fun updatePage() {
        if (bookPages.isNotEmpty()) {
            binding.tvPageContent.text = bookPages[currentPage - 1]
            binding.tvPageNumber.text = getString(R.string.page_number, currentPage, totalPages)
        } else {
            binding.tvPageContent.text = getString(R.string.book_content_not_available)
            binding.tvPageNumber.text = "0 dari 0"
        }
    }
}