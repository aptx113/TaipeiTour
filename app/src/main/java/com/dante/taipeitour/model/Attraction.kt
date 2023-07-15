package com.dante.taipeitour.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attraction(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("name_zh")
    val nameZh: String?,
    @SerialName("open_status")
    val openStatus: Int,
    @SerialName("introduction")
    val introduction: String,
    @SerialName("open_time")
    val openTime: String,
    @SerialName("zipcode")
    val zipcode: String,
    @SerialName("distric")
    val distric: String,
    @SerialName("address")
    val address: String,
    @SerialName("tel")
    val tel: String,
    @SerialName("fax")
    val fax: String,
    @SerialName("email")
    val email: String,
    @SerialName("months")
    val months: String,
    @SerialName("nlat")
    val nlat: Double,
    @SerialName("elong")
    val elong: Double,
    @SerialName("official_site")
    val officialSite: String,
    @SerialName("facebook")
    val facebook: String,
    @SerialName("ticket")
    val ticket: String,
    @SerialName("remind")
    val remind: String,
    @SerialName("staytime")
    val staytime: String,
    @SerialName("modified")
    val modified: String,
    @SerialName("url")
    val url: String,
    @SerialName("category")
    val category: List<Category>,
    @SerialName("target")
    val target: List<Target>,
    @SerialName("service")
    val service: List<Service>,
    @SerialName("friendly")
    val friendly: List<Friendly>,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("files")
    val files: List<File>,
    @SerialName("links")
    val links: List<Link>
)
