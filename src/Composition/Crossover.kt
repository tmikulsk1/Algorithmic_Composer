package Composition

import Utils.*

fun SimpleCrossover(staffOne: Staff, staffTwo: Staff): Result {
    val output = Staff()

    for (index in 0..(staffOne.note.size-1)) {
        var selectedStaff = (0..1).random()

        if (index >= staffTwo.note.size) { selectedStaff = 0 }
        if (index >= staffOne.note.size) { selectedStaff = 1 }

        if (selectedStaff == 0) {
            if (index >= staffOne.note.size) { continue }
            output.note.add(staffOne.note[index])
            output.duration.add(staffOne.duration[index])
            output.symbol.add(staffOne.symbol[index])
            continue
        }
        if (selectedStaff == 1) {
            output.note.add(staffTwo.note[index])
            output.duration.add(staffTwo.duration[index])
            output.symbol.add(staffTwo.symbol[index])
        }
    }

    val result = splitDuration(output)
    return result
}