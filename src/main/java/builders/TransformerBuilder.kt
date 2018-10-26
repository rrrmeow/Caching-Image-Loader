package builders

import javafx.scene.image.ImageView
import transformations.CircleCropTransformation
import transformations.FitCenterTransformation
import transformations.ImageTransformation
import transformations.ResizeTransformation

class TransformerBuilder {
  private val transformers = mutableListOf<ImageTransformation>()

  fun getTransformers(): MutableList<ImageTransformation> = transformers

  fun fitCenter(width: Int, height: Int): TransformerBuilder {
    if (transformers.any { it is FitCenterTransformation }) {
      throw IllegalStateException("FitCenterTransformation already applied!")
    }

    transformers.add(FitCenterTransformation(width.toDouble(), height.toDouble()) as ImageTransformation)
    return this
  }

  fun fitCenter(view: ImageView): TransformerBuilder {
    if (transformers.any { it is FitCenterTransformation }) {
      throw IllegalStateException("FitCenterTransformation already applied!")
    }

    transformers.add(FitCenterTransformation(view.fitWidth, view.fitHeight) as ImageTransformation)
    return this
  }

  fun circleCrop(circleCropParamsBuilder: CircleCropParametersBuilder): TransformerBuilder {
    if (transformers.any { it is CircleCropTransformation }) {
      throw IllegalStateException("CircleCropTransformation already applied!")
    }

    transformers.add(CircleCropTransformation(circleCropParamsBuilder.getParameters()) as ImageTransformation)
    return this
  }

  fun resize(newWidth: Int, newHeight: Int): TransformerBuilder {
    if (transformers.any { it is ResizeTransformation }) {
      throw IllegalStateException("ResizeTransformation already applied!")
    }

    transformers.add(ResizeTransformation(newWidth.toDouble(), newHeight.toDouble()) as ImageTransformation)
    return this
  }

  fun resize(newWidth: Double, newHeight: Double): TransformerBuilder {
    if (transformers.any { it is ResizeTransformation }) {
      throw IllegalStateException("ResizeTransformation already applied!")
    }

    transformers.add(ResizeTransformation(newWidth, newHeight) as ImageTransformation)
    return this
  }
}