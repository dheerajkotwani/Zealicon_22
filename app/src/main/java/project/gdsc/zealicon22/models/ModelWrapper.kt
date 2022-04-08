package project.gdsc.zealicon22.models

/**
 * Created by Nakul
 * on 08,April,2022
 */
data class ModelWrapper<T: Any?>(
    val success: Boolean,
    val data: T
)
