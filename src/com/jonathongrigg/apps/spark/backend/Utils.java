/* 
 * Utils.java by Supercurio (Francois Simond) for the Voodoo OTA RootKeeper app
 * Code licensed under the DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 * 
 * Modified slightly for Spark's purposes by Jonathon Grigg (aka removed what wasn't needed)
 */

package com.jonathongrigg.apps.spark.backend;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;

public class Utils {

    private static final String TAG = "Spark Mod Manager, Backend Utils";
    private static final String scriptFileName = "commands.sh";

    public static final String runScript(Context context, String content) {
        return runScript(context, content, "/system/bin/sh");
    }

    public static final String runScript(Context context, String content, String shell) {

        Log.d(TAG, "Run script content (with shell: " + shell + "):\n" + content);

        StringBuilder output = new StringBuilder();

        try {

            // write script content
            FileOutputStream fos = context.openFileOutput(scriptFileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            // set script file permission
            Process p = Runtime.getRuntime().exec(
                            "chmod 700 " + context.getFileStreamPath(scriptFileName));
            p.waitFor();

            // now execute the script
            String command = context.getFileStreamPath(scriptFileName).getAbsolutePath();
            command = shell + " -c " + command;
            p = Runtime.getRuntime().exec(command);
            p.waitFor();

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                output.append(line);
                output.append("\n");
            }

        } catch (Exception e) {
            Log.d(TAG, "unable to run script: " + scriptFileName);
            e.printStackTrace();
        }
        return output.toString();
    }

    public static final String getCommandOutput(String command) throws IOException {

        StringBuilder output = new StringBuilder();

        Process p = Runtime.getRuntime().exec(command);
        InputStream is = p.getInputStream();
        InputStreamReader r = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(r);

        String line;
        while ((line = in.readLine()) != null) {
            output.append(line);
            output.append("\n");
        }

        return output.toString();
    }

}
