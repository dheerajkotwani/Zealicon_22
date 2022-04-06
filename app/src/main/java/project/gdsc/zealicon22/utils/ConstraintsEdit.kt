package project.gdsc.zealicon22.utils

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import project.gdsc.zealicon22.interfaces.ConstraintInstructions

fun ConstraintLayout.updateConstraints(instructions: List<ConstraintInstructions>) {
    ConstraintSet().also {
        it.clone(this)
        for (instruction in instructions) {
            if (instruction is ConstraintInstructions.ConnectConstraint) it.connect(instruction.startID, instruction.startSide, instruction.endID, instruction.endSide)
            if (instruction is ConstraintInstructions.DisconnectConstraint) it.clear(instruction.startID, instruction.startSide)
        }
        it.applyTo(this)
    }
}