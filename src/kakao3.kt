class Solution11 {
    val userPool = mutableMapOf<String, MutableList<List<String>>>()

    fun poolLanguage(origin: String, key: String): List<String> {
        return listOf("${origin}$key", "${origin}-")

    }

    fun poolFramework(origin: String, key: String): List<String> {
         return listOf("${origin}$key", "${origin}-")

    }

    fun poolCareer(origin: String, key: String): List<String> {
         return listOf("${origin}$key", "${origin}-")

    }

    fun poolFood(origin: String, key: String): List<String> {
           return listOf("${origin}$key", "${origin}-")

    }

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val infoSplitted = info.map { it.split(" ") }

        infoSplitted.forEach { info ->
            poolLanguage("", info[0]).forEach { a ->
                poolFramework(a, info[1]).forEach { b ->
                    poolCareer(b, info[2]).forEach { c ->
                        poolFood(c, info[3]).forEach { d ->
                            userPool.getOrPut(d) { mutableListOf() }.add(info)
                        }
                    }
                }
            }
        }

        val qSplitted = query.map { it.split(" ").filterNot { it == "and" } }
        return qSplitted.map { qList ->
            var count = 0
//            poolLanguage("", qList[0]).forEach { a ->
//                poolFramework(a, qList[1]).forEach { b ->
//                    poolCareer(b, qList[2]).forEach { c ->
//                        poolFood(c, qList[3]).forEach { d ->
////                            println("query $d")
//                            val users = userPool.get("${qList[0]}${qList[1]}${qList[2]}${qList[3]}")
//                            if (users.isNullOrEmpty()) {
////                                println("query no user")
//                                count += 0
//                            } else {
//                               val users2 = users.filter {
//                                    it[4].toInt() >= qList[4].toInt()
//                                }
////                                println("query users ${users2.size}")
//                                count += users2.size
//                            }
//                        }
//                    }
//                }
//            }
            val users = userPool.get("${qList[0]}${qList[1]}${qList[2]}${qList[3]}")
            if (users.isNullOrEmpty()) {
//                                println("query no user")
                count += 0
            } else {
                val users2 = users.filter {
                    it[4].toInt() >= qList[4].toInt()
                }
//                                println("query users ${users2.size}")
                count += users2.size
            }
            count
//            infoSplitted.filter { info ->
//
//                if (qList[0] != "-" && qList[0] != info[0]) {
//                    return@filter false
//                }
//                if ((qList[1] != "-" && qList[1] != info[1])) {
//                    return@filter false
//                }
//                if ((qList[2] != "-" && qList[2] != info[2])) {
//                    return@filter false
//                }
//                if ((qList[3] != "-" && qList[3] != info[3])) {
//                    return@filter false
//                }
//                if ((qList[4] != "-" && qList[4].toInt() > info[4].toInt())) {
//                    return@filter false
//                }
//                return@filter true
//            }.size
        }.toIntArray()


//        val infoSplitted = info.map { it.split(" ") }
//        val qSplitted = query.map { it.split(" ").filterNot { it == "and" } }
//        return qSplitted.map { qList ->
//            infoSplitted.filter { info ->
//
//                if (qList[0] != "-" && qList[0] != info[0]) {
//                    return@filter false
//                }
//                if ((qList[1] != "-" && qList[1] != info[1])) {
//                    return@filter false
//                }
//                if ((qList[2] != "-" && qList[2] != info[2])) {
//                    return@filter false
//                }
//                if ((qList[3] != "-" && qList[3] != info[3])) {
//                    return@filter false
//                }
//                if ((qList[4] != "-" && qList[4].toInt() > info[4].toInt())) {
//                    return@filter false
//                }
//                return@filter true
//            }.size
//        }.toIntArray()

//        val infoSplitted = info.map { it.split(" ") }.map {
//            var bit = 0
//            bit = when (it[0]) {
//                "cpp" -> bit xor 1
//                "java" -> bit xor 2
//                "python" -> bit xor 3
//                else -> bit xor 7
//            }
//            bit = bit shl 3
//
//            bit = when (it[1]) {
//                "backend" -> bit xor 1
//                "frontend" -> bit xor 2
//                else -> bit xor 7
//            }
//            bit = bit shl 3
//
//            bit = when (it[2]) {
//                "junior" -> bit xor 1
//                "senior" -> bit xor 2
//                else -> bit xor 7
//            }
//
//            bit = bit shl 3
//
//            bit = when (it[3]) {
//                "chicken" -> bit xor 1
//                "pizza" -> bit xor 2
//                else -> bit xor 7
//            }
//
//            bit to it.last()
//        }
//
//        val qSplitted = query.map { it.split(" ").filterNot { it == "and" } }.map {
//            var bit = 0
//            bit = when (it[0]) {
//                "cpp" -> bit xor 6
//                "java" -> bit xor 5
//                "python" -> bit xor 4
//                else -> bit xor 7
//            }
//            bit = bit shl 3
//
//            bit = when (it[1]) {
//                "backend" -> bit xor 6
//                "frontend" -> bit xor 5
//                else -> bit xor 7
//            }
//            bit = bit shl 3
//
//            bit = when (it[2]) {
//                "junior" -> bit xor 6
//                "senior" -> bit xor 5
//                else -> bit xor 7
//            }
//            bit = bit shl 3
//
//            bit = when (it[3]) {
//                "chicken" -> bit xor 6
//                "pizza" -> bit xor 5
//                else -> bit xor 7
//            }
//
//            bit to it.last()
//        }
//        return qSplitted.map { qList ->
//            infoSplitted.filter { info ->
////                println("${info.first.toString(2)}\n${qList.first.toString(2)}\n${(info.first.or(qList.first)).toString(2)}\n")
//                if (info.first.or(qList.first) != 4095) {
//                    return@filter false
//                }
//                if (info.second.toInt() < qList.second.toInt()) {
//                    return@filter false
//                }
//                return@filter true
//            }.size
//        }.toIntArray()

    }
}