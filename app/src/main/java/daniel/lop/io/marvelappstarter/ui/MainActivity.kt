package daniel.lop.io.marvelappstarter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import daniel.lop.io.marvelappstarter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // public key: 89a6951dda6eb99a004e7ae36cefc027
    // private key: 291adc521210e383d6fe3e685db2578aed414f4d

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}