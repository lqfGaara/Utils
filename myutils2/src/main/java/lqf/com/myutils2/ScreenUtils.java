package lqf.com.myutils2;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import java.math.BigDecimal;
public class ScreenUtils {
   private static float sNoncompatDensity;
    private static float SNocompateScaledDensity;
    public static void setHello(String ss ){
        System.out.println("hahhaahhah"+ss);
    }

    public static void setCustomDebsity(Activity activity, final Application application){
         DisplayMetrics appDisplayMetrices= application.getResources().getDisplayMetrics();
         if (sNoncompatDensity==0){
             sNoncompatDensity=appDisplayMetrices.density;
             SNocompateScaledDensity=appDisplayMetrices.scaledDensity;
             application.registerComponentCallbacks(new ComponentCallbacks() {
                 @Override
                 public void onConfigurationChanged(Configuration newConfig) {
                     if (newConfig!=null && newConfig.fontScale>0){
                         SNocompateScaledDensity=application.getResources().getDisplayMetrics().scaledDensity;
                     }
                 }

                 @Override
                 public void onLowMemory() {

                 }
             });
         }
         final  float targetDensity=  new BigDecimal((float)appDisplayMetrices.widthPixels/360).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
         final float targetScaledDensity=targetDensity*(SNocompateScaledDensity/sNoncompatDensity);
         final int targetDensityDpi= (int) (160*targetDensity);
         appDisplayMetrices.density=targetDensity;
         appDisplayMetrices.scaledDensity=targetScaledDensity;
         appDisplayMetrices.densityDpi=targetDensityDpi;

        DisplayMetrics activityDisplayMetrices= activity.getResources().getDisplayMetrics();
        activityDisplayMetrices.densityDpi=targetDensityDpi;
        activityDisplayMetrices.scaledDensity=targetScaledDensity;
        activityDisplayMetrices.densityDpi=targetDensityDpi;
    }
}
