package com.linus.glesandroiduilib.GLES.glrenderer;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by linus on 2017/8/21.
 */

public class GLES30Canvas implements GLCanvas {

    
    @Override
    public GLId getGLId() {
        return null;
    }

    @Override
    public void setSize(int width, int height) {

    }

    @Override
    public void clearBuffer() {

    }

    @Override
    public void clearBuffer(float[] argb) {

    }

    @Override
    public void setAlpha(float alpha) {

    }

    @Override
    public float getAlpha() {
        return 0;
    }

    @Override
    public void multiplyAlpha(float alpha) {

    }

    @Override
    public void translate(float x, float y, float z) {

    }

    @Override
    public void translate(float x, float y) {

    }

    @Override
    public void scale(float sx, float sy, float sz) {

    }

    @Override
    public void rotate(float angle, float x, float y, float z) {

    }

    @Override
    public void multiplyMatrix(float[] mMatrix, int offset) {

    }

    @Override
    public void save() {

    }

    @Override
    public void save(int saveFlags) {

    }

    @Override
    public void restore() {

    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2, GLPaint paint) {

    }

    @Override
    public void drawRect(float x1, float y1, float x2, float y2, GLPaint paint) {

    }

    @Override
    public void fillRect(float x, float y, float width, float height, int color) {

    }

    @Override
    public void drawTexture(BasicTexture texture, int x, int y, int width, int height) {

    }

    @Override
    public void drawMesh(BasicTexture tex, int x, int y, int xyBuffer, int uvBuffer, int indexBuffer, int indexCount) {

    }

    @Override
    public void drawTexture(BasicTexture texture, RectF source, RectF target) {

    }

    @Override
    public void drawTexture(BasicTexture texture, float[] mTextureTransform, int x, int y, int w, int h) {

    }

    @Override
    public void drawMixed(BasicTexture from, int toColor, float ratio, int x, int y, int w, int h) {

    }

    @Override
    public void drawMixed(BasicTexture from, int toColor, float ratio, RectF src, RectF target) {

    }

    @Override
    public boolean unloadTexture(BasicTexture texture) {
        return false;
    }

    @Override
    public void deleteBuffer(int bufferId) {

    }

    @Override
    public void deleteRecycledResources() {

    }

    @Override
    public void dumpStatisticsAndClear() {

    }

    @Override
    public void beginRenderTarget(RawTexture texture) {

    }

    @Override
    public void endRenderTarget() {

    }

    @Override
    public void setTextureParameters(BasicTexture texture) {

    }

    @Override
    public void initializeTextureSize(BasicTexture texture, int format, int type) {

    }

    @Override
    public void initializeTexture(BasicTexture texture, Bitmap bitmap) {

    }

    @Override
    public void texSubImage2D(BasicTexture texture, int xOffset, int yOffset, Bitmap bitmap, int format, int type) {

    }

    @Override
    public int uploadBuffer(FloatBuffer buffer) {
        return 0;
    }

    @Override
    public int uploadBuffer(ByteBuffer buffer) {
        return 0;
    }

    @Override
    public void recoverFromLightCycle() {

    }

    @Override
    public void getBounds(Rect bounds, int x, int y, int width, int height) {

    }
}
