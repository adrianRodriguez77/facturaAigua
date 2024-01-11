fun quotaFixa(): Int{
    val quoataFixaMensual = 6
    return quoataFixaMensual
}
fun quotaConsumAigua(consumAigua: Int): Double {
    val quotaVariable: Double = when(consumAigua){
        in 0..50 -> {
            quotaFixa().toDouble()
        }
        in 51..200 -> {
            (consumAigua*0.15)+quotaFixa()
        }
        else -> {
            (consumAigua*0.30)+quotaFixa()
        }
    }
    return quotaVariable
}
fun descompteConsumAigua(consumAigua: Int, tipusDescompte: String, membresFamilia: Int): Double{
    var quotaAmbDescompte = 0.0
    when (tipusDescompte){
        "FN", "FM" -> {
            val totalDescompte = 10.0*membresFamilia
            if (totalDescompte>50){
                println("Has superat el màxim del descompte")
                quotaAmbDescompte = quotaConsumAigua(consumAigua)
            } else {
                println("Tens un total de $totalDescompte% de descompte")
                quotaAmbDescompte = quotaConsumAigua(consumAigua)*totalDescompte/100.0
            }
        }
        "BS" -> {
            println("La quota fixa s'ha reduït 3€")
            println("Tens un 80% de descompte en el consum.")
            quotaAmbDescompte = (quotaConsumAigua(consumAigua)-3)*(80/100.0)
        }
        else -> println("No tens descomptes.")
    }
    return quotaAmbDescompte
}
fun factura(consumAigua: Int, tipusDescompte: String, membresFamilia: Int){
    println("""
        
        _________________FACTURA_________________
        Consum d'aigua en litres:           $consumAigua L
        Tipus de descompte:                   $tipusDescompte
        
        Preu total:                         ${descompteConsumAigua(consumAigua, tipusDescompte, membresFamilia)}€
        _________________________________________
    """.trimIndent())
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)

    println("Benvingut al càlcul de consum d'aigua!")

    var consumAigua = 0
    var membresFamilia = 0

    do {
        println("\nQuants litres d'aigua has consumit aquest mes?: ")
        if (scanner.hasNextInt()){
            consumAigua = scanner.nextInt()
            quotaConsumAigua(consumAigua)
        } else println("Aquesta dada no és correcta")
        scanner.nextLine()
    }while (consumAigua < 0)

    println("Tens descompte? Família monomarental ('FM'), nombrosa ('FN') o bo social ('BS') Indica quin: ")
    val tipusDescompte = scanner.next()
    if (tipusDescompte == "FN" || tipusDescompte == "FM"){
        println("Quants membres sou a la família?: ")
        membresFamilia = scanner.nextInt()
    }
    factura(consumAigua, tipusDescompte, membresFamilia)
}