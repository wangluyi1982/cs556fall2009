package android_programmers_guide.AndroidViews;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;

public class GraphView extends View {

	public static boolean BAR = true;
	public static boolean LINE = false;

	private Paint paint;
	private double[] values;
	private String[] horlabels;
	private String[] verlabels;
	private String title;
	private boolean type;

	public GraphView(Context context, double[] values, String title, String[] horlabels, String[] verlabels, boolean type) {
		super(context);
		if (values == null)
			values = new double[0];
		else
			this.values = values;
		if (title == null)
			title = "";
		else
			this.title = title;
		if (horlabels == null)
			this.horlabels = new String[0];
		else
			this.horlabels = horlabels;
		if (verlabels == null)
			this.verlabels = new String[0];
		else
			this.verlabels = verlabels;
		this.type = type;
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		float border = 20;
		float horstart = border * 2;
		float height = getHeight()/2;
		float width = getWidth()-1;
		double max = getMax();
		double min = getMin();
		float diff = (float)(max - min);
		float graphheight = height /2;
		float graphwidth = width -(2*border);

		paint.setTextAlign(Align.LEFT);
		int vers = verlabels.length - 1;
		paint.setColor(Color.WHITE);
		canvas.drawLine(horstart, height-border, width, height-border, paint);
		for (int i = 0; i < verlabels.length; i++) {
			float y = ((graphheight / vers) * i) + border;
			paint.setColor(Color.BLUE);
			canvas.drawText(verlabels[i], 50, y, paint);
		}
		paint.setColor(Color.WHITE);
		canvas.drawLine(horstart, height - border, horstart, border, paint);
		for (int i = 0; i < horlabels.length; i++) {
			float datalength = values.length;
			float colwidth = (width /2) / datalength;
			paint.setTextAlign(Align.CENTER);
			if (i==horlabels.length-1)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.YELLOW);
			canvas.drawText(horlabels[i], 20+(colwidth*i)+horstart, height - 4, paint);
		}
		
		canvas.drawText("C1-Calories to be consumed this week: "+values[0], 300, height +30, paint);
		canvas.drawText("C2-Calories consumed till date:       "+values[2], 300, height +50, paint);
		canvas.drawText("C3-Calories spent during exercise:    "+values[1], 300, height +70, paint);
		canvas.drawText("C4-Calories stil to be consumed:       "+values[3], 300, height +90, paint);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);
		if (max != min) {
			paint.setColor(Color.GREEN);
			if (type == BAR) {
				float datalength = values.length;
				float colwidth = (width /2) / datalength;
				for (int i = 0; i < values.length; i++) {
					double val = values[i] - min;
					double rat = val / diff;
					float h = (float)(graphheight * rat);
					canvas.drawRect((i * colwidth) + horstart, (border - h) + graphheight, ((i * colwidth) + horstart) + (colwidth - 1), height - (border - 1), paint);
				}
			} else {
				float datalength = values.length;
				float colwidth = (width - (2 * border)) / datalength;
				float halfcol = colwidth / 2;
				float lasth = 0;
				for (int i = 0; i < values.length; i++) {
					double val = values[i] - min;
					double rat = val / diff;
					float h = (float)(graphheight * rat);
					if (i > 0)
						canvas.drawLine(((i - 1) * colwidth) + (horstart + 1) + halfcol, (border - lasth) + graphheight, (i * colwidth) + (horstart + 1) + halfcol, (border - h) + graphheight, paint);
					lasth = h;
				}
			}
		}
	}
	private double getMax() {
		double largest = Double.MIN_VALUE;
		for (int i = 0; i < values.length; i++)
			if (values[i] > largest)
				largest = values[i];
		return largest;
	}

	private double getMin() {
		double smallest = Double.MAX_VALUE;
		for (int i = 0; i < values.length; i++)
			if (values[i] < smallest)
				smallest = values[i];
		return smallest;
	}

}
