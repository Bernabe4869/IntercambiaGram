package kt.evaprojects.cum.unex.es.intercambiagram


import android.support.v7.app.AppCompatActivity

import android.os.Bundle

import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        cancelar.setOnClickListener {
            finish()
        }
        //Utiliza una función de Firebase, y comprueba con correo y pass si esa cuenta existe, en caso de existir, lleva al activity principal
        login.setOnClickListener {
            val email = insertarCorreo.text.toString()
            val pass = insertarPass.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    startActivity(Intent(this, activityPrincipal::class.java))
                    finish()
                }

                .addOnFailureListener {
                    Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                }
        }
    }

}
