package com.example.adaptiveapp

import android.graphics.Rect
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import java.io.Serializable

/**
 * Information about the posture of the device
 */
sealed interface DevicePosture {
    object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

@OptIn(ExperimentalContracts::class)
fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
}
fun foldCases(activity :MainActivity = MainActivity(),lifecycleScope : LifecycleCoroutineScope) =

         WindowInfoTracker.getOrCreate(activity).windowLayoutInfo(activity)
        .flowWithLifecycle(activity.lifecycle)
        .map { layoutInfo ->
            val foldingFeature =
                layoutInfo.displayFeatures
                    .filterIsInstance<FoldingFeature>()
                    .firstOrNull()
            when {
                isBookPosture(foldingFeature) ->
                    DevicePosture.BookPosture(foldingFeature.bounds)

                isSeparating(foldingFeature) ->
                    DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

                else -> DevicePosture.NormalPosture
            }
        }
        .stateIn(
            scope = lifecycleScope,
            started = SharingStarted.Eagerly,
            initialValue = DevicePosture.NormalPosture
        )
