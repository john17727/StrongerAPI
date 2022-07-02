package app.tracktion.data.utilities

import io.ktor.server.application.*
import io.ktor.util.pipeline.*


fun PipelineContext<Unit, ApplicationCall>.getLimit(): Int {
    return (call.request.queryParameters["limit"]?.toInt() ?: Constants.LIMIT).let {
        if (it > Constants.MAX_LIMIT) {
            Constants.MAX_LIMIT
        } else {
            it
        }
    }
}