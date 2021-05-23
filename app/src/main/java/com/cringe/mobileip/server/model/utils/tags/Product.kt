package com.cringe.mobileip.server.model.utils.tags

data class Product(override var weight: Double) : TagTypeAndWeight {
    override val type = TagType.PRODUCT

    override fun isSelected() = weight != 0.0
}