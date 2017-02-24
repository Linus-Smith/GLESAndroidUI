package com.linus.glesandroiduilib.GLES.ui;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.hardware.Camera;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by Linus on 2017/2/23.
 */

public class ApiHelper {
    public static final boolean AT_LEAST_16;
    public static final boolean USE_888_PIXEL_FORMAT;
    public static final boolean ENABLE_PHOTO_EDITOR;
    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE;
    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    public static final boolean HAS_MEDIA_COLUMNS_WIDTH_AND_HEIGHT;
    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_REGION_DECODER;
    public static final boolean HAS_REUSING_BITMAP_IN_BITMAP_FACTORY;
    public static final boolean HAS_SET_BEAM_PUSH_URIS;
    public static final boolean HAS_SET_DEFALT_BUFFER_SIZE;
    public static final boolean HAS_RELEASE_SURFACE_TEXTURE;
    public static final boolean HAS_SURFACE_TEXTURE;
    public static final boolean HAS_MTP;
    public static final boolean HAS_AUTO_FOCUS_MOVE_CALLBACK;
    public static final boolean HAS_REMOTE_VIEWS_SERVICE;
    public static final boolean HAS_INTENT_EXTRA_LOCAL_ONLY;
    public static final boolean HAS_SET_SYSTEM_UI_VISIBILITY;
    public static final boolean HAS_FACE_DETECTION;
    public static final boolean HAS_GET_CAMERA_DISABLED;
    public static final boolean HAS_MEDIA_ACTION_SOUND;
    public static final boolean HAS_OLD_PANORAMA;
    public static final boolean HAS_TIME_LAPSE_RECORDING;
    public static final boolean HAS_ZOOM_WHEN_RECORDING;
    public static final boolean HAS_CAMERA_FOCUS_AREA;
    public static final boolean HAS_CAMERA_METERING_AREA;
    public static final boolean HAS_FINE_RESOLUTION_QUALITY_LEVELS;
    public static final boolean HAS_MOTION_EVENT_TRANSFORM;
    public static final boolean HAS_EFFECTS_RECORDING = false;
    public static final boolean HAS_EFFECTS_RECORDING_CONTEXT_INPUT;
    public static final boolean HAS_GET_SUPPORTED_VIDEO_SIZE;
    public static final boolean HAS_SET_ICON_ATTRIBUTE;
    public static final boolean HAS_MEDIA_PROVIDER_FILES_TABLE;
    public static final boolean HAS_SURFACE_TEXTURE_RECORDING;
    public static final boolean HAS_ACTION_BAR;
    public static final boolean HAS_VIEW_TRANSFORM_PROPERTIES;
    public static final boolean HAS_CAMERA_HDR;
    public static final boolean HAS_OPTIONS_IN_MUTABLE;
    public static final boolean CAN_START_PREVIEW_IN_JPEG_CALLBACK;
    public static final boolean HAS_VIEW_PROPERTY_ANIMATOR;
    public static final boolean HAS_POST_ON_ANIMATION;
    public static final boolean HAS_ANNOUNCE_FOR_ACCESSIBILITY;
    public static final boolean HAS_OBJECT_ANIMATION;
    public static final boolean HAS_GLES20_REQUIRED;
    public static final boolean HAS_ROTATION_ANIMATION;
    public static final boolean HAS_ORIENTATION_LOCK;
    public static final boolean HAS_CANCELLATION_SIGNAL;
    public static final boolean HAS_MEDIA_MUXER;
    public static final boolean HAS_DISPLAY_LISTENER;

    public ApiHelper() {
    }

    public static int getIntFieldIfExists(Class<?> klass, String fieldName, Class<?> obj, int defaultVal) {
        try {
            Field e = klass.getDeclaredField(fieldName);
            return e.getInt(obj);
        } catch (Exception var5) {
            return defaultVal;
        }
    }

    private static boolean hasField(Class<?> klass, String fieldName) {
        try {
            klass.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException var3) {
            return false;
        }
    }

    private static boolean hasMethod(String className, String methodName, Class... parameterTypes) {
        try {
            Class th = Class.forName(className);
            th.getDeclaredMethod(methodName, parameterTypes);
            return true;
        } catch (Throwable var4) {
            return false;
        }
    }

    private static boolean hasMethod(Class<?> klass, String methodName, Class... paramTypes) {
        try {
            klass.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException var4) {
            return false;
        }
    }

