package Utils

import Composition.Staff
import Finals.NoteDuration

fun splitDuration(staff: Staff): Result {

        //var input = mutableListOf(1, 4, 4, 1, 3, 2, 2, 0)
        var finalStaff = staff
        //var list = finalStaff.duration
        val timeSignature = TimeSignature(4,4)
        val denominator = timeSignature.denominator.toFloat()
        val numerator = timeSignature.numerator.toFloat()
        var fullCompass = mutableListOf<Duration>()
        val output = mutableListOf<List<Duration>>()
        var index = 0
        var run = true

        while (run) {
            var modulus = finalStaff.duration[index].duration.rem(2)
            if (modulus != 0 && finalStaff.duration[index].duration != 1) {
                breakDuration(staff, index)
            }

            var percent: Float = (denominator / finalStaff.duration[index].duration) / numerator
            var compassSize = fullCompass.map { it.percent }.sum()

            if (percent > 1 || compassSize + percent > 1f) {
                while (percent > 1 || compassSize + percent > 1f) {
                    finalStaff = breakDuration(staff, index)
                    percent = (denominator / finalStaff.duration[index].duration) / numerator
                }
            }

            fullCompass.add(Duration(finalStaff.duration[index].duration, percent))

            compassSize = fullCompass.map { it.percent }.sum()
            if (compassSize == 1f) {
                output.add(fullCompass)
                fullCompass = mutableListOf<Duration>()
            }

            index += 1
            if (index == finalStaff.duration.size - 1) {
                output.add(fullCompass)
                run = false
            }
        }

        return Result(output, finalStaff)
    }

    fun breakDuration(staff: Staff, index: Int): Staff {
        val actualDuration = staff.duration
        val actualNote = staff.note
        val actualSymbol = staff.symbol

        val modulus = actualDuration[index].duration.rem(2)
        val splittedDuration = if (modulus == 0 || actualDuration[index].duration == 1) actualDuration[index].duration * 2 else actualDuration[index].duration + 1
        val times = if (modulus == 0 || actualDuration[index].duration == 1) 2 else 3

        actualDuration.removeAt(index)
        actualNote.removeAt(index)
        actualSymbol.removeAt(index)

        repeat(times) {
            actualDuration.add(index, NoteDuration(splittedDuration))
            actualNote.add(index, actualNote[index])
            actualSymbol.add(index, actualSymbol[index])
        }

        return Staff(actualNote, actualDuration, actualSymbol)
    }

data class Duration(val duration: Int, val percent: Float)
data class TimeSignature(val numerator: Int = 0, val denominator: Int = 0)

data class Result(val listCompass: List<List<Duration>>, val staff: Staff)