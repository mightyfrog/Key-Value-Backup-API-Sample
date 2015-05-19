# BackupAPI
Android backup API sample code

http://developer.android.com/training/cloudsync/backupapi.html

*You must use your own package name with an API key gerated for it.

*no transports other than android/com.android.internal.backup.LocalTransport seems to be working :(



<b>Create and enqueue a backup request</b>

programmatically:
```java
new BackupManager(context).dataChanged();
```

adb:
```shell
adb shell bmgr backup <package name>
```

At some point in the future, Android calls onBackup() to perform backups in the queue. To perform backups immediately for testing, use adb command:
```shell
adb shell bmgr run
```

on successful backup, you'll see log messages in logcat:
```shell
V/BackupServiceBinder﹕ doBackup() invoked
D/BackupHelperDispatcher﹕ handling new helper 'myfiles'
```
or this if it's not for the first time
```shell
D/BackupHelperDispatcher﹕ handling existing helper 'myfiles' android.app.backup.FileBackupHelper@32ef2023
```
at this point, uninstall the app and delete one.txt, two,txt, and three.txt from the disk. Resintall the app to see three files get restored. (*auto-restore must be anabled in Backup & reset in the system settings)


<b>Restore from a backup</b>

programmatically:
```java
new BackupManager(context).requestRestore(callback); // restores immediately
```

adb:
```shell
adb shell bmgr restore
```

on success, you will see something like this:
```shell
restoreStarting: 1 packages
restoreFinished: 0
done
```
restoreFinished other than 0 indicates an error: 
https://github.com/android/platform_frameworks_base/blob/a6ed1b9547dcd592031a415739cc7e672c068f7c/core/java/android/app/backup/BackupTransport.java

adb to list available transports:
```shell
adb shell list transports
```
you will see two transports and one more if you have an enterprise account:
```shell
*android/com.android.internal.backup.LocalTransport
 com.google.android.gms/.backup.BackupTransportService
 com.android.server.enterprise/.EdmBackupTranspo
```
\* indicates the currently selected transport.

adb to select a transport to use:
```shell
adb shell transport android/com.android.internal.backup.LocalTransport
```

adb to clear backups:
```shell
adb shell bmgr wipe <transport> <package name>
```
