package com.ssafy.finalproject.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseActivity
import com.ssafy.finalproject.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnv.setupWithNavController(navController)

        hideBottomNavigationView(navController)
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.visibility = when (destination.id) {
                R.id.loginFragment -> View.GONE
                R.id.homeFragment -> View.VISIBLE
                R.id.categoryFragment -> View.VISIBLE
                R.id.myPageFragment -> View.VISIBLE
                else -> View.VISIBLE
            }
        }
    }
}