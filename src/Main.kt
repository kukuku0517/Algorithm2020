class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = Solution().solution(
                "hit",
                "cog",
                arrayOf("hot", "dot", "dog", "lot", "log")
            )
            println(result)
        }
    }
}
