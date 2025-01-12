package com.example.libraryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.libraryapp.adapter.BookAdapter
import com.example.libraryapp.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity(), BookAdapter.OnBookClickListener {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set fragment awal (Homepage)
        loadFragment(HomepageFragment())

        // Handle bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomepageFragment()
                R.id.nav_collection -> CollectionFragment()
                R.id.nav_social -> SocialFragment()
                R.id.nav_account -> AccountFragment()
                else -> null
            }
            fragment?.let { loadFragment(it) }
            true
        }
    }

    // Fungsi untuk mengganti fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    override fun onBookClick(bookId: Int) {
        val bundle = Bundle()
        bundle.putInt("book_id", bookId)

        val detailBookFragment = DetailBookFragment()
        detailBookFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailBookFragment)
            .addToBackStack(null)
            .commit()
    }
}