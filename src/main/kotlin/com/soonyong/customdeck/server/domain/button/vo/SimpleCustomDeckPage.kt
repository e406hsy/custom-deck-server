package com.soonyong.customdeck.server.domain.button.vo

import com.fasterxml.jackson.annotation.JsonAutoDetect

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
data class SimpleCustomDeckPage(val id: Int, val xCount: Int, val yCount: Int, val buttonIds: MutableList<Int>)