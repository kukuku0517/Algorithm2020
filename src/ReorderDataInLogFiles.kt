import java.lang.Exception
import kotlin.math.log
import kotlin.math.min

class Solution8 {
    companion object {
        const val DIG = "dig"
        const val LET = "let"
    }

    data class Log(
        val id: String,
        val isDig: Boolean,
        val logs: Array<String>,
        val str: String
    )

    fun reorderLogFiles(logs: Array<String>): Array<String> {
        return logs.map {
            val log = it.split(" ")
            val isDig = try {
                log[1].toDouble()
                true
            } catch (e: Exception) {
                false
            }
            Log(
                log[0], isDig, log.subList(1, log.size).toTypedArray(), it
            )
        }
            .sortedWith(Comparator { o1, o2 ->

                if (o1.isDig != o2.isDig) {
                    return@Comparator o1.isDig.compareTo(o2.isDig)
                } else if (o1.isDig) {
                    return@Comparator 0
                }

                val letCompare = o1.logs.reduce { acc, s -> "$acc $s" }
                    .compareTo(o2.logs.reduce { acc, s -> "$acc $s" })

                if (letCompare == 0) {
                    o1.id.compareTo(o2.id)
                } else {
                    letCompare
                }
            }).map { it.str }.toTypedArray()
    }
}