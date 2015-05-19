package org.mightyfrog.android.minimal.backupapi;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.os.Environment;

import java.io.File;

/**
 * @author ssoejima on 5/19/2015.
 */
public class BackupAgent extends BackupAgentHelper {
    @Override
    public void onCreate() {
        super.onCreate();

        FileBackupHelper helper = new FileBackupHelper(this, "one.txt", "two.txt", "three.txt");
        addHelper("myfiles", helper);
    }

    @Override
    public File getFilesDir() {
        // return super.getFilesDir();

        // override this method to change the file root directory
        // all the files names above must be relative to this path!!
        return Environment.getExternalStorageDirectory();
    }
}
