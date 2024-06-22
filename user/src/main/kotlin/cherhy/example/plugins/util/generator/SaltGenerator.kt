package cherhy.example.plugins.util.generator

object SaltGenerator {
    fun generate() = StringBuilder().let {
        for (i in 0..32) {
            val number = (Math.random() * 75 + 48).toInt()
            it.append(number.toChar())
        }
        it.toString()
    }
}