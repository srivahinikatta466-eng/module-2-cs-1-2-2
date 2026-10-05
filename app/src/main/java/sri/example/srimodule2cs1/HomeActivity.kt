package sri.example.srimodule2cs1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sri.example.srimodule2cs1.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "user@example.com"
        val isFromRegistration = intent.getBooleanExtra("FROM_REGISTRATION", false)

        val welcomeMessage = if (isFromRegistration) {
            "Welcome, $userName!"
        } else {
            "Welcome back, $userName!"
        }

        binding.welcomeText.text = welcomeMessage
        binding.userEmail.text = "Email: $userEmail"

        val currentTime = SimpleDateFormat("hh:mm a, dd MMM yyyy", Locale.getDefault()).format(Date())
        binding.loginTime.text = "Login Time: $currentTime"

        val message = if (isFromRegistration) {
            "Registration successful! Welcome to the app!"
        } else {
            "Login successful! Welcome back!"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        setupLogoutButton()
    }

    private fun setupLogoutButton() {
        binding.logoutButton.setOnClickListener {
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Please use logout button to exit", Toast.LENGTH_SHORT).show()
    }
}