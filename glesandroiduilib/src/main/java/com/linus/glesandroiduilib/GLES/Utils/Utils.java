package com.linus.glesandroiduilib.GLES.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by Linus on 2017/2/23.
 */

public class Utils {
    private static final String TAG = "Utils";
    private static final String DEBUG_TAG = "GalleryDebug";
    private static final long POLY64REV = -7661587058870466123L;
    private static final long INITIALCRC = -1L;
    private static long[] sCrcTable = new long[256];
    private static final boolean IS_DEBUG_BUILD;
    private static final String MASK_STRING = "********************************";

    public Utils() {
    }

    public static void assertTrue(boolean cond) {
        if(!cond) {
            throw new AssertionError();
        }
    }

    public static void fail(String message, Object... args) {
        throw new AssertionError(args.length == 0?message:String.format(message, args));
    }

    public static <T> T checkNotNull(T object) {
        if(object == null) {
            throw new NullPointerException();
        } else {
            return object;
        }
    }

    public static boolean equals(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    public static int nextPowerOf2(int n) {
        if(n > 0 && n <= 1073741824) {
            --n;
            n |= n >> 16;
            n |= n >> 8;
            n |= n >> 4;
            n |= n >> 2;
            n |= n >> 1;
            return n + 1;
        } else {
            throw new IllegalArgumentException("n is invalid: " + n);
        }
    }

    public static int prevPowerOf2(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException();
        } else {
            return Integer.highestOneBit(n);
        }
    }

    public static int clamp(int x, int min, int max) {
        return x > max?max:(x < min?min:x);
    }

    public static float clamp(float x, float min, float max) {
        return x > max?max:(x < min?min:x);
    }

    public static long clamp(long x, long min, long max) {
        return x > max?max:(x < min?min:x);
    }

    public static boolean isOpaque(int color) {
        return color >>> 24 == 255;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static final long crc64Long(String in) {
        return in != null && in.length() != 0?crc64Long(getBytes(in)):0L;
    }

    public static final long crc64Long(byte[] buffer) {
        long crc = -1L;
        int k = 0;

        for(int n = buffer.length; k < n; ++k) {
            crc = sCrcTable[((int)crc ^ buffer[k]) & 255] ^ crc >> 8;
        }

        return crc;
    }

    public static byte[] getBytes(String in) {
        byte[] result = new byte[in.length() * 2];
        int output = 0;
        char[] arr$ = in.toCharArray();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            char ch = arr$[i$];
            result[output++] = (byte)(ch & 255);
            result[output++] = (byte)(ch >> 8);
        }

        return result;
    }

    public static void closeSilently(Closeable c) {
        if(c != null) {
            try {
                c.close();
            } catch (IOException var2) {
                Log.w("Utils", "close fail ", var2);
            }

        }
    }

    public static int compare(long a, long b) {
        return a < b?-1:(a == b?0:1);
    }

    public static int ceilLog2(float value) {
        int i;
        for(i = 0; i < 31 && (float)(1 << i) < value; ++i) {
            ;
        }

        return i;
    }

    public static int floorLog2(float value) {
        int i;
        for(i = 0; i < 31 && (float)(1 << i) <= value; ++i) {
            ;
        }

        return i - 1;
    }

    public static void closeSilently(ParcelFileDescriptor fd) {
        try {
            if(fd != null) {
                fd.close();
            }
        } catch (Throwable var2) {
            Log.w("Utils", "fail to close", var2);
        }

    }

    public static void closeSilently(Cursor cursor) {
        try {
            if(cursor != null) {
                cursor.close();
            }
        } catch (Throwable var2) {
            Log.w("Utils", "fail to close", var2);
        }

    }

    public static float interpolateAngle(float source, float target, float progress) {
        float diff = target - source;
        if(diff < 0.0F) {
            diff += 360.0F;
        }

        if(diff > 180.0F) {
            diff -= 360.0F;
        }

        float result = source + diff * progress;
        return result < 0.0F?result + 360.0F:result;
    }

    public static float interpolateScale(float source, float target, float progress) {
        return source + progress * (target - source);
    }

    public static String ensureNotNull(String value) {
        return value == null?"":value;
    }

    public static float parseFloatSafely(String content, float defaultValue) {
        if(content == null) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(content);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static int parseIntSafely(String content, int defaultValue) {
        if(content == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(content);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    public static boolean isNullOrEmpty(String exifMake) {
        return TextUtils.isEmpty(exifMake);
    }

    public static void waitWithoutInterrupt(Object object) {
        try {
            object.wait();
        } catch (InterruptedException var2) {
            Log.w("Utils", "unexpected interrupt: " + object);
        }

    }

    public static boolean handleInterrruptedException(Throwable e) {
        if(!(e instanceof InterruptedIOException) && !(e instanceof InterruptedException)) {
            return false;
        } else {
            Thread.currentThread().interrupt();
            return true;
        }
    }

    public static String escapeXml(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        for(int len = s.length(); i < len; ++i) {
            char c = s.charAt(i);
            switch(c) {
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&#039;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String getUserAgent(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException var3) {
            throw new IllegalStateException("getPackageInfo failed");
        }

        return String.format("%s/%s; %s/%s/%s/%s; %s/%s/%s", new Object[]{packageInfo.packageName, packageInfo.versionName, Build.BRAND, Build.DEVICE, Build.MODEL, Build.ID, Integer.valueOf(Build.VERSION.SDK_INT), Build.VERSION.RELEASE, Build.VERSION.INCREMENTAL});
    }

    public static String[] copyOf(String[] source, int newSize) {
        String[] result = new String[newSize];
        newSize = Math.min(source.length, newSize);
        System.arraycopy(source, 0, result, 0, newSize);
        return result;
    }

    public static String maskDebugInfo(Object info) {
        if(info == null) {
            return null;
        } else {
            String s = info.toString();
            int length = Math.min(s.length(), "********************************".length());
            return IS_DEBUG_BUILD?s:"********************************".substring(0, length);
        }
    }

    public static void debug(String message, Object... args) {
        Log.v("GalleryDebug", String.format(message, args));
    }

    static {
        IS_DEBUG_BUILD = Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug");

        for(int i = 0; i < 256; ++i) {
            long part = (long)i;

            for(int j = 0; j < 8; ++j) {
                long x = ((int)part & 1) != 0?-7661587058870466123L:0L;
                part = part >> 1 ^ x;
            }

            sCrcTable[i] = part;
        }

    }
}
