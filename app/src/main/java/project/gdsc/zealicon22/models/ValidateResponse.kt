package project.gdsc.zealicon22.models

/**
 * @author Dheeraj Kotwani on 13/04/22.
 */
class ValidateResponse(
    val message: String,
    val admission_no: ArrayList<String>,
    val contact_co: ArrayList<String>,
    val fullname: ArrayList<String>,
    val college: ArrayList<String>,
)