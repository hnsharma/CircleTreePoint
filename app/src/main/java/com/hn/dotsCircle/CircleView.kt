package com.hn.dotsCircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class CircleView :
    View {



    constructor(context: Context?) : super(context!!, null, 0) {
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
    {

    }

     constructor(context: Context?, attrs: AttributeSet?)  : super(context, attrs, 0) {

    }

     constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) :super(context, attrs, defStyle) {

    }
    var points : ArrayList<Points> = ArrayList<Points>()

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // Let the ScaleGestureDetector inspect all events.
        val action = ev.action
        when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val x = ev.x
                val y = ev.y
                var point = Points(x,y)
                if(points.size >= 3)
                {
                    points.clear()
                }
                points.add(point)
                invalidate();

            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
            };
            MotionEvent.ACTION_CANCEL -> {
            };
            MotionEvent.ACTION_POINTER_UP -> {

            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.save();
        var paintPont  = Paint()
        paintPont.color  = Color.GREEN
        paintPont.strokeWidth = 10f
        println("Hari")
        for ( point in points) {
            var x : Float  = point.x;
            var y : Float  = point.y;
           // canvas.translate(point.x,point.y)
            //canvas.drawPoint(point.x,point.y,paintPont)
            println("Centrea = ($x,$y)")
            canvas.drawCircle(point.x,point.y,5f,paintPont)
        }
        if(points.size >=3)
        {

            findCircle(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,points.get(2).x,points.get(2).y,canvas)
        }


      //  canvas.restore();
    }


    fun findCircle(
        x1: Float, y1: Float,
        x2: Float, y2: Float,
        x3: Float, y3: Float,
        canvas : Canvas) {
        val x12 = x1 - x2
        val x13 = x1 - x3
        val y12 = y1 - y2
        val y13 = y1 - y3
        val y31 = y3 - y1
        val y21 = y2 - y1
        val x31 = x3 - x1
        val x21 = x2 - x1

        // x1^2 - x3^2
        val sx13 = (Math.pow(x1.toDouble(), 2.0) -
                Math.pow(x3.toDouble(), 2.0))

        // y1^2 - y3^2
        val sy13 = (Math.pow(y1.toDouble(), 2.0) -
                Math.pow(y3.toDouble(), 2.0))
        val sx21 = (Math.pow(x2.toDouble(), 2.0) -
                Math.pow(x1.toDouble(), 2.0))
        val sy21 = (Math.pow(y2.toDouble(), 2.0) -
                Math.pow(y1.toDouble(), 2.0))
        val f = ((sx13 * x12 + sy13 * x12 + sx21 * x13 + sy21 * x13)
                / (2 * (y31 * x12 - y21 * x13)))
        val g = ((sx13 * y12 + sy13 * y12 + sx21 * y13 + sy21 * y13)
                / (2 * (x31 * y12 - x21 * y13)))
        val c = -Math.pow(x1.toDouble(), 2.0) - Math.pow(y1.toDouble(), 2.0)
             - 2 * g * x1 - 2 * f * y1

        // eqn of circle be x^2 + y^2 + 2*g*x + 2*f*y + c = 0
        // where centre is (h = -g, k = -f) and radius r
        // as r^2 = h^2 + k^2 - c
        val h = -g
        val k = -f
        val sqr_of_r = h * h + k * k - c

        // r is the radius
        val r = Math.sqrt(sqr_of_r.toDouble())
        //val df = DecimalFormat("#.#####")
        println("Centre = ($h,$k)")
        System.out.println("Radius = " + r)
        var paintPont  = Paint()
        paintPont.color  = Color.GREEN
        paintPont.strokeWidth = 5f
        canvas.drawCircle(h.toFloat(),k.toFloat(), r.toFloat(),paintPont)
    }


}