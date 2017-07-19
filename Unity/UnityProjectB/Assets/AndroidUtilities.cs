using UnityEngine;

public static class AndroidUtilities
{
    public static void InvokeNativeMethod(string methodName, params object[] args)
    {
        const string PLAYER_NAME = "com.sk.androidlibraryb.UnityPlayerActivityB";

        Debug.Log("[Android] Calling native method=" + methodName + " on player=" + PLAYER_NAME);

        using (var playerCls = new AndroidJavaClass(PLAYER_NAME))
        {
            using (AndroidJavaObject activityObj = playerCls.GetStatic<AndroidJavaObject>("currentActivity"))
            {
                activityObj.Call(methodName, args);
            }
        }
    }

    public static T InvokeNativeMethod<T>(string methodName, params object[] args)
    {
        const string PLAYER_NAME = "com.sk.androidlibraryb.UnityPlayerActivityB";

        Debug.Log("[Android] Calling native method=" + methodName + " on player=" + PLAYER_NAME);

        T returnObj;

        using (var playerCls = new AndroidJavaClass(PLAYER_NAME))
        {
            using (AndroidJavaObject activityObj = playerCls.GetStatic<AndroidJavaObject>("currentActivity"))
            {
                returnObj = activityObj.Call<T>(methodName, args);
            }
        }

        return returnObj;
    }
}
