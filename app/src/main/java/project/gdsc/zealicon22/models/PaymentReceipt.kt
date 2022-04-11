package project.gdsc.zealicon22.models

data class PaymentReceipt(
    var razorpay_payment_id: String?,
    var razorpay_order_id: String?,
    var razorpay_signature: String?,
    val server_order_id: String,
    val admission_no: String,
    val college: String,
    val contact_no: String,
    val fullname: String,
    val email: String
)
