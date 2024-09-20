// APARTADO 6
// fun main() {
//     val firstNumber = 10
//     val secondNumber = 5

//     println("$firstNumber + $secondNumber = ${firstNumber+secondNumber}")
// }


// APARTAADO 7
// fun main() {
    
//     val firstNumber = 10
//     val secondNumber = 5
//     val thirdNumber = 8   

//     println("$firstNumber + $secondNumber = ${add(firstNumber, secondNumber)}")
//     println("$firstNumber + $thirdNumber = ${add(firstNumber, thirdNumber)}")
       
//     println("$firstNumber + $secondNumber = ${substract(firstNumber, secondNumber)}")
//     println("$firstNumber + $thirdNumber = ${substract(firstNumber, thirdNumber)}")
// }


// // Define add() function below this line

// fun add(first: Int, second: Int)= first + second
// fun substract(first: Int, second: Int)= first - second


fun main() {
    val operatingSystem = "Debian"
    val emailId = "realemail@gmail.com"

    println(displayAlertMessage(operatingSystem, emailId))
}

fun displayAlertMessage(operatingSystem: String, emailId: String)= "There's a new sign-in request on $operatingSystem for your Google Account $emailId"

// Define your displayAlertMessage() below this line.    


fun main() {
    val firstUserEmailId = "user_one@gmail.com"

    // The following line of code assumes that you named your parameter as emailId.
    // If you named it differently, feel free to update the name.
    println(displayAlertMessage(emailId = firstUserEmailId))
    println()

    val secondUserOperatingSystem = "Windows"
    val secondUserEmailId = "user_two@gmail.com"

    println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))
    println()

    val thirdUserOperatingSystem = "Mac OS"
    val thirdUserEmailId = "user_three@gmail.com"

    println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
    println()
}

fun displayAlertMessage(operatingSystem = "Unknown", emailId: String)= "There's a new sign-in request on $operatingSystem for your Google Account $emailId"

// Define your displayAlertMessage() below this line.

