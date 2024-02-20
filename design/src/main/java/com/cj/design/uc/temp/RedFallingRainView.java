//package com.ng.code.temp;
//
//import java.util.LinkedList;
//
//import android.graphics.Bitmap;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
///**
// * @author : jiangzhengnan.jzn@alibaba-inc.com
// * @creation : 2023/05/18
// * @description :
// */
//
//public class RedFallingRainView extends SurfaceView implements SurfaceHolder.Callback {
//
//    private static final String TAG = "RedFallingRainView";
//
//    private static final int COLUMN_COUNT = 3;
//    private static final float UNNORMAL_BARRAGE_RATIO = 0.8f;
//    private static final int TASK_DELAY_MILLS = 25;
//    private static final int LEFT_RIGHT_SPACE = dp2px(2);
//    private static final int COLUMN_HOR_SPACE = dp2px(4);
//
//    private int mColumnWidth;
//    private int mSpeedPx;
//    private int mBarrageShowSize;
//    private ColumnInfo[] mBarrageColumnPos;
//    @Nullable
//    private Bitmap mBitmapIcon;
//    private final Timer mTimer = new Timer();
//
//    @NonNull
//    private final ArrayList<FallingItemData> mCurrentRenderBarrages = new ArrayList<>();
//    private final BarrageDataPool mBarrageDataPool;
//    private final Paint mPaint;
//    @NonNull
//    private final RectF mIconDestPos = new RectF();
//    private static final Random RANDOM = new Random();
//    private SurfaceHolder mSurfaceHolder;
//    private AtomicBoolean mIsSurfaceCreated;
//
//    public RedFallingRainView(final Context context) {
//        super(context);
//        mBarrageDataPool = new BarrageDataPool(10);
//        mPaint = new Paint();
//        mPaint.setFilterBitmap(true);
//        mPaint.setAntiAlias(true);
//        init();
//    }
//
//    public void startShowFallingRain(@Nullable final String iconUrl) {
//        if (StringUtils.isEmpty(iconUrl)) {
//            return;
//        }
//
//        SdkImgLoader.getInstance().decodeNetImage(iconUrl, new SimpleImageDecodeListener() {
//
//            @Override
//            public void onImageDecoded(final String url, final boolean state, final Bitmap bitmap) {
//                if (bitmap == null || bitmap.isRecycled()) {
//                    return;
//                }
//                Log.d(TAG, "onImageDecoded over !!");
//                initBarrageData( 12, 100, bitmap);
//                try {
//                    mTimer.schedule(new TimerTask() {
//
//                        @Override
//                        public void run() {
//                            updateAndDrawBarrage();
//                        }
//                    }, 0, TASK_DELAY_MILLS);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void release() {
//        Log.d(TAG, "paint release. barrage finish !!");
//        synchronized (this) {
//            mBitmapIcon = null;
//            mTimer.cancel();
//        }
//    }
//
//    private void init() {
//        setZOrderOnTop(true);
//        mSurfaceHolder = getHolder();
//        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
//        mSurfaceHolder.addCallback(this);
//        mIsSurfaceCreated = new AtomicBoolean(false);
//    }
//
//    private void updateAndDrawBarrage() {
//        if (mBarrageColumnPos == null || !isShown() || !mIsSurfaceCreated.get()) {
//            Log.e(TAG, "data is invalidate, view is not shown or state invalidate. mIsSurfaceCreated = " + mIsSurfaceCreated.get());
//            return;
//        }
//
//        Canvas canvas = null;
//        try {
//            canvas = mSurfaceHolder.lockCanvas();
//            if (canvas == null) {
//                Log.e(TAG, "get canvas from holder fail");
//                return;
//            }
//            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//
//            synchronized (this) {
//                if (mBitmapIcon == null || mBitmapIcon.isRecycled()) {
//                    return;
//                }
//
//                FallingItemData itemData;
//                for (int i = 0; i < mBarrageColumnPos.length; ++i) {
//                    final int laneIndex = getShortestLaneIndex(mBarrageColumnPos);
//                    final int columnEndY   = mBarrageColumnPos[laneIndex].endColumnPosY;
//                    if (columnEndY < 0 || columnEndY >= getHeight()) {
//                        break;
//                    }
//
//                    itemData = updateBarragePos(laneIndex, columnEndY);
//                    mCurrentRenderBarrages.add(itemData);
//                }
//
//                FallingItemData currentBarrage;
//                Iterator<FallingItemData> iterator = mCurrentRenderBarrages.iterator();
//                while (iterator.hasNext()) {
//                    currentBarrage = iterator.next();
//                    /** 如果弹幕在可见区域，绘制并移动，否则删除 */
//                    if (currentBarrage.endY < currentBarrage.startY && (currentBarrage.endY < getHeight())) {
//                        if (currentBarrage.startY > 0) {
//                            drawCanvas(mBitmapIcon, canvas, currentBarrage);
//                        }
//                        currentBarrage.move(currentBarrage.speed);
//                    } else {
//                        iterator.remove();
//                        mBarrageDataPool.recycle(currentBarrage);
//                    }
//                }
//
//                /** 更新每个弹道的最后的位置信息 */
//                for (ColumnInfo columnInfo : mBarrageColumnPos) {
//                    columnInfo.move(mSpeedPx);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (mIsSurfaceCreated.get() && canvas != null) {
//                mSurfaceHolder.unlockCanvasAndPost(canvas);
//            }
//        }
//    }
//
//    private void drawCanvas(@NonNull final Bitmap bitmap, Canvas canvas, FallingItemData fallingItemData) {
//        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
//            return;
//        }
//
//        if (canvas != null) {
//            int xPos = fallingItemData.startX;
//            int yPos = fallingItemData.startY;
//            int size = (int) (mBarrageShowSize * (fallingItemData.mNormalSize ? 1 : UNNORMAL_BARRAGE_RATIO));
//            int width, height;
//            if (bitmap.getHeight() > bitmap.getWidth()) {
//                height = size;
//                width = bitmap.getWidth() * size / bitmap.getHeight();
//            } else {
//                width = size;
//                height = bitmap.getHeight() * size / bitmap.getWidth();
//            }
//
//            mIconDestPos.set(xPos, yPos - height, width + xPos, yPos);
//            canvas.drawBitmap(bitmap, null, mIconDestPos, mPaint);
//        }
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        release();
//    }
//
//    @Override
//    public boolean onTouchEvent(final MotionEvent event) {
//        if (!HCAdConfig.isFallingRainClickable()) {
//            Log.e(TAG, "点击开关关闭，不允许点击");
//            return false;
//        }
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            setTag(null);
//            /** 遍历当前红包，如果点击坐标命中红包，则响应点击行为；反之不处理 */
//            ArrayList<FallingItemData> tmpRenderFallingItem;
//            synchronized (this) {
//                tmpRenderFallingItem = new ArrayList<>(mCurrentRenderBarrages);
//            }
//
//            final float x = event.getX();
//            final float y = event.getY();
//
//            for (FallingItemData itemData : tmpRenderFallingItem) {
//                if (itemData.endY >= itemData.startY || (itemData.startY < 0 || itemData.endY >= getHeight())) {
//                    continue;
//                }
//                if (x >= itemData.startX && x <= itemData.endX && y <= itemData.startY && y >= itemData.endY) {
//                    setTag(HCSplashViewClickType.TAG_ICON_FALLING_RAIN_VIEW);
//                    Log.e(TAG, "ACTION_DOWN : click barrage match");
//                    return super.onTouchEvent(event);
//                }
//            }
//            return false;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    public void initBarrageData(int speedPix, int iconSizeDp, final Bitmap bitmap) {
//        final int width = getWidth() > 0 ? getWidth() : Utils.getScreenWidth(getContext());
//        mColumnWidth = (width - LEFT_RIGHT_SPACE * 2 - COLUMN_HOR_SPACE * (COLUMN_COUNT - 1)) / COLUMN_COUNT;
//        mSpeedPx = speedPix;
//        mBarrageShowSize = dp2px(iconSizeDp);
//        if (mBarrageShowSize >= mColumnWidth) {
//            mBarrageShowSize = mColumnWidth - dp2px(6);
//        }
//
//        mBarrageColumnPos = new ColumnInfo[COLUMN_COUNT];
//        mBitmapIcon = bitmap;
//
//        final ColumnInfo[] distancePos = new ColumnInfo[]{
//          new ColumnInfo(-mBarrageShowSize, true),
//          new ColumnInfo(0, true),
//          new ColumnInfo((int) (-mBarrageShowSize * UNNORMAL_BARRAGE_RATIO), false)};
//        for (int i = 0; i < mBarrageColumnPos.length; ++i) {
//            mBarrageColumnPos[i] = distancePos[i % 3];
//        }
//    }
//
//    private FallingItemData updateBarragePos(int lineNum, int startPosY) {
//        final FallingItemData itemData = mBarrageDataPool.obtain();
//        itemData.init(mSpeedPx);
//        itemData.mNormalSize = mBarrageColumnPos[lineNum].isNormalSize;
//        final int itemSize = (int) (itemData.mNormalSize ? mBarrageShowSize  : mBarrageShowSize * UNNORMAL_BARRAGE_RATIO);
//        // x pos
//        final int boundX = (mColumnWidth - itemSize) / 2;
//        final int randomOffsetX = RANDOM.nextInt(boundX) * (RANDOM.nextBoolean() ? -1 : 1);
//        int startPosX = LEFT_RIGHT_SPACE + lineNum * (COLUMN_HOR_SPACE + mColumnWidth);
//        startPosX += ((mColumnWidth - itemSize) / 2);
//        startPosX += randomOffsetX;
//
//        // y pos
//        itemData.setPos(startPosX, startPosY, itemSize);
//        // random y gap
//        final int gap = yGapSpace();
//        mBarrageColumnPos[lineNum].set(itemData.endY - gap, !mBarrageColumnPos[lineNum].isNormalSize);
//        return itemData;
//    }
//
//    private static int yGapSpace() {
//        return dp2px(RANDOM.nextInt(80) + 100);
//    }
//
//    /**
//     * 计算多行弹幕时，当前最短的列index
//     */
//    private static int getShortestLaneIndex(@NonNull ColumnInfo[] lineStartPos) {
//        if (lineStartPos.length == 0) {
//            return 0;
//        }
//
//        int resultIndex = 0;
//        float maxPosY = lineStartPos[0].endColumnPosY;
//        for (int index = 1; index < lineStartPos.length; ++index) {
//            if (maxPosY < lineStartPos[index].endColumnPosY) {
//                maxPosY = lineStartPos[index].endColumnPosY;
//                resultIndex = index;
//            }
//        }
//        return resultIndex;
//    }
//
//    private static int dp2px(float dpValue) {
//        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
//        return (int) (dpValue * metrics.density + 0.5f);
//    }
//
//    @Override
//    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
//        mIsSurfaceCreated.set(true);
//        Log.d(TAG, "surfaceCreated !!");
//    }
//
//    @Override
//    public void surfaceChanged(final SurfaceHolder surfaceHolder, final int format, final int width, final int height) {}
//
//    @Override
//    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
//        mIsSurfaceCreated.set(false);
//    }
//
//    private static class ColumnInfo {
//        private int endColumnPosY;
//        private boolean isNormalSize;
//
//        public ColumnInfo(int pos, boolean normalSize) {
//            set(pos, normalSize);
//        }
//
//        public void set(int pos, boolean normalSize) {
//            this.endColumnPosY = pos;
//            this.isNormalSize = normalSize;
//        }
//
//        public void move(int distance) {
//            this.endColumnPosY += distance;
//        }
//    }
//
//    private static class FallingItemData {
//        private int startX;
//        private int endX;
//        private int startY;
//        private int speed;
//        private int endY;
//        private boolean mNormalSize;
//        private final AtomicBoolean isRecycle = new AtomicBoolean(true);
//
//        public void init(int speedPix) {
//            this.speed = speedPix;
//        }
//
//        public void setPos(int x, int y, int itemSize) {
//            this.startX = x;
//            this.startY = y;
//            this.endX = x + itemSize;
//            this.endY = y - itemSize;
//        }
//
//        public void move(float distance) {
//            this.startY += distance;
//            this.endY += distance;
//        }
//
//        @Override
//        public boolean equals(final Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            FallingItemData that = (FallingItemData) o;
//            return that.hashCode() == hashCode();
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(startX, endX, startY, endY, speed, isRecycle);
//        }
//
//        public boolean isRecycled() {
//            return isRecycle.get();
//        }
//
//        public void setUsing(final boolean using) {
//            this.isRecycle.set(!using);
//        }
//    }
//
//    public static class BarrageDataPool {
//        private final LinkedList<FallingItemData> mDataPool = new LinkedList<>();
//
//        public BarrageDataPool(int initialCapacity) {
//            synchronized (this) {
//                for (int i = 0; i < initialCapacity; ++i) {
//                    mDataPool.offer(new FallingItemData());
//                }
//            }
//        }
//
//        @NonNull
//        public synchronized FallingItemData obtain() {
//            for (FallingItemData tempData : mDataPool) {
//                if (tempData.isRecycled()) {
//                    tempData.setUsing(true);
//                    return tempData;
//                }
//            }
//
//            FallingItemData data = new FallingItemData();
//            data.setUsing(true);
//            boolean success = mDataPool.offer(data);
//            Log.w("BarrageSurfaceView", "obtain new create: hash = " + data.hashCode() + ", add = " + success);
//            return data;
//        }
//
//        public synchronized void recycle(@NonNull FallingItemData data) {
//            data.setUsing(false);
//        }
//
//        public synchronized void clean() {
//            mDataPool.clear();
//        }
//    }
//}