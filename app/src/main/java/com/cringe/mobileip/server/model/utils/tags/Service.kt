package com.cringe.mobileip.server.model.utils.tags

data class Service(override var weight: Boolean): TagTypeAndWeight {
    override val type = TagType.SERVICE

    override fun isSelected() = weight == true

    override fun getWeight() = -1
}