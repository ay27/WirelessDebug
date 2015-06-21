package com.ay27.WirelessDebug;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Proudly to user Intellij IDEA.
 * Created by ay27 on 15/1/24.
 */
public class UpgradeSystemPermission {

    public static final String PERMISSION_WRITE_SECURE_SETTINGS = "android.permission.WRITE_SECURE_SETTINGS";
    public static final String PACKAGE_NAME = WirelessDebugApplication.getContext().getPackageName();

    private UpgradeSystemPermission() {
    }

    public static boolean upgradeRootPermission() {
        return runCmd("chmod 777 " + PACKAGE_NAME);
//        Process process = null;
//        DataOutputStream os = null;
//        try {
//            String cmd="chmod 777 " + PACKAGE_NAME;
//            runCmd(cmd);
////            String cmd2 = "pm grant bitman.ay27.blockade  android.permission.WRITE_SECURE_SETTINGS";
//            process = Runtime.getRuntime().exec("su"); //切换到root帐号
//            os = new DataOutputStream(process.getOutputStream());
//            os.writeBytes(cmd1 + "\n");
////            os.writeBytes(cmd2 + "\n");
//            os.writeBytes("exit\n");
//            os.flush();
//            process.waitFor();
//        } catch (Exception e) {
//            return false;
//        } finally {
//            try {
//                if (os != null) {
//                    os.close();
//                }
//                process.destroy();
//            } catch (Exception e) {
//            }
//        }
//        return true;
    }

    public static boolean grantSystemPermission(String permission) {
        return runCmd("pm grant " + PACKAGE_NAME + " " + permission);
    }

    public static boolean runCmd(String cmd) {
        Process process = null;
        DataOutputStream dos = null;
        try {
            process = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes(cmd + "\n exit\n");
            dos.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null)
                process.destroy();
        }
        return true;
    }

}
