package fr.lehautcambara.gitstars.bus.events

import retrofit2.Response

data class RetrofitError(val event: BusEvent, val response:  Response<*>) : BusEvent()
