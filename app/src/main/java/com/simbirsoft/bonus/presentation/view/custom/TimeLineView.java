package com.simbirsoft.bonus.presentation.view.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.simbirsoft.bonus.R;
import com.simbirsoft.bonus.domain.entity.profile.Timeline;

import java.util.ArrayList;
import java.util.List;

public class TimeLineView extends View {

    public static final String HOODIE = "HOODIE";
    public static final String SHIRT = "SHIRT";
    public static final String BONUS = "BONUS";
    public static final String BACKPACK = "BACKPACK";
    public static final String POWERBANK = "POWERBANK";
    public static final String BOX = "BOX";

    private Path pathEnable;
    private Path pathDisable;
    private Paint pLine;
    private CornerPathEffect pathEffect;

    private float startPosition;
    private float startPositionDisableStrokeX;
    private float stepY;
    private float stepYFirst;
    private float radius;

    private float sizeStroke;
    private float sizeTitle;
    private float sizeDescription;
    private int sizeImageLvl;
    private int sizeIconProgress;

    private int colorProgress;
    private int colorStroke;
    private int colorTitle;
    private int colorDescription;

    private float marginTopTitle;
    private float marginTopDescription;
    private float marginTopProgressIcon;
    private float marginHorizontalText;
    private int marginHorizontalImage;
    private int marginHorizontalStroke;

    private Bitmap iconDisableLvl;
    private Bitmap iconProgress;

    private List<Timeline> lvls = new ArrayList<>();