    static {
        AT_LEAST_16 = Build.VERSION.SDK_INT >= 16;
        USE_888_PIXEL_FORMAT = Build.VERSION.SDK_INT >= 16;
        ENABLE_PHOTO_EDITOR = Build.VERSION.SDK_INT >= 14;
        HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE = hasField(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");
        HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION = hasField(View.class, "SYSTEM_UI_FLAG_HIDE_NAVIGATION");
        HAS_MEDIA_COLUMNS_WIDTH_AND_HEIGHT = hasField(MediaStore.MediaColumns.class, "WIDTH");
        HAS_REUSING_BITMAP_IN_BITMAP_REGION_DECODER = Build.VERSION.SDK_INT >= 16;
        HAS_REUSING_BITMAP_IN_BITMAP_FACTORY = Build.VERSION.SDK_INT >= 11;
        HAS_SET_BEAM_PUSH_URIS = Build.VERSION.SDK_INT >= 16;
        HAS_SET_DEFALT_BUFFER_SIZE = hasMethod("android.graphics.SurfaceTexture", "setDefaultBufferSize", new Class[]{Integer.TYPE, Integer.TYPE});
        HAS_RELEASE_SURFACE_TEXTURE = hasMethod("android.graphics.SurfaceTexture", "release", new Class[0]);
        HAS_SURFACE_TEXTURE = Build.VERSION.SDK_INT >= 11;
        HAS_MTP = Build.VERSION.SDK_INT >= 12;
        HAS_AUTO_FOCUS_MOVE_CALLBACK = Build.VERSION.SDK_INT >= 16;
        HAS_REMOTE_VIEWS_SERVICE = Build.VERSION.SDK_INT >= 11;
        HAS_INTENT_EXTRA_LOCAL_ONLY = Build.VERSION.SDK_INT >= 11;
        HAS_SET_SYSTEM_UI_VISIBILITY = hasMethod(View.class, "setSystemUiVisibility", new Class[]{Integer.TYPE});
        boolean hasFaceDetection = false;

        try {
            Class t = Class.forName("android.hardware.Camera$FaceDetectionListener");
            hasFaceDetection = hasMethod(Camera.class, "setFaceDetectionListener", new Class[]{t}) && hasMethod(Camera.class, "startFaceDetection", new Class[0]) && hasMethod(Camera.class, "stopFaceDetection", new Class[0]) && hasMethod(Camera.Parameters.class, "getMaxNumDetectedFaces", new Class[0]);
        } catch (Throwable var2) {
            ;
        }

        HAS_FACE_DETECTION = hasFaceDetection;
        HAS_GET_CAMERA_DISABLED = hasMethod(DevicePolicyManager.class, "getCameraDisabled", new Class[]{ComponentName.class});
        HAS_MEDIA_ACTION_SOUND = Build.VERSION.SDK_INT >= 16;
        HAS_OLD_PANORAMA = Build.VERSION.SDK_INT >= 14;
        HAS_TIME_LAPSE_RECORDING = Build.VERSION.SDK_INT >= 11;
        HAS_ZOOM_WHEN_RECORDING = Build.VERSION.SDK_INT >= 14;
        HAS_CAMERA_FOCUS_AREA = Build.VERSION.SDK_INT >= 14;
        HAS_CAMERA_METERING_AREA = Build.VERSION.SDK_INT >= 14;
        HAS_FINE_RESOLUTION_QUALITY_LEVELS = Build.VERSION.SDK_INT >= 11;
        HAS_MOTION_EVENT_TRANSFORM = Build.VERSION.SDK_INT >= 11;
        HAS_EFFECTS_RECORDING_CONTEXT_INPUT = Build.VERSION.SDK_INT >= 17;
        HAS_GET_SUPPORTED_VIDEO_SIZE = Build.VERSION.SDK_INT >= 11;
        HAS_SET_ICON_ATTRIBUTE = Build.VERSION.SDK_INT >= 11;
        HAS_MEDIA_PROVIDER_FILES_TABLE = Build.VERSION.SDK_INT >= 11;
        HAS_SURFACE_TEXTURE_RECORDING = Build.VERSION.SDK_INT >= 16;
        HAS_ACTION_BAR = Build.VERSION.SDK_INT >= 11;
        HAS_VIEW_TRANSFORM_PROPERTIES = Build.VERSION.SDK_INT >= 11;
        HAS_CAMERA_HDR = Build.VERSION.SDK_INT >= 17;
        HAS_OPTIONS_IN_MUTABLE = Build.VERSION.SDK_INT >= 11;
        CAN_START_PREVIEW_IN_JPEG_CALLBACK = Build.VERSION.SDK_INT >= 14;
        HAS_VIEW_PROPERTY_ANIMATOR = Build.VERSION.SDK_INT >= 12;
        HAS_POST_ON_ANIMATION = Build.VERSION.SDK_INT >= 16;
        HAS_ANNOUNCE_FOR_ACCESSIBILITY = Build.VERSION.SDK_INT >= 16;
        HAS_OBJECT_ANIMATION = Build.VERSION.SDK_INT >= 11;
        HAS_GLES20_REQUIRED = Build.VERSION.SDK_INT >= 11;
        HAS_ROTATION_ANIMATION = hasField(WindowManager.LayoutParams.class, "rotationAnimation");
        HAS_ORIENTATION_LOCK = Build.VERSION.SDK_INT >= 18;
        HAS_CANCELLATION_SIGNAL = Build.VERSION.SDK_INT >= 16;
        HAS_MEDIA_MUXER = Build.VERSION.SDK_INT >= 18;
        HAS_DISPLAY_LISTENER = Build.VERSION.SDK_INT >= 17;
    }

    public interface VERSION_CODES {
        int GINGERBREAD_MR1 = 10;
        int HONEYCOMB = 11;
        int HONEYCOMB_MR1 = 12;
        int HONEYCOMB_MR2 = 13;
        int ICE_CREAM_SANDWICH = 14;
        int ICE_CREAM_SANDWICH_MR1 = 15;
        int JELLY_BEAN = 16;
        int JELLY_BEAN_MR1 = 17;
        int JELLY_BEAN_MR2 = 18;
    }
}
