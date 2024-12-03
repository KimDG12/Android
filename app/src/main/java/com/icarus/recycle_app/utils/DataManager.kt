package com.icarus.recycle_app.utils

object DataManager {
    fun stringToMap(input: String?): Map<Int, Boolean> {
        val map = mutableMapOf<Int, Boolean>()

        // input이 null이거나 빈 문자열인 경우 바로 빈 맵을 반환
        if (input.isNullOrEmpty()) {
            return map
        }

        // input이 null이 아닐 경우에만 split 실행
        var keyValuePairs = input?.split(" ")

        // keyValuePairs가 null이거나 비어 있을 경우 바로 빈 맵을 반환
        if (keyValuePairs.isNullOrEmpty()) {
            return map
        }


        for (pair in keyValuePairs) {
            val parts = pair.split(":")
            // key와 value가 모두 있는 경우에만 맵에 추가
            if (parts.size == 2) {
                val key = parts[0].toIntOrNull()
                val value = parts[1].toBoolean()

                if (key != null) {
                    map[key] = value
                }
            }
        }

        return map
    }

    fun stringToTrueMap(input: String?,countIndex: Int): Map<Int, Boolean> {
        val map = mutableMapOf<Int, Boolean>()
        var count = 0
        // input이 null이거나 빈 문자열인 경우 바로 빈 맵을 반환
        if (input.isNullOrEmpty()) {
            return map
        }

        val keyValuePairs = input?.split(" ")?.asReversed()

        // keyValuePairs가 null이거나 비어 있을 경우 바로 빈 맵을 반환
        if (keyValuePairs.isNullOrEmpty()) {
            return map
        }

        for (pair in keyValuePairs) {
            val parts = pair.split(":")
            if (parts.size == 2 && parts[1] == "true") {
                val key = parts[0].toIntOrNull()
                val value = parts[1].toBoolean()

                if (key != null) {
                    map[key] = value
                    count++
                }
                if(count == countIndex)
                    break
            }
        }

        return map
    }

    fun mapToString(map: Map<Int, Boolean>): String {
        val keyValuePairs = mutableListOf<String>()

        for ((key, value) in map) {
            keyValuePairs.add("$key:$value")

        }

        return keyValuePairs.joinToString(" ")
    }
}