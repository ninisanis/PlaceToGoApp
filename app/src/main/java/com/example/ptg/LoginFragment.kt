package com.example.ptg

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

class LoginFragment : Fragment() {
    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var database: DatabaseReference? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etUsername = view.findViewById(R.id.et_name)
        etPassword = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)
        database = FirebaseDatabase.getInstance().getReference("users")

        btnLogin!!.setOnClickListener {
            val username = etUsername!!.text.toString()
            val password = etPassword!!.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Username dan Password harus diisi")
            } else {
                checkUserLogin(username, password)
            }
        }

        return view
    }

    private fun checkUserLogin(username: String, password: String) {
        val userRef = database!!.child(username)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userData = dataSnapshot.value as? Map<*, *>
                val storedPassword = userData?.get("password") as? String

                if (storedPassword == password) {
                    showToast("Login berhasil")
                    // Lakukan navigasi ke HomeFragment setelah login berhasil
                    val homeFragment = HomeFragment()
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, homeFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else {
                    showToast("Username atau Password salah")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                showToast("Terjadi kesalahan: ${databaseError.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}


