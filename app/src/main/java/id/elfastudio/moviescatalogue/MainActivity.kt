package id.elfastudio.moviescatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.elfastudio.moviescatalogue.databinding.ActivityMainBinding
import id.elfastudio.moviescatalogue.util.hide
import id.elfastudio.moviescatalogue.util.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController by lazy {
            (supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment_activity_main
            ) as DynamicNavHostFragment).navController
        }

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.detailFilmActivity){
                navView.hide()
            }else {
                navView.show()
            }
        }
    }
}