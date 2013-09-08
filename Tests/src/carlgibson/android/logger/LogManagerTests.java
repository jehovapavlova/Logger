package carlgibson.android.logger;

import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.test.AndroidTestCase;
import android.view.Display;
import carlgibson.android.logger.Controller.LogHandler;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.DAL.MockLogData;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 23/05/13
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class LogManagerTests extends AndroidTestCase {

    LogHandler sut;

    @Override
    public void setUp() throws Exception {

        Field field = LogHandler.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        field = MockLogData.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        sut = LogHandler.getInstance(new Context() {
            @Override
            public AssetManager getAssets() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Resources getResources() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public PackageManager getPackageManager() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ContentResolver getContentResolver() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Looper getMainLooper() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Context getApplicationContext() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setTheme(int resid) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Resources.Theme getTheme() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getPackageName() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ApplicationInfo getApplicationInfo() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getPackageResourcePath() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String getPackageCodePath() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public SharedPreferences getSharedPreferences(String name, int mode) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public FileInputStream openFileInput(String name) throws FileNotFoundException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean deleteFile(String name) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getFileStreamPath(String name) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getFilesDir() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getExternalFilesDir(String type) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getObbDir() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getCacheDir() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getExternalCacheDir() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String[] fileList() {
                return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getDir(String name, int mode) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean deleteDatabase(String name) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public File getDatabasePath(String name) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String[] databaseList() {
                return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Drawable getWallpaper() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Drawable peekWallpaper() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getWallpaperDesiredMinimumWidth() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int getWallpaperDesiredMinimumHeight() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setWallpaper(Bitmap bitmap) throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void setWallpaper(InputStream data) throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void clearWallpaper() throws IOException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startActivity(Intent intent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startActivity(Intent intent, Bundle options) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startActivities(Intent[] intents) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startActivities(Intent[] intents, Bundle options) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendBroadcast(Intent intent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendBroadcast(Intent intent, String receiverPermission) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendBroadcastAsUser(Intent intent, UserHandle user) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendStickyBroadcast(Intent intent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void removeStickyBroadcast(Intent intent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void unregisterReceiver(BroadcastReceiver receiver) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public ComponentName startService(Intent service) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean stopService(Intent service) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean bindService(Intent service, ServiceConnection conn, int flags) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void unbindService(ServiceConnection conn) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getSystemService(String name) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkPermission(String permission, int pid, int uid) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkCallingPermission(String permission) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkCallingOrSelfPermission(String permission) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforcePermission(String permission, int pid, int uid, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceCallingPermission(String permission, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceCallingOrSelfPermission(String permission, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void revokeUriPermission(Uri uri, int modeFlags) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkCallingUriPermission(Uri uri, int modeFlags) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Context createConfigurationContext(Configuration overrideConfiguration) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Context createDisplayContext(Display display) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        Field field = LogHandler.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        field = MockLogData.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);
    }

    public void testLogManagerGetLogs() throws Exception {

        List<Log> logs = sut.getDateDescSortedLogs();
        int actual = logs.size();

        assertEquals(0,actual);
    }

    public void testLogManagerLogsAfterAddition() throws Exception {

        List<Log> l1 = sut.getDateDescSortedLogs();
        int before = l1.size();

        sut.addLogEntry(new Log(0,null,null,1,null,null,new Date()));

        List<Log> l2 = sut.getDateDescSortedLogs();
        int after = l2.size();

        assertEquals(before+1, after);
    }
}
