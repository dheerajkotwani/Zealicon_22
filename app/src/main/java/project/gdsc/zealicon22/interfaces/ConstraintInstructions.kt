package project.gdsc.zealicon22.interfaces

interface ConstraintInstructions{
    data class ConnectConstraint(val startID: Int, val startSide: Int, val endID: Int, val endSide: Int) : ConstraintInstructions
    data class DisconnectConstraint(val startID: Int, val startSide: Int) : ConstraintInstructions
}