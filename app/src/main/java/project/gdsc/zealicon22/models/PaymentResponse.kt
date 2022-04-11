package project.gdsc.zealicon22.models

data class PaymentResponse(
    val key_id: String?,
    val order_id: String?,
    val amount: Int?,
    val server_order_id: String?
)
