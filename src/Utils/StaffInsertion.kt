package Utils

import Composition.Staff

fun Staff.insertStaff(newStaff: Staff) : Staff {
    note.addAll(newStaff.note)
    duration.addAll(newStaff.duration)
    symbol.addAll(newStaff.symbol)
    return this
}

fun staffInsertion(first: Staff, second: Staff) : Staff {
    var output = Staff()

    output.note.addAll(first.note)
    output.note.addAll(second.note)

    output.duration.addAll(first.duration)
    output.duration.addAll(second.duration)

    output.symbol.addAll(first.symbol)
    output.symbol.addAll(second.symbol)

    return output
}