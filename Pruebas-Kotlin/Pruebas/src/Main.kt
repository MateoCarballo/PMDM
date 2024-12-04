fun main() {
    //tiposDeDatos()
    //println(esMayorQue(numero1 = 100, numero2 = 20))
    funciones()
}

fun funciones() {

    fun saludoNombre(nombre: String, saludo: String = "Hola") {
        println("$saludo $nombre")
    }

    saludoNombre("Ruben","Hola como estás?")
    saludoNombre("Mateo")
    saludoNombre(saludo="Saludando a DAvid",nombre="saludando a Mateo")
}

fun tiposDeDatos(){
    val enteroInmutable = 10
    val cadenaInmutable = "Esta cadena NO puede ser modificada"
    val floatInmutable = 10.11

    var enteroMutable = 20
    var cadenaMutable = "Esta cadena puede ser modificada"
    var floatMutable = 20.22

    println("Esto es un entero que  NO se puede modificar $enteroInmutable")
    println("Esto es una cadena que NO se puede modificar $cadenaInmutable")
    println("Esto es un float que   NO se puede modificar $floatInmutable")

    var enteroNulable: Int? = null
    var cadenaNulable: String? = null
    var floatNulable: Float? = null

    println("Esto es un entero que se puede modificar y es nulable $enteroNulable")
    println("Esto es una cadena que se puede modificar y es nulable $cadenaNulable")
    println("Esto es un float que se puede modificar y es nulable $floatNulable")

    enteroNulable = 33
    cadenaNulable = "Cadena nulable"
    floatNulable = 33.33f

    println("Esto es un entero que se puede modificar y es nulable $enteroNulable")
    println("Esto es una cadena que se puede modificar y es nulable $cadenaNulable")
    println("Esto es un float que se puede modificar y es nulable $floatNulable")
}

fun esMayorQue(numero1: Int, numero2: Int) = if (numero1 > numero2) "El numero mayor es $numero1" else  "El numero mayor es $numero2"




