package io.github.francoiscampbell.manytimes.layout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import io.github.francoiscampbell.manytimes.R
import io.github.francoiscampbell.manytimes.shape.Oval
import java.util.*

/**
 * Created by francois on 2016-01-12.
 */
class CircleLayout : ViewGroup {
    private val TAG = "CircleLayout"

    private var centerViewId: Int
    private var angle: Float
    private var equalSpacing: Boolean

    private lateinit var displayArea: Rect
    private lateinit var center: Point
    private var outerRadius: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleLayout, defStyleAttr, 0)
        centerViewId = attributes.getResourceId(R.styleable.CircleLayout_centerView, View.NO_ID)
        angle = Math.toRadians(attributes.getFloat(R.styleable.CircleLayout_angle, 0f).toDouble()).toFloat()
        equalSpacing = attributes.getBoolean(R.styleable.CircleLayout_angle, true)
        attributes.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        displayArea = Rect(left + paddingLeft, top + paddingTop, right - paddingRight, bottom - paddingBottom)

        val displayAreaWidth = displayArea.right - displayArea.left
        val displayAreaHeight = displayArea.bottom - displayArea.top
        center = Point(paddingLeft + displayAreaWidth / 2, paddingRight + displayAreaHeight / 2)
        outerRadius = Math.min(displayAreaWidth, displayAreaHeight) / 2

        val centerView = findViewById(centerViewId)
        centerView.layoutAtCenter(center)

        val childrenToLayout = ArrayList<View>(childCount)
        for (i in 0..childCount) {
            val child = getChildAt(i) ?: continue
            if (child.id != centerViewId && child.visibility != GONE) {
                childrenToLayout.add(child)
            }
        }

        val angleIncrement = if (angle == 0f) (2 * Math.PI / childrenToLayout.size).toFloat() else angle
        var currentAngle = 0.0
        for (child in childrenToLayout) {
            val innerWidth = outerRadius - child.measuredWidth / 2
            val innerHeight = outerRadius - child.measuredHeight / 2
            val ovalAxisA = Math.min(innerWidth, innerHeight)
            val ovalAxisB = Math.max(innerWidth, innerHeight)
            val oval = Oval(ovalAxisA.toDouble(), ovalAxisB.toDouble())
            val radius = oval.getRadiusAtAngle(currentAngle)

            val childCenter = toPoint(radius, currentAngle, center)
            child.layoutAtCenter(childCenter)

            currentAngle += angleIncrement
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        if (isInEditMode) {
            val paint = Paint()
            paint.strokeWidth = 10f
            paint.color = Color.RED
            paint.style = Paint.Style.STROKE
            canvas?.drawCircle(center.x.toFloat(), center.y.toFloat(), outerRadius.toFloat(), paint)
        }
    }

    fun View.getCenter(): Point {
        return Point((right + left) / 2, (top + bottom) / 2)
    }

    fun View.layoutAtCenter(center: Point) {
        val left = center.x - measuredWidth / 2
        val top = center.y - measuredHeight / 2
        val right = left + measuredWidth
        val bottom = top + measuredHeight
        layout(left, top, right, bottom)
    }

    fun toPoint(radius: Double, angle: Double, origin: Point = Point(0, 0)): Point {
        val newX = radius * Math.cos(angle)
        val newY = radius * Math.sin(angle)
        return Point(origin.x + newX.toInt(), origin.y - newY.toInt())
    }
}
