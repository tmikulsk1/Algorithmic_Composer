package Utils

    fun splitDuration(): List<List<Duration>> {

        var input = mutableListOf(1, 4, 4, 1, 3, 2, 2, 0)
        var timeSignature = TimeSignature(4,4)
        val denominator = timeSignature.denominator.toFloat()
        val numerator = timeSignature.numerator.toFloat()
        var fullCompass = mutableListOf<Duration>()
        val output = mutableListOf<List<Duration>>()
        var index = 0
        var run = true

        while (run) {
            var modulus = input[index].rem(2)
            if (modulus != 0 && input[index] != 1) {
                breakDuration(input, index)
            }

            var percent: Float = (denominator / input[index]) / numerator
            var compassSize = fullCompass.map { it.percent }.sum()

            if (percent > 1 || compassSize + percent > 1f) {
                while (percent > 1 || compassSize + percent > 1f) {
                    input = breakDuration(input, index)
                    percent = (denominator / input[index]) / numerator
                }
            }

            fullCompass.add(Duration(input[index], percent))

            compassSize = fullCompass.map { it.percent }.sum()
            if (compassSize == 1f) {
                output.add(fullCompass)
                fullCompass = mutableListOf<Duration>()
            }

            index += 1
            if (index == input.size - 1) {
                output.add(fullCompass)
                run = false
            }
        }

        return output
    }

    fun breakDuration(durationList: MutableList<Int>, index: Int): MutableList<Int> {
        val modulus = durationList[index].rem(2)
        val splittedDuration = if (modulus == 0 || durationList[index] == 1) durationList[index] * 2 else durationList[index] + 1
        val times = if (modulus == 0 || durationList[index] == 1) 2 else 3

        durationList.removeAt(index)
        repeat(times) {
            durationList.add(index, splittedDuration)
        }

        return durationList
    }

data class Duration(val duration: Int, val percent: Float)
data class TimeSignature(val numerator: Int = 0, val denominator: Int = 0)