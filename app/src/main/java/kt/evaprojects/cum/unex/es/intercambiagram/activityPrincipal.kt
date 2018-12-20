package kt.evaprojects.cum.unex.es.intercambiagram

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_principal.*

class activityPrincipal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FragmentHome.OnFragmentInteractionListener, fragment_foto.OnFragmentInteractionListener
,fragmet_yo.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {

    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val manager = supportFragmentManager


    //Botones de navegación inferiores
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentoHome()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentoFoto()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentoYo()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //Comprobamos si hay usuario, si no, se le manda a la pantalla en la que tiene que registrarse o hacer login
        if(FirebaseAuth.getInstance().currentUser == null){
            startActivity(Intent(this, MainActivity::class.java))
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //Establecemos por defecto el fragmento de Home como principal
        fragmentoHome()
    }

    //Este es el fragmento Home, se encarga de que salga las fotos de los usuarios
    fun fragmentoHome(){
        val fragmentohome = FragmentHome.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contenedorfragment, fragmentohome)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        Toast.makeText(this, "asdad", Toast.LENGTH_SHORT).show()
    }

    //Este es el fragmento foto, se encarga de que subir fotos o hacerlas
    fun fragmentoFoto(){
        val fragmentofoto = fragment_foto.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contenedorfragment, fragmentofoto)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        Toast.makeText(this, "asdad", Toast.LENGTH_SHORT).show()

    }

    //Tu espacio personal
    fun fragmentoYo(){
        val fragmentohome = fragmet_yo.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contenedorfragment, fragmentohome)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        Toast.makeText(this, "asdad", Toast.LENGTH_SHORT).show()

    }
}
