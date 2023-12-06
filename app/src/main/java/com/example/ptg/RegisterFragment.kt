package com.example.ptg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {
    private var etUsername: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etRePassword: EditText? = null
    private var btnRegister: Button? = null
    private var database: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        etUsername = view.findViewById(R.id.et_name)
        etEmail = view.findViewById(R.id.et_email)
        etPassword = view.findViewById(R.id.et_password)
        etRePassword = view.findViewById(R.id.et_repassword)
        btnRegister = view.findViewById(R.id.btn_register)
        database = FirebaseDatabase.getInstance().getReference("users")

        btnRegister!!.setOnClickListener {
            val username = etUsername!!.text.toString()
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()
            val rePassword = etRePassword!!.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Ada Data Yang Masih Kosong!!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != rePassword) {
                Toast.makeText(
                    requireContext(),
                    "Password tidak sesuai dengan ulang password!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val userRef = database!!.child(username)
                userRef.child("username").setValue(username)
                userRef.child("email").setValue(email)
                userRef.child("password").setValue(password)

                Toast.makeText(requireContext(), "Register Berhasil", Toast.LENGTH_SHORT).show()
                val fragment = LoginFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        return view
    }
}