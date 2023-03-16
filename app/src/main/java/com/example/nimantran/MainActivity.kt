package com.example.nimantran

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nimantran.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val prefs by lazy { getSharedPreferences("prefs", 0) }
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        auth = FirebaseAuth.getInstance()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val bottomNav: BottomNavigationView = binding.appBarMain.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.appBarMain.appBarLogoimage.setOnClickListener {
            //open drawer
            drawerLayout.open()
        }
        // Passing each menu ID as a set of Ids because each
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_logout,
                R.id.homeFragment,
                R.id.myGuestListFragment,
                R.id.myGiftsFragment,
                R.id.myProfileFragment,
                R.id.templateDesignsFragment
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //hide action bar
        supportActionBar?.hide()
        bottomNav.setupWithNavController(navController)
        val userType = prefs.getString("userType", "user")

        // add listener to navigation drawer
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_logout -> {
                    Log.d("logout", "logout")
                    auth.signOut()
                    prefs.edit().putString("userType", "").apply()
                    startActivity(Intent(this, AuthenticationActivity::class.java))
                    finish()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}