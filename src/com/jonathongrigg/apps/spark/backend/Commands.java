/*
 * Copyright (C) 2011 Jonathon Grigg <jonathongrigg@gmail.com>
 *
 * Licensed under the Open Software License version 3.0
 * A copy of the license may be obtained from http://www.opensource.org/licenses/osl-3.0
 */

package com.jonathongrigg.apps.spark.backend;

import android.content.Context;
import android.util.Log;

public class Commands {
    
    Context context;

    private static final String TAG = "Spark Mod Manager, Backend Commands";
    public static final String touchkeyDimmerEnablePath = "/sys/class/misc/backlightdimmer/enabled";
    public static final String screenDimmerEnablePath = "/sys/class/misc/screendimmer/enabled";
    public static final String touchWakeEnablePath = "/sys/class/misc/touchwake/enabled";

    public Commands(Context context) {
        this.context = context;
    }
    
    public final void toggleTouchkeyDimmer(boolean isEnabled) {
        Log.i(TAG, "Changing Touchkey Dimmer state to " + isEnabled);
        String script = "#!/system/bin/sh\n";
        script += "echo ";
        
        if (isEnabled == true) {
            script += "1";
        } else {
            script += "0";
        }
        
        script += " > ";
        script += touchkeyDimmerEnablePath;

        Utils.runScript(context, script, "su");
    }
    
    public final void toggleTouchWake(boolean isEnabled) {
        Log.i(TAG, "Changing Touch Wake state to " + isEnabled);
        String script = "#!/system/bin/sh\n";
        script += "echo ";
        
        if (isEnabled == true) {
            script += "1";
        } else {
            script += "0";
        }
        
        script += " > ";
        script += touchWakeEnablePath;

        Utils.runScript(context, script, "su");
    }

    public final void toggleScreenDimmer(boolean isEnabled) {
        Log.i(TAG, "Changing Screen Dimmer state to " + isEnabled);
        String script = "#!/system/bin/sh\n";
        script += "echo ";
        
        if (isEnabled == true) {
            script += "1";
        } else {
            script += "0";
        }
        
        script += " > ";
        script += screenDimmerEnablePath;

        Utils.runScript(context, script, "su");
    }
}
