package sri.example.srimodule2cs1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import sri.example.srimodule2cs1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabLayout()
        setupValidation()
        setupButtonClicks()
    }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Login tab
                        binding.loginCard.visibility = android.view.View.VISIBLE
                        binding.registerCard.visibility = android.view.View.GONE
                    }
                    1 -> { // Register tab
                        binding.loginCard.visibility = android.view.View.GONE
                        binding.registerCard.visibility = android.view.View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupValidation() {
        // Login Email Validation
        binding.loginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateLoginEmail()
            }
        })

        // Login Password Validation
        binding.loginPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateLoginPassword()
            }
        })

        // Register Name Validation
        binding.registerName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateRegisterName()
            }
        })

        // Register Email Validation
        binding.registerEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateRegisterEmail()
            }
        })

        // Register Password Validation
        binding.registerPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateRegisterPassword()
                validatePasswordMatch()
            }
        })

        // Register Confirm Password Validation
        binding.registerConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePasswordMatch()
            }
        })
    }

    private fun setupButtonClicks() {
        // Login Button Click
        binding.loginButton.setOnClickListener {
            if (validateLoginForm()) {
                val email = binding.loginEmail.text.toString()
                
                // Navigate to HomeActivity
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("USER_NAME", email.split("@")[0]) // Extract name from email
                intent.putExtra("USER_EMAIL", email)
                intent.putExtra("FROM_REGISTRATION", false)
                startActivity(intent)
                finish() 
            }
        }

        // Register Button Click
        binding.registerButton.setOnClickListener {
            if (validateRegisterForm()) {
                if (!binding.termsCheckbox.isChecked) {
                    Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val name = binding.registerName.text.toString()
                val email = binding.registerEmail.text.toString()

                // Navigate to HomeActivity
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("USER_NAME", name)
                intent.putExtra("USER_EMAIL", email)
                intent.putExtra("FROM_REGISTRATION", true)
                startActivity(intent)
                finish()
            }
        }

        // Forgot Password Click
        binding.forgotPassword.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Password reset link sent to $email", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Please enter a valid email first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validation Functions
    private fun validateLoginEmail(): Boolean {
        val email = binding.loginEmail.text.toString()
        return if (email.isEmpty()) {
            binding.loginEmail.error = "Email is required"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.loginEmail.error = "Enter a valid email address"
            false
        } else {
            binding.loginEmail.error = null
            true
        }
    }

    private fun validateLoginPassword(): Boolean {
        val password = binding.loginPassword.text.toString()
        return if (password.isEmpty()) {
            binding.loginPasswordLayout.error = "Password is required"
            false
        } else if (password.length < 6) {
            binding.loginPasswordLayout.error = "Password must be at least 6 characters"
            false
        } else {
            binding.loginPasswordLayout.error = null
            true
        }
    }

    private fun validateRegisterName(): Boolean {
        val name = binding.registerName.text.toString()
        return if (name.isEmpty()) {
            binding.registerName.error = "Full name is required"
            false
        } else if (name.length < 3) {
            binding.registerName.error = "Name must be at least 3 characters"
            false
        } else {
            binding.registerName.error = null
            true
        }
    }

    private fun validateRegisterEmail(): Boolean {
        val email = binding.registerEmail.text.toString()
        return if (email.isEmpty()) {
            binding.registerEmail.error = "Email is required"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.registerEmail.error = "Enter a valid email address"
            false
        } else {
            binding.registerEmail.error = null
            true
        }
    }

    private fun validateRegisterPassword(): Boolean {
        val password = binding.registerPassword.text.toString()
        return if (password.isEmpty()) {
            binding.registerPasswordLayout.error = "Password is required"
            false
        } else if (password.length < 6) {
            binding.registerPasswordLayout.error = "Password must be at least 6 characters"
            false
        } else {
            binding.registerPasswordLayout.error = null
            true
        }
    }

    private fun validatePasswordMatch(): Boolean {
        val password = binding.registerPassword.text.toString()
        val confirmPassword = binding.registerConfirmPassword.text.toString()

        return if (confirmPassword.isEmpty()) {
            binding.registerConfirmPasswordLayout.error = "Please confirm your password"
            false
        } else if (password != confirmPassword) {
            binding.registerConfirmPasswordLayout.error = "Passwords do not match"
            false
        } else {
            binding.registerConfirmPasswordLayout.error = null
            true
        }
    }

    private fun validateLoginForm(): Boolean {
        val isEmailValid = validateLoginEmail()
        val isPasswordValid = validateLoginPassword()

        return isEmailValid && isPasswordValid
    }

    private fun validateRegisterForm(): Boolean {
        val isNameValid = validateRegisterName()
        val isEmailValid = validateRegisterEmail()
        val isPasswordValid = validateRegisterPassword()
        val isPasswordMatchValid = validatePasswordMatch()

        return isNameValid && isEmailValid && isPasswordValid && isPasswordMatchValid
    }
}