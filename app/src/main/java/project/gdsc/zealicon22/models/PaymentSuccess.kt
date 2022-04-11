package project.gdsc.zealicon22.models

data class PaymentSuccess(
    val zeal_id: String,
    val admission_no: String,
    val email: String,
    val fullname: String,
    val college: String = "JSSATE",
    val contact_no: String
)
