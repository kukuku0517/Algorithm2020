class Solution1 {
    fun solution(new_id: String): String {
        var answer: String = new_id
        val a1 = answer.toLowerCase()
//        println(a1)
        var a2 = ""
        a1.forEach {
            if (it.isValidLetter()) {
                a2 += it
            }
        }
//        println(a2)
        val a3 = a2.replace(Regex("[.]+"), ".")
//        println(a3)
        val a4 = a3.let {
            if (it.firstOrNull() == '.')  it.substring(1) else it
        }.let {
            if (it.lastOrNull() == '.')  it.substring(0, it.length - 1) else it
        }
//        println(a4)

        val a5 = a4.ifEmpty { "a" }
        val a6 = (if (a5.length>=16) a5.substring(0,15) else a5).let {
            if (it.last() == '.')  it.substring(0, it.length - 1) else it
        }

        val a7 = if (a6.length<=2) a6.padEnd(3, a6.last()) else a6

        return a7
    }

    fun Char.isValidLetter(): Boolean {
        return this == '-'
                || this == '_'
                || this == '.'
                || this.isLetterOrDigit()
    }
}