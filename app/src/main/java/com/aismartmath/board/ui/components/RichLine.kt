package com.aismartmath.board.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.remember
import com.aismartmath.board.board.Part
import com.aismartmath.board.math.MathNode
import com.aismartmath.board.math.hasMath
import com.aismartmath.board.math.parseRich
import com.aismartmath.board.math.richPieces

/** Text with real stacked fractions (numerator over a bar over denominator). */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RichLine(parts: List<Part>, modifier: Modifier = Modifier) {
    FlowRow(modifier) {
        parts.forEach { part ->
            when (part) {
                is Part.T -> if (hasMath(part.s)) {
                    val pieces = remember(part.s) { richPieces(part.s) }
                    pieces.forEach { pc -> MathView(pc, 13.sp, modifier = Modifier.align(Alignment.CenterVertically)) }
                } else {
                    Text(part.s, color = Color.White, fontSize = 13.sp, modifier = Modifier.align(Alignment.CenterVertically))
                }
                is Part.M -> MathView(part.node, 13.sp, modifier = Modifier.align(Alignment.CenterVertically))
                is Part.F -> {
                    val node = remember(part.n, part.d) { MathNode.Frac(parseRich(part.n), parseRich(part.d)) }
                    MathView(node, 13.sp, modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 3.dp))
                }
            }
        }
    }
}
