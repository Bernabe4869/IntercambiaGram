package kt.evaprojects.cum.unex.es.intercambiagram

import java.util.*


//Para cuando reciba usuarios
class Usuario(var correo: String, var nombre:String, var uid: String, var fotos: List<String>?, var seguidores: List<String>?, var seguidos: List<String>?) {

    override fun toString(): String {
        return "Usuario(correo='$correo', nombre='$nombre', uid='$uid', fotos=$fotos, seguidores=$seguidores, seguidos=$seguidos)"
    }
}