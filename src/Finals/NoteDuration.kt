package Finals

fun listOfNoteDurations(): List<NoteDuration> {
    val list = arrayListOf (1, 2, 3, 4, 7, 8, 15, 16, 31, 32)
    val durations = mutableListOf<NoteDuration>()

    for (number in list) {
        durations.add(NoteDuration(number))
    }

    return durations
}

data class NoteDuration(
        val duration: Int,
        val lilypondDuration: String = when (duration) {
            1 -> "1"
            2 -> "2"
            3 -> "2."
            4 -> "4"
            7 -> "4."
            8 -> "8"
            15 -> "8."
            16 -> "16"
            31 -> "16."
            32 -> "32"
            else -> ""
        })

