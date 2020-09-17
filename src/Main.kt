class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = Solution().trap(
                intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
            )
            println(result.toString())
        }
    }
}