    public TimeLineView(Context context) {
        super(context);
        initItems();
    }

    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initItems();
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initItems();
    }

    public void replaceLvls(List<Timeline> lvls) {
        this.lvls.clear();
        this.lvls.addAll(lvls);
        requestLayout();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TimeLineView);
        stepY = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_step_y_size, 150);
        radius = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_radius_size, 0);
        stepYFirst = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_step_y_first_size, 0);

        colorProgress = typedArray.getColor(R.styleable.TimeLineView_ctlv_progress_color, getResources().getColor(R.color.static_white_color));
        colorStroke = typedArray.getColor(R.styleable.TimeLineView_ctlv_stroke_color, getResources().getColor(R.color.deepGrey));
        colorTitle = typedArray.getColor(R.styleable.TimeLineView_ctlv_title_color, getResources().getColor(R.color.static_white_color));
        colorDescription = typedArray.getColor(R.styleable.TimeLineView_ctlv_description_color, getResources().getColor(R.color.transparent_white_color));

        marginTopDescription = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_top_description, 125);
        marginTopTitle = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_top_title, 125);
        marginTopProgressIcon = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_top_progress_icon, 0);
        marginHorizontalImage = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_horizontal_image, 50);
        marginHorizontalText = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_horizontal_text, 185);
        marginHorizontalStroke = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_margin_horizontal_stroke, 0);

        iconDisableLvl = getBitmap(typedArray.getResourceId(R.styleable.TimeLineView_ctlv_disable_icon, 0));
        iconProgress = getBitmap(typedArray.getResourceId(R.styleable.TimeLineView_ctlv_progress_icon, 0));

        sizeDescription = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_description_size, 50);
        sizeTitle = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_title_size, 150);
        sizeStroke = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_stroke_size, 10);
        sizeImageLvl = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_image_lvl_size, 0);
        sizeIconProgress = typedArray.getDimensionPixelSize(R.styleable.TimeLineView_ctlv_icon_progress_size, 0);

        typedArray.recycle();
    }

    private void initItems() {
        pathEnable = new Path();
        pathDisable = new Path();
        pLine = new Paint();
        pathEffect = new CornerPathEffect(radius);

        if (sizeImageLvl != 0 && iconDisableLvl != null) {
            Bitmap bm = iconDisableLvl;
            iconDisableLvl = Bitmap.createScaledBitmap(bm, sizeImageLvl, sizeImageLvl, false);
            bm.recycle();
        }

        if (sizeIconProgress != 0 && iconProgress != null) {
            Bitmap bm = iconProgress;
            iconProgress = Bitmap.createScaledBitmap(bm, sizeIconProgress, sizeIconProgress, false);
            bm.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        buildPath();
        startPosition = getMeasuredWidth() / 2;
        setMeasuredDimension(widthMeasureSpec, (int) ((stepY * lvls.size()) + stepYFirst + 50));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        pLine.reset();
        pLine.setStyle(Paint.Style.STROKE);
        pLine.setStrokeWidth(sizeStroke);
        pLine.setPathEffect(pathEffect);

        canvas.translate(startPosition, 0);
        pLine.setColor(colorProgress);
        canvas.drawPath(pathEnable, pLine);
        pLine.setColor(colorStroke);
        canvas.drawPath(pathDisable, pLine);

        pLine.reset();
        pLine.setFlags(Paint.ANTI_ALIAS_FLAG);

        boolean printProgressIcon = true;

        for (int i = 0; i < lvls.size(); i++) {
            Timeline lvl = lvls.get(i);

            if (i == 0) {
                printTitleRight(pLine, canvas, lvl.getTitle(), i);
                printDescriptionRight(pLine, canvas, lvl.getDescription(), i);
                printIcon(pLine, lvl, canvas, i);

                if (iconProgress != null && lvl.getPercents() != 100) {
                    if (lvl.getCount() == 0) {
                        canvas.drawBitmap(iconProgress, -sizeIconProgress / 2, -sizeIconProgress / 2, pLine);
                    } else {
                        canvas.drawBitmap(iconProgress, -startPositionDisableStrokeX - (sizeIconProgress / 2), stepYFirst - (sizeIconProgress / 2), pLine);
                    }
                    printProgressIcon = false;
                }

                continue;
            }

            if (iconProgress != null && lvl.getPercents() != 100 && printProgressIcon) {
                canvas.drawBitmap(iconProgress, i % 2 == 0 ? startPosition - startPositionDisableStrokeX - (marginHorizontalStroke + ((float) marginHorizontalStroke / 2)) : startPositionDisableStrokeX - startPosition + ((float) marginHorizontalStroke / 2) + sizeIconProgress / 2, (stepY * i) + marginTopProgressIcon, pLine);
                printProgressIcon = false;
            }

            if (pLine.getTextAlign() == Paint.Align.LEFT) {
                printTitleLeft(pLine, canvas, lvl.getTitle(), i);
                printDescriptionLeft(pLine, canvas, lvl.getDescription(), i);
                printIcon(pLine, lvl, canvas, i);
            } else if (pLine.getTextAlign() == Paint.Align.RIGHT) {
                printTitleRight(pLine, canvas, lvl.getTitle(), i);
                printDescriptionRight(pLine, canvas, lvl.getDescription(), i);
                printIcon(pLine, lvl, canvas, i);
            }
        }
    }

    private void printIcon(Paint pLine, Timeline lvl, Canvas canvas, int i) {
        Bitmap bm = null;
        if (lvl.getCount() == lvl.getMaxCount() && lvl.getResIcon().length() != 0) {
            bm = getBitmap(getIconRes(lvl.getResIcon()));
        } else if (iconDisableLvl != null) {
            bm = iconDisableLvl;
        }

        if (bm != null) {
            if (pLine.getTextAlign() == Paint.Align.LEFT) {
                printBitmapRight(pLine, canvas, bm, i);
            } else {
                printBitmapLeft(pLine, canvas, bm, i);
            }
        }
    }

    private void printDescriptionLeft(Paint pLine, Canvas canvas, String description, int i) {
        pLine.setTextSize(sizeDescription);
        pLine.setTypeface(Typeface.DEFAULT);
        pLine.setColor(colorDescription);

        pLine.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(description, startPosition - marginHorizontalText, (stepY * i) + marginTopTitle + sizeTitle + marginTopDescription, pLine);
    }

    private void printDescriptionRight(Paint pLine, Canvas canvas, String description, int i) {
        pLine.setTextSize(sizeDescription);
        pLine.setTypeface(Typeface.DEFAULT);
        pLine.setColor(colorDescription);

        pLine.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(description, -(startPosition - marginHorizontalText), (stepY * i) + marginTopTitle + sizeTitle + marginTopDescription, pLine);
    }

    private void printTitleLeft(Paint pLine, Canvas canvas, String title, int i) {
        pLine.setTextSize(sizeTitle);
        pLine.setTypeface(Typeface.DEFAULT_BOLD);
        pLine.setColor(colorTitle);

        pLine.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(title, startPosition - marginHorizontalText, (stepY * i) + marginTopTitle, pLine);
    }

    private void printTitleRight(Paint pLine, Canvas canvas, String title, int i) {
        pLine.setTextSize(sizeTitle);
        pLine.setTypeface(Typeface.DEFAULT_BOLD);
        pLine.setColor(colorTitle);

        pLine.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(title, -(startPosition - marginHorizontalText), (stepY * i) + marginTopTitle, pLine);
    }

    private void printBitmapRight(Paint pLine, Canvas canvas, Bitmap bitmap, int i) {
        canvas.drawBitmap(bitmap, -(startPosition - marginHorizontalImage), (stepY * i) + marginTopTitle - ((stepY - sizeImageLvl) / 2), pLine);
    }

    private void printBitmapLeft(Paint pLine, Canvas canvas, Bitmap bitmap, int i) {
        canvas.drawBitmap(bitmap, startPosition - marginHorizontalImage - sizeImageLvl, (stepY * i) + marginTopTitle - ((stepY - sizeImageLvl) / 2), pLine);
    }

    private void buildPath() {
        pathDisable.reset();
        pathEnable.reset();

        float stepX = getMeasuredWidth() - (marginHorizontalStroke * 2);
        float stepXFirst = startPosition - marginHorizontalStroke;

        Path path;
        boolean enable;

        if (lvls.size() != 0 && lvls.get(0).getCount() != 0) {
            path = pathEnable;
            enable = true;
        } else {
            path = pathDisable;
            enable = false;
        }

        for (int i = 0; i < lvls.size(); i++) {
            Timeline lvl = lvls.get(i);

            if (lvl.getPercents() == 100) {
                if (i == 0) {
                    path.rLineTo(0, stepYFirst);
                    path.rLineTo(-stepXFirst, 0);
                    path.rLineTo(0, i == lvls.size() - 1 ? stepY / 2 : stepY);
                } else {
                    path.rLineTo(i % 2 == 0 ? -stepX : stepX, 0);
                    path.rLineTo(0, i == lvls.size() - 1 ? stepY / 2 : stepY);
                }
            } else {
                if (i == 0) {
                    path.rLineTo(0, stepYFirst);

                    if (enable) {
                        startPositionDisableStrokeX = (stepXFirst / 100) * lvl.getPercents();
                        path.rLineTo(-startPositionDisableStrokeX, 0);

                        path = pathDisable;
                        enable = false;

                        path.moveTo(-startPositionDisableStrokeX, stepYFirst + (stepY * i));
                    }

                    path.rLineTo(-(stepXFirst - startPositionDisableStrokeX), 0);
                    path.rLineTo(0, i == lvls.size() - 1 ? stepY / 2 : stepY);
                } else {
                    if (enable) {
                        startPositionDisableStrokeX = (stepX / 100) * lvl.getPercents();
                        path.rLineTo(i % 2 == 0 ? -startPositionDisableStrokeX : startPositionDisableStrokeX, 0);

                        path = pathDisable;
                        enable = false;

                        path.moveTo(i % 2 == 0 ? startPosition - startPositionDisableStrokeX - marginHorizontalStroke : startPositionDisableStrokeX - startPosition + marginHorizontalStroke, stepYFirst + (stepY * i));
                        path.rLineTo(i % 2 == 0 ? -(stepX - startPositionDisableStrokeX) : stepX - startPositionDisableStrokeX, 0);
                    } else {
                        path.rLineTo(i % 2 == 0 ? -stepX : stepX, 0);
                    }
                    path.rLineTo(0, i == lvls.size() - 1 ? stepY / 2 : stepY);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @Nullable
    private Bitmap getBitmap(int drawableId) {
        if (drawableId == 0) {
            return null;
        }

        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(getContext().getResources(), drawableId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (drawable instanceof VectorDrawable) {
                return getBitmap((VectorDrawable) drawable);
            } else {
                throw new IllegalArgumentException("unsupported drawable type");
            }
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    private Integer getIconRes(String icon) {
        int iconRes;

        switch (icon) {
            case HOODIE:
                iconRes = R.drawable.ic_active_achievement;
                break;
            case SHIRT:
                iconRes = R.drawable.ic_active_achievement;
                break;
            case BONUS:
                iconRes = R.drawable.ic_active_achievement;
                break;
            case BACKPACK:
                iconRes = R.drawable.ic_active_achievement;
                break;
            case POWERBANK:
                iconRes = R.drawable.ic_powerbank_active;
                break;
            case BOX:
                iconRes = R.drawable.ic_box_active;
                break;
            default:
                iconRes = R.drawable.ic_active_achievement;
        }

        return iconRes;
    }
}
