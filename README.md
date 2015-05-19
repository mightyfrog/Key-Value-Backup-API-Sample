# BackupAPI
Android backup API sample code

http://developer.android.com/training/cloudsync/backupapi.html

*You must use your own package name with an API key gerated for it.


programmatically create and enqueue a backup request:
```java
new BackupManager(context).dataChanged(); // backup happens at some point in the future
```

use adb command to run backup in the queue immediately:
```shell
adb shell bmgr run
```

when a backup is successfully made, you'll see log messages in logcat:
```shell
V/BackupServiceBinder﹕ doBackup() invoked
D/BackupHelperDispatcher﹕ handling new helper 'myfiles'
```
or this if it's not for the first time
```shell
D/BackupHelperDispatcher﹕ handling existing helper 'myfiles' android.app.backup.FileBackupHelper@32ef2023
```
at this point, uninstall the app and delete one.txt, two,txt, and three.txt from the disk. Resintall the app to see three files get restored. (*auto-restore must be anabled in Backup & reset in the system settings)

you can use adb to create and enqueue a backup request:
```shell
adb shell bmgr backup <package name>
```

programmatically restore from a backup
```java
new BackupManager(context).requestRestore(callback); // restores immediately
```

adb to restore:
```shell
adb shell bmgr restore
```

adb to list available transports:
```shell
adb shell list transports
```

adb to select a transport to use:
```shell
adb shell transport android/com.android.internal.backup.LocalTransport
```
