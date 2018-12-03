package Composition

import Finals.NoteDuration
import Finals.listOfNoteDurations
import java.util.*

fun extendNotes(size: Int, staffQuantity: Int, listOfNotes: List<String>): MutableList<Staff> {
    val durations = listOfNoteDurations()
    val staffs = mutableListOf<Staff>()

    var noteIndex = 0

    repeat(staffQuantity) {
        staffs.add(Staff())
    }

    for (index in 0..size) {
        val selectedStaff = (0..(staffQuantity-1)).random()
        val quantitySlurs = (0..4).random()

        if (staffs[selectedStaff].note.size == 0) {
            for (i in 0..quantitySlurs) {
                val note = "r"
                val duration = durations[(0..8).random()] // static max/min durations
                val symbol = ""

                staffs[selectedStaff].note.add(note)
                staffs[selectedStaff].duration.add(duration)
                staffs[selectedStaff].symbol.add(symbol)
            }

            continue
        }

        for (i in 0..quantitySlurs) {
            val note = listOfNotes[noteIndex]
            val duration = durations[(0..8).random()] // static max/min durations
            val symbol = ""

            staffs[selectedStaff].note.add(note)
            staffs[selectedStaff].duration.add(duration)
            staffs[selectedStaff].symbol.add(symbol)
        }

        if (noteIndex == listOfNotes.size -1) {
            noteIndex = 0
        } else {
            noteIndex += 1
        }
    }

    return staffs
}

data class Staff (
        val note:  MutableList<String> = ArrayList(),
        val duration: MutableList<NoteDuration> = ArrayList(),
        val symbol: MutableList<String> = ArrayList()
)