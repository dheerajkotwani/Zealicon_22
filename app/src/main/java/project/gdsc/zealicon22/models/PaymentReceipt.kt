package project.gdsc.zealicon22.models

data class PaymentReceipt(
    val razorpay_payment_id: String?,
    val razorpay_order_id: String?,
    val razorpay_signature: String?,
    val server_order_id: String
)
