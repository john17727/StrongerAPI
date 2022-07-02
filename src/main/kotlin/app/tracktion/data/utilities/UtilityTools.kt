package app.tracktion.data.utilities

import io.ktor.server.application.*
import io.ktor.util.pipeline.*

class UtilityTools {
    companion object {
        fun getNextOffset(limit: Int, currentOffset: Long, count: Long): Long? {
            val nextOffset = currentOffset + limit
            return if (nextOffset >= count) {
                null
            } else {
                nextOffset
            }
        }

        fun getPreviousOffset(limit: Int, currentOffset: Long): Long? {
            return if (currentOffset <= limit) {
                null
            } else {
                currentOffset - limit
            }
        }
    }
}