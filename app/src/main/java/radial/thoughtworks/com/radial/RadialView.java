package radial.thoughtworks.com.radial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RadialView extends View {

    Paint radialPaint;
    Paint barPaint;
    int viewHeight;
    int viewWidth;
    int centerX;
    int centerY;
    int radius = 200;
    int padding = 30;
    DrawingType type = DrawingType.Circle;

    public RadialView(Context context) {
        super(context);
        init();
    }

    public RadialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        barPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setColor(getResources().getColor(R.color.androidGreen));

        radialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        radialPaint.setStyle(Paint.Style.STROKE);
        radialPaint.setStrokeWidth(20);
        radialPaint.setColor(getResources().getColor(R.color.androidGreen));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);

        centerX = viewWidth / 2 ;
        centerY = viewHeight / 2;

        if(2*(radius + padding) < viewHeight ) {
            type = DrawingType.Circle;
        } else if (viewHeight > 150) {
            type = DrawingType.Ellipse;
        } else {
            type = DrawingType.Bar;
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(type == DrawingType.Circle) {
            canvas.drawArc(centerX-radius, centerY-radius, centerX+radius, centerY+radius, 0, 360, true, radialPaint);
        } else if(type == DrawingType.Ellipse) {
            float leftx = centerX - radius;
            float topy = padding;
            float rightx = centerX + radius;
            float bottomy = viewHeight - padding;
            RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
            canvas.drawOval(ovalBounds, radialPaint);
        } else if(type == DrawingType.Bar) {
            canvas.drawRect(padding, padding, viewWidth - padding, viewHeight-padding, barPaint);
        }

    }

    enum DrawingType {
        Ellipse,
        Circle,
        Bar
    }
}
