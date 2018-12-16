package Utils

import Composition.Staff

fun insertSlur(staff: Staff): Staff {

    for (index in 0..(staff.symbol.size-1)) {
        if (index+1 > staff.symbol.size-1) { continue }
        if (staff.note[index] == staff.note[index + 1]) {
            staff.symbol.set(index, "~")
        }
    }
    return staff
}