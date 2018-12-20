package kt.evaprojects.cum.unex.es.intercambiagram

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registrarse.*

class registrarseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        cancelarRegistro.setOnClickListener {
            finish()
        }
        RegistrarseButton.setOnClickListener {
            registro()
        }
    }

    private fun registro(){
        val email = insertarCorreoRegistro.text.toString()
        val pass = insertarPassregistro.text.toString()


        //Validamos que los campos no esten vacios
        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"Algunos campos están vacios", Toast.LENGTH_SHORT).show()
            return
        }


        //Creamos el usuario en Firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                val uid = FirebaseAuth.getInstance().uid ?: ""
                val referencia = FirebaseDatabase.getInstance().getReference("/users/$uid")
                Toast.makeText(this,"Usuario Registrado con UID: ${it.result.user.uid}", Toast.LENGTH_SHORT).show()
                //Creamos ahora un usuario en la bd
                referencia.setValue(Usuario(insertarCorreoRegistro.text.toString(), insertarNombreregistro.text.toString(),uid, null, null, null))
                    .addOnSuccessListener {
                        //Una vez ha sido registrado, inicializa la actividad principal
                        val intent = Intent(this, activityPrincipal::class.java)
                        //Evita volver a la pantalla de registro al pulsar atras
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
            }


            //Como último fallo es posible que el correo no este en el formato correcto ....@.... y avisamos al usuario del fallo
            .addOnFailureListener {
                Toast.makeText(this,"El formato del email es incorrecto, ${it.message}", Toast.LENGTH_SHORT).show()
                Log.d("RegisterActivity",it.message)
            }
}

    }

