package com.example.libraryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.libraryapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(), LifecycleObserver {
    private lateinit var binding: ActivityDashboardBinding
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        // Panggil DatabaseSeeder untuk melakukan inisialisasi data
        lifecycleScope.launch {
            DatabaseSeeder.seed(
                this@DashboardActivity,
                database.bookDao(),
                database.collectionDao(),
                database.socialPostDao(),
                database.userProfileDao()
            )
        }

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
}