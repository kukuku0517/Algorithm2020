class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            val result = Solution11().solution(
//                arrayOf(
//                    "java backend junior pizza 150",
//                    "python frontend senior chicken 210",
//                    "python frontend senior chicken 150",
//                    "cpp backend senior pizza 260",
//                    "java backend junior chicken 80",
//                    "python backend senior chicken 50"),
//                arrayOf(
//                    "java and backend and junior and pizza 100",
//                    "python and frontend and senior and chicken 200",
//                    "cpp and - and senior and pizza 250",
//                    "- and backend and senior and - 150",
//                    "- and - and - and chicken 100",
//                    "- and - and - and - 150"
//                )
//            )
//            print(result.toList())

//            val result = Solution().solution(6,4,6,2, arrayOf(
//                    intArrayOf(4, 1, 10),
//                    intArrayOf(3, 5, 24),
//                    intArrayOf(5, 6, 2),
//                    intArrayOf(3, 1, 41),
//                    intArrayOf(5, 1, 24),
//                    intArrayOf(4, 6, 50),
//                    intArrayOf(2, 4, 66),
//                    intArrayOf(2, 3, 22),
//                    intArrayOf(1, 6, 25)
//            ))
//            println(result.toString())

//            val result2 = Solution().solution(7,3,4,1, arrayOf(
//                intArrayOf(5, 7, 9), intArrayOf(4, 6, 4), intArrayOf(3, 6, 1), intArrayOf(3, 2, 3), intArrayOf(2, 1, 6)
//            ))
//            println(result2.toString())

            val result3 = Solution().solution(6,4,5,6, arrayOf(
                intArrayOf(2,6,6), intArrayOf(6,3,7), intArrayOf(4,6,7), intArrayOf(6,5,11), intArrayOf(2,5,12), intArrayOf(5,3,20), intArrayOf(2,4,8), intArrayOf(4,3,9)
            ))
            println(result3.toString())
        }
    }
}

//n	s	a	b	fares	result
//6	4	6	2	intArrayOf(intArrayOf(4, 1, 10), intArrayOf(3, 5, 24), intArrayOf(5, 6, 2), intArrayOf(3, 1, 41), intArrayOf(5, 1, 24), intArrayOf(4, 6, 50), intArrayOf(2, 4, 66), intArrayOf(2, 3, 22), intArrayOf(1, 6, 25))	82
//7	3	4	1	intArrayOf(intArrayOf(5, 7, 9), intArrayOf(4, 6, 4), intArrayOf(3, 6, 1), intArrayOf(3, 2, 3), intArrayOf(2, 1, 6))	14
//6	4	5	6	intArrayOf(intArrayOf(2,6,6), intArrayOf(6,3,7), intArrayOf(4,6,7), intArrayOf(6,5,11), intArrayOf(2,5,12), intArrayOf(5,3,20), intArrayOf(2,4,8), intArrayOf(4,3,9))	18